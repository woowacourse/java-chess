package chess.ui;

import chess.domain.UserInterface;
import chess.domain.position.MovingFlow;
import chess.view.InputView;

import java.util.Scanner;

public class Console implements UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMETER = " ";
    private static final int MOVE_COMMAND_INDEX = 0;
    private static final int FROM_INDEX = 1;
    private static final int TO_INDEX = 2;

    @Override
    public MovingFlow inputMovingFlow() {
        InputView.printMoveRequest();
        String input = scanner.nextLine();
        String[] words = input.split(DELIMETER);
        //todo: refac
        Command.of(words[MOVE_COMMAND_INDEX]);
        return MovingFlow.of(words[FROM_INDEX], words[TO_INDEX]);
    }

    @Override
    public Command inputStart() {
        InputView.printStart();
        String input = scanner.nextLine();
        //todo: refac
        return Command.of(input);
    }


}
