package controller;

import static view.InputView.END_COMMAND;
import static view.InputView.MOVE_COMMAND;
import static view.InputView.START_COMMAND;

import domain.ChessTable;
import domain.Square;
import java.util.List;
import view.InputView;
import view.OutputView;

public class ChessController {

    public static final int COMMAND_INDEX = 0;
    public static final int MOVE_SOURCE_INDEX = 1;
    public static final int MOVE_TARGET_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printHeader();
        ChessTable chessTable = new ChessTable();

        while (true) {
            final List<String> input = inputView.readCommand();
            final String command = input.get(COMMAND_INDEX);

            if (END_COMMAND.equals(command)) {
                break;
            }
            if (START_COMMAND.equals(command)) {
                chessTable = ChessTable.create();
                outputView.printChessTable(chessTable.getPieceSquares());
                continue;
            }
            if (MOVE_COMMAND.equals(command)) {
                final Square source = Square.from(input.get(MOVE_SOURCE_INDEX));
                final Square target = Square.from(input.get(MOVE_TARGET_INDEX));

                chessTable.move(source, target);
                outputView.printChessTable(chessTable.getPieceSquares());
            }
        }
    }


}
