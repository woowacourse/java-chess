package chess.view;

import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import chess.domain.piece.Piece;

public class OutputView {

    public static void printBoard(Game game) {
        Board board = game.getBoard();
        for (YPosition yPosition : YPosition.values()) {
            printXAxis(board, yPosition);
        }
    }

    private static void printXAxis(Board board, YPosition yPosition) {
        for (XPosition xPosition : XPosition.values()) {
            Position position = Position.of(xPosition, yPosition);
            Piece piece = board.pieceAtPosition(position);
            System.out.print(piece.getSymbol());
        }
        System.out.println();
    }
}
