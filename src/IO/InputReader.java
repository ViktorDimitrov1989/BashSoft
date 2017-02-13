package IO;

import StaticData.SessionData;
import java.util.Scanner;


public class InputReader {
    private static final String END_COMMAND = "quit";

    public static void readCommands() {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        while (input != END_COMMAND) {
            CommandInterpreter.interpretCommand(input);
            OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));

            input = sc.nextLine();
        }

    }

}
