package view;

import controller.command.Command;
import controller.command.Continue;
import controller.command.End;
import controller.command.Move;
import controller.command.Search;
import controller.command.Start;
import controller.command.Status;
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
        if (userInput.equals("search")) {
            return new Search();
        }
        if (userInput.equals("end")) {
            return new End();
        }
        if (userInput.equals("status")) {
            return new Status();
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

    public String requestUserName() {
        System.out.println("사용자 이름을 입력하세요.");
        return SCANNER.nextLine();
    }

    public String requestGameTitle() {
        System.out.println("게임 제목을 입력하세요.");
        return SCANNER.nextLine();
    }

    public String requestGameId() {
        System.out.println("입장을 원하는 게임의 ID를 입력하세요.");
        return SCANNER.nextLine();
    }

    public Command requestStartCommand() {
        System.out.println("다시 시작을 원하면 continue, 아니라면 end를 입력해주세요.");
        String startCommandInput = SCANNER.nextLine();
        if (startCommandInput.equals("continue")) {
            return new Continue();
        }
        if (startCommandInput.equals("end")) {
            return new End();
        }
        System.out.println("잘못된 입력입니다.");
        System.out.println();
        return requestStartCommand();
    }
}
