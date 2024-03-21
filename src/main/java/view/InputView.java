package view;

import static domain.EndCommand.END_COMMAND;
import static domain.StartCommand.START_COMMAND;

import domain.Command;
import domain.MoveCommand;
import java.util.Scanner;

public class InputView {
    public static Command readStartOrEnd() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("start") && !input.equals("end")) {
            System.out.println("다시 입력해 주세요");
            input = scanner.nextLine();
        }
        if (input.equals("end")) {
            return END_COMMAND;
        }
        return START_COMMAND;
    }

    public static Command readEndOrMove() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.matches("move [a-h][1-8] [a-h][1-8]") && !input.equals("end")) {
            System.out.println("다시 입력해 주세요");
            input = scanner.nextLine();
        }
        if (input.equals("end")) {
            return END_COMMAND;
        }
        String options = input.substring(5);
        String[] splitOptions = options.split(" ");
        return new MoveCommand(splitOptions);
    }
}
