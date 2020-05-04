package chess.ui;

import chess.domain.position.MovingFlow;
import chess.view.InputView;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMETER = " ";
    private static final int MOVE_COMMAND_INDEX = 0;
    private static final int FROM_INDEX = 1;
    private static final int TO_INDEX = 2;

    public MovingFlow inputMovingFlow() {
        InputView.printMoveRequest();
        String input = scanner.nextLine();
        String[] words = input.split(DELIMETER);
        Command command = Command.of(words[MOVE_COMMAND_INDEX]);
        if (command.isNotMove()) {
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
        return MovingFlow.of(words[FROM_INDEX], words[TO_INDEX]);
    }

    public Command inputStart() {
        InputView.printStart();
        String input = scanner.nextLine();
        return Command.of(input);
    }

    public Command inputStatus() {
        InputView.printResultRequest();
        String input = scanner.nextLine();
        return Command.of(input);
    }

}
