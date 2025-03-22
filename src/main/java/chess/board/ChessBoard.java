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

public final class ChessBoard {

    private final Map<Position, Piece> boardMap;

    public ChessBoard() {
        this.boardMap = new HashMap<>();
        boardMap.putAll(getStartingPositionByColor(Color.WHITE));
        boardMap.putAll(getStartingPositionByColor(Color.BLACK));
    }

    private Map<Position, Piece> getStartingPositionByColor(Color color) {
        if (color == Color.WHITE) {
            return createStartingPosition(ONE, TWO);
        }
        return createStartingPosition(EIGHT, SEVEN);
    }

    private Map<Position, Piece> createStartingPosition(Row remainRow, Row pawnRow) {
        return new HashMap<>() {{
            put(new Position(remainRow, A), new Rook());
            put(new Position(remainRow, B), new Knight());
            put(new Position(remainRow, C), new Bishop());
            put(new Position(remainRow, D), new Queen());
            put(new Position(remainRow, E), new King());
            put(new Position(remainRow, F), new Bishop());
            put(new Position(remainRow, G), new Knight());
            put(new Position(remainRow, H), new Rook());

            put(new Position(pawnRow, A), new Pawn());
            put(new Position(pawnRow, B), new Pawn());
            put(new Position(pawnRow, C), new Pawn());
            put(new Position(pawnRow, D), new Pawn());
            put(new Position(pawnRow, E), new Pawn());
            put(new Position(pawnRow, F), new Pawn());
            put(new Position(pawnRow, G), new Pawn());
            put(new Position(pawnRow, H), new Pawn());
        }};
    }

    //1. 초기화

}
