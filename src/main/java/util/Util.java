package util;
import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Class Unit innehåller metoder för Huvudmenu utseende.
 */
public class Util {
    static public final String O = "\u001B[31m";
    static public final String G = "\u001B[33m";
    static public final String C = "\u001B[34m";
    static public final String D = "\u001B[39m";
    static public final String R = "\u001b[0m";
    static public final File USER_LIST_JSON = Paths.get("userList.json").toFile();

    /**
     * Metoden som visa  separerande stjärnor.
     */
    static public void textSpace() {
        System.out.println(D);
        System.out.println("********DAGBOK*******\n");
    }

    static public void textEnd() {
        System.out.println(D);
        System.out.println("*********END*******\n");
    }

    static public void textEndP() {
        System.out.println(D);
        System.out.println("--------------------\n");
    }

    /**
     * Metoden som visa olika färger för texten.
     * @return ny färg.
     */
    static public String getColor() {
        String mColors[] = new String[]{"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001b[38m", "\u001b[34m", "\u001b[35m", "\u001b[36m", "\u001b[37m"};
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);
        String color = mColors[randomNumber];
        return color;
    }

    /**
     * Metoden som visa välkommande text.
     */
    static public void optionsMainMenuShowText() {
        System.out.print
                (" ________________\n" +
                        "|                |\n" +
                        "|" + C + "  M " + O + " E " + G + " N " + D + " U    |\n" +
                        "|________________|\n"
                );
        System.out.println(" \n\nSkapa nytt inlägg :"
                + G + "1" + R
                + "\n\nLäsa de inlägg som finns i dagboken :"
                + C + "2" + R + "\n\nAvsluta programmet :"
                + O + "3" + R);
    }

    /**
     * Metoden som hälsar användare.
     */
    static public void helloMessageToUser() {
        System.out.print
                (
                        " _____________________\n" +
                                "|                     |\n" +
                                "|" + O + " W " + G + " E " + C + " L " + D + " C " + O + " O " + G + " M " + D + " E |\n" +
                                "|_____________________|\n"
                );
        textSpace();
    }

    /**
     * Metoden som räkna antalet av användare
     * @param y inkommande antalet av användare from JSON
     */
    static public void userListExistControll(int y) {
        if (y < 1) {
            System.out.println("Du har inga användare att välja");
        } else System.out.println(R + "\nUsers att välja \n  " + y);

    }

}
