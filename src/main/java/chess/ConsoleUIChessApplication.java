package chess;

import chess.domain.ChessBoard;
import chess.domain.utils.InputParser;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        OutputView.start();
        ChessBoard chessboard = new ChessBoard();
        OutputView.command();
        String command;
        boolean endFlag = true;
        do {
            command = InputView.command();
            endFlag = !command.equals("end") && !command.equals("status");
            String[] splitInput = command.split(" ");
            if (splitInput.length > 1) {
                endFlag = chessboard.move(InputParser.position(splitInput[1]), InputParser.position(splitInput[2]));
                OutputView.board(chessboard);
            }
        } while (endFlag);
        OutputView.result(chessboard);
    }
}
