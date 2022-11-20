import com.fasterxml.jackson.databind.ObjectMapper;
import model.Note;
import model.User;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static util.Util.*;

/**
 * Klassen Main huvud point för programmet.
 *
 */
public class Main {
    static private Scanner scanner = new Scanner(System.in);
    static private ObjectMapper mapper = new ObjectMapper();
    static private Pattern pattern = Pattern.compile("[A-Za-z]*");
    static public List<User> usersList;
    static private List<Note> noteList;
    static private String name;
    static private User currentUser = new User("Ingen", new ArrayList<>());

    /**
     * Huvudmedtoden  main som startar programmet.
     * @param args inkommande values
     */
    public static void main(String[] args) {
        try {
            usersList = List.of(mapper.readValue(USER_LIST_JSON, User[].class)).stream().collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            usersList = new ArrayList<>();
            try {
                mapper.writeValue(USER_LIST_JSON, usersList);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (NullPointerException e) {
            usersList = new ArrayList<>();
            name = "Ingen";
            usersList.add(currentUser);
        }
        helloMessageToUser();
        menuUsersOption();
    }


    /**
     * Metoden for huvudmenu  som visa olika alternativ för användare.
     * Läsas values från Scanner.
     * Try och Catch filtrerar fel format for inkommande values.
     */
    static void showOptionsMainMenu() {
        optionsMainMenuShowText();
        try {
            int val = scanner.nextInt();
            switch (val) {
                case 1:
                    writeUsersNote();
                    break;
                case 2:
                    readUsersNote();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    //TODO   Fel text och prova en gång till!
                    System.out.println("Fel format!");
                    showOptionsMainMenu();
                    return;
            }
            showOptionsMainMenu();
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(G + "Fel format,prova igen!");
            textEndP();
            scanner.next();
            showOptionsMainMenu();
        }
    }

    /**
     * Metoden som skapar nya användare.
     * Metoden wriyeValue sparar userList i Json format.
     * Try och Catch filtrerar fel format for inkommande values.
     */

    static void createNewUsersProfil() {
        textSpace();
        System.out.println("Vad heter du?");
        if (scanner.hasNext(pattern)) {
            name = scanner.next();
            noteList = new ArrayList<>();
            User currentUser = new User(name, noteList);
            usersList.add(currentUser);
            System.out.println(" \nVälkommen till dagbok "
                    + getColor()
                    + currentUser.getName().toUpperCase() + R
                    + "!");
            try {
                mapper.writeValue(USER_LIST_JSON, usersList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            showOptionsMainMenu();
        } else {
            System.out.println("Fel format exception: ");
        }
    }

    /**
     * Menu för användares val
     */
    static void createNewUser() {
        textSpace();
        System.out.println(" \n\nVill du presentera dig?  :   " + O
                + "1" + R + " \n\nGå tillbaka till Huvudmenu  " + C
                + "2" + R + "\n\nAvsluta programmet    " + G
                + "3" + G);
        int val = scanner.nextInt();
        switch (val) {
            case 1:
                createNewUsersProfil();
            case 2:
                textSpace();
                menuUsersOption();
            case 3:
                exit();
        }
    }

    /**
     * Metoden som läser användars lista.
     * Try och Catch filtrerar fel format for inkommande values.
     */
    static void readListOfUsers() {
        int lenghtOfUserList = usersList.size();
        userListExistControll(lenghtOfUserList);
        for (int i = 0; i < lenghtOfUserList; i++) {
            User n = usersList.get(i);
            getColor();
            System.out.println(R + "\nUsers att välja \n  "
                    + "\t" + O + n.getName()
                    + ":    " + C
                    + (i + 1) + R);
        }
        System.out.println(R + "\n Välja att gå tillbaka till Huvudmenu... \n  "
                + "\t" + O + "0" + R);
        try {
            int index = scanner.nextInt();
            if (index != 0) {
                System.out.println("\n Välj användare :\n");
                currentUser = usersList.get(index - 1);
                noteList = currentUser.getNotes();
                System.out.println(getColor()
                        + "Du inloggat som  "
                        + getColor()
                        + currentUser.getName().toUpperCase() + R
                        + "!");
                textSpace();
                showOptionsMainMenu();
            } else {
                menuUsersOption();
            }
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(G + "Fel format,prova igen!");
            textEndP();
            scanner.next();
            readListOfUsers();
        }
    }

    /**
     * Metoden för användarens val.
     * Try och Catch filtrerar fel format for inkommande values.
     */

    static void menuUsersOption() {
        System.out.println(G + "Aktiv användare: " + C
                + currentUser.getName() + R);
        System.out.println(" \n\nVälj användare :   " + O
                + "1" + R + " \n\nSkapa ny användare :  " + C
                + "2" + R + "\n\nAvsluta programmet :   " + G
                + "3" + R);
        try {
            int val = scanner.nextInt();
            switch (val) {
                case 1:
                    textSpace();
                    readListOfUsers();
                    break;
                case 2:
                    createNewUser();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    textEndP();
                    System.out.println(G + "Fel num,välja 1, 2 eller 3!");
                    textEndP();
                    menuUsersOption();
            }
        } catch (InputMismatchException e) {
            System.out.println(G + "Fel format,prova igen!");
            textEndP();
            scanner.next();
            menuUsersOption();
        }
    }

    /**
     * Metoden som läser användares inlägg.
     */

    static void readUsersNote() {
        textSpace();
        System.out.println("Du har "
                + getColor()
                + noteList.size()
                + " artiklar att läsa" + R);
        if (noteList.size() != 0) {
            for (int i = 0; i < noteList.size(); i++) {
                System.out.println(R + "\nTitel " + G
                        + "'"
                        + noteList.get(i).getTitel() + R + "'" + "\nnr :  " + O

                        + (i + 1));
                textEndP();
            }
            System.out.println(R + "Välja num på artikeln : " + C);
            try {
                int index = scanner.nextInt();
                System.out.println(noteList.get(index - 1).getText()
                        + "\n"
                        + noteList.get(index - 1).getDate());
                textEndP();
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println(G + "Fel format,prova igen!");
                textEndP();
                scanner.next();
                readUsersNote();
            }

        } else System.out.println("Du har inga artiklar att visa");
    }


    /**
     * Metoden som sparar användares inlägg
     * Try och Catch filtrerar fel format for inkommande values.
     * writeValue sparar users note i Json format.
     */
    static void writeUsersNote() {
        textSpace();
        scanner.nextLine();
        System.out.println("\nFylla i en titel till sitt inlägg ");
        String phrase1 = scanner.nextLine();
        System.out.println("Skriv sitt dagsboksinlägg");
        String phrase2 = scanner.nextLine();
        Note note = new Note(phrase1, phrase2, LocalDate.now().toString());
        noteList.add(note);
        try {
            mapper.writeValue(USER_LIST_JSON, usersList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        textEnd();
    }


    /**
     * Metoden som stänger programmet.
     */
    static void exit() {
        System.out.println(getColor()
                + "Hej då, " + getColor()
                + getColor() + "! vi ses !");
        textEnd();
        System.exit(0);
    }
}
