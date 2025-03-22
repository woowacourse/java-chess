package chess;

import chess.piece.Board;
import chess.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.function.Supplier;

public class ChessApplication {

    public static void main(String[] args) {
        final Board board = Board.emptyBoard();
        board.gameSetUp();
        tryCatchLoop(() -> gameStart(board));
    }

    public static void gameStart(final Board board) {
        OutputView.printBoard(board.getMapView());
        final Position startPosition = tryCatchLoop(InputView::readStartPosition);
        final Position endPosition = tryCatchLoop(InputView::readEndPosition);
        final Piece targetPiece = board.move(startPosition, endPosition);
        OutputView.printEndPiece(targetPiece);
        gameStart(board);
    }

    private static void tryCatchLoop(final Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            runnable.run();
        }
    }

    private static <T> T tryCatchLoop(Supplier<T> callBack) {
        try {
            return callBack.get();
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return tryCatchLoop(callBack);
        }
    }

}
