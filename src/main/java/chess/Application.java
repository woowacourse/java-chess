package chess;

import chess.domain.ChessBoard;
import chess.domain.Commands;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        run();
    }

    private static void runUntilValid(ChessBoard chessBoard) {
        try {
            runByCommands(chessBoard);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            OutputView.printBoard(chessBoard.getChessBoard());
            runUntilValid(chessBoard);
        }
    }

    private static void run() {
        OutputView.printInitMessage();
        final ChessBoard chessBoard = new ChessBoard();
        while (chessBoard.isPlaying()) {
            runUntilValid(chessBoard);
        }
        OutputView.printScore(chessBoard.result());
    }

    private static void runByCommands(ChessBoard chessBoard) {
        List<String> command = InputView.command();
        Commands.run(chessBoard, command);
    }


}
