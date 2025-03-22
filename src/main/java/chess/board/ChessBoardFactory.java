package chess.board;

import static chess.board.Column.A;
import static chess.board.Column.B;
import static chess.board.Column.C;
import static chess.board.Column.D;
import static chess.board.Column.E;
import static chess.board.Column.F;
import static chess.board.Column.G;
import static chess.board.Column.H;
import static chess.board.Row.EIGHT;
import static chess.board.Row.ONE;
import static chess.board.Row.SEVEN;
import static chess.board.Row.TWO;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardFactory {

    public static ChessBoard createChessBoard() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.putAll(getStartingPositionByColor(Color.WHITE));
        boardMap.putAll(getStartingPositionByColor(Color.BLACK));
        return new ChessBoard(boardMap);
    }

    private static Map<Position, Piece> getStartingPositionByColor(Color color) {
        if (color == Color.WHITE) {
            return createStartingPosition(color, ONE, TWO);
        }
        return createStartingPosition(color, EIGHT, SEVEN);
    }

    private static Map<Position, Piece> createStartingPosition(Color color, Row remainRow, Row pawnRow) {
        return new HashMap<>() {{
            put(new Position(remainRow, A), new Rook(color));
            put(new Position(remainRow, B), new Knight(color));
            put(new Position(remainRow, C), new Bishop(color));
            put(new Position(remainRow, D), new Queen(color));
            put(new Position(remainRow, E), new King(color));
            put(new Position(remainRow, F), new Bishop(color));
            put(new Position(remainRow, G), new Knight(color));
            put(new Position(remainRow, H), new Rook(color));

            put(new Position(pawnRow, A), new Pawn(color));
            put(new Position(pawnRow, B), new Pawn(color));
            put(new Position(pawnRow, C), new Pawn(color));
            put(new Position(pawnRow, D), new Pawn(color));
            put(new Position(pawnRow, E), new Pawn(color));
            put(new Position(pawnRow, F), new Pawn(color));
            put(new Position(pawnRow, G), new Pawn(color));
            put(new Position(pawnRow, H), new Pawn(color));
        }};
    }
}
