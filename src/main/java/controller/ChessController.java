package controller;

import static view.InputView.END_COMMAND;
import static view.InputView.MOVE_COMMAND;
import static view.InputView.START_COMMAND;

import domain.ChessTable;
import java.util.List;
import view.InputView;
import view.OutputView;

public class ChessController {

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
            final String command = input.get(0);

            if (START_COMMAND.equals(command)) {
                // TODO : init 고려
                chessTable = ChessTable.create();
                outputView.printChessTable(chessTable.getPieceContainer());
                continue;
            }
            if (MOVE_COMMAND.equals(command)) {
                final String source = input.get(1);
                final String target = input.get(2);

                chessTable.move(source, target);
                continue;
            }
            if (END_COMMAND.equals(command)) {
                break;
            }
        }
    }


}
