package chess;

import chess.domain.ChessBoard;
import chess.domain.Commands;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        OutputView.printInitMessage();
        final ChessBoard chessBoard = new ChessBoard();
        while (chessBoard.isPlaying()) {
            List<String> command = InputView.command();
            Commands.run(chessBoard, command);
        }
        OutputView.printScore(chessBoard.result());
    }


}
