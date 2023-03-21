package view;

import controller.command.Command;
import controller.command.End;
import controller.command.Move;
import controller.command.Start;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner SCANNER = new Scanner(System.in);

    public Command requestUserCommand() {
        String userInput = SCANNER.nextLine();
        if (userInput.equals("start")) {
            return new Start();
        }
        if (userInput.equals("end")) {
            return new End();
        }
        return convertUserInputToMoveCommand(userInput);
    }

    private Command convertUserInputToMoveCommand(String userInput) {
        List<String> userInputWords = Arrays.asList(userInput.split(" "));
        if (userInputWords.get(0).equals("move")) {
            return getMoveCommandBy(userInputWords);
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private Move getMoveCommandBy(List<String> userInputWords) {
        try {
            return Move.of(userInputWords.get(1), userInputWords.get(2));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("올바른 움직임을 입력해주세요. 예) move b2 b4");
        }
    }

}
