package chess;

import chess.domain.Board;
import chess.domain.utils.InputParser;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        OutputView.start();
        Board board = Board.init();
        OutputView.command();
        String command;
        do {
            command = InputView.command();
            String[] splitInput = command.split(" ");
            if (splitInput.length > 1) {
                board.move(InputParser.position(splitInput[1]), InputParser.position(splitInput[2]));
            }
            OutputView.board(board);
        } while (!command.equals("end"));
    }
}
