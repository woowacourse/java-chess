package chess;

import chess.piece.Board;
import chess.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {

    public static void main(String[] args) {
        final Board board = Board.emptyBoard();
        board.gameSetUp();
        tryCatchLoop(() -> gameStart(board));
    }

    public static void gameStart(final Board board) {
        OutputView.printBoard(board.getMapView());
        final Position startPosition = InputView.tryCatchLoop(InputView::readStartPosition);
        final Position endPosition = InputView.tryCatchLoop(InputView::readEndPosition);
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

}
