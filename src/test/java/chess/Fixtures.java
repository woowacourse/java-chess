package chess;

import static chess.Color.BLACK;
import static chess.Color.WHITE;
import static chess.Column.A;
import static chess.Column.B;
import static chess.Column.C;
import static chess.Column.D;
import static chess.Column.E;
import static chess.Column.F;
import static chess.Column.G;
import static chess.Column.H;
import static chess.Row.EIGHT;
import static chess.Row.ONE;
import static chess.Row.SEVEN;
import static chess.Row.TWO;

import chess.board.Board;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public final class Fixtures {

    public static final Position A1 = new Position(Column.A, Row.ONE);
    public static final Position A2 = new Position(Column.A, Row.TWO);
    public static final Position A3 = new Position(Column.A, Row.THREE);
    public static final Position A4 = new Position(Column.A, Row.FOUR);
    public static final Position A5 = new Position(Column.A, Row.FIVE);
    public static final Position A6 = new Position(Column.A, Row.SIX);
    public static final Position A7 = new Position(Column.A, Row.SEVEN);
    public static final Position A8 = new Position(Column.A, Row.EIGHT);

    public static final Position B1 = new Position(Column.B, Row.ONE);
    public static final Position B2 = new Position(Column.B, Row.TWO);
    public static final Position B3 = new Position(Column.B, Row.THREE);
    public static final Position B4 = new Position(Column.B, Row.FOUR);
    public static final Position B5 = new Position(Column.B, Row.FIVE);
    public static final Position B6 = new Position(Column.B, Row.SIX);
    public static final Position B7 = new Position(Column.B, Row.SEVEN);
    public static final Position B8 = new Position(Column.B, Row.EIGHT);

    public static final Position C1 = new Position(Column.C, Row.ONE);
    public static final Position C2 = new Position(Column.C, Row.TWO);
    public static final Position C3 = new Position(Column.C, Row.THREE);
    public static final Position C4 = new Position(Column.C, Row.FOUR);
    public static final Position C5 = new Position(Column.C, Row.FIVE);
    public static final Position C6 = new Position(Column.C, Row.SIX);
    public static final Position C7 = new Position(Column.C, Row.SEVEN);
    public static final Position C8 = new Position(Column.C, Row.EIGHT);

    public static final Position D1 = new Position(Column.D, Row.ONE);
    public static final Position D2 = new Position(Column.D, Row.TWO);
    public static final Position D3 = new Position(Column.D, Row.THREE);
    public static final Position D4 = new Position(Column.D, Row.FOUR);
    public static final Position D5 = new Position(Column.D, Row.FIVE);
    public static final Position D6 = new Position(Column.D, Row.SIX);
    public static final Position D7 = new Position(Column.D, Row.SEVEN);
    public static final Position D8 = new Position(Column.D, Row.EIGHT);

    public static final Position E1 = new Position(Column.E, Row.ONE);
    public static final Position E2 = new Position(Column.E, Row.TWO);
    public static final Position E3 = new Position(Column.E, Row.THREE);
    public static final Position E4 = new Position(Column.E, Row.FOUR);
    public static final Position E5 = new Position(Column.E, Row.FIVE);
    public static final Position E6 = new Position(Column.E, Row.SIX);
    public static final Position E7 = new Position(Column.E, Row.SEVEN);
    public static final Position E8 = new Position(Column.E, Row.EIGHT);

    public static final Position F1 = new Position(Column.F, Row.ONE);
    public static final Position F2 = new Position(Column.F, Row.TWO);
    public static final Position F3 = new Position(Column.F, Row.THREE);
    public static final Position F4 = new Position(Column.F, Row.FOUR);
    public static final Position F5 = new Position(Column.F, Row.FIVE);
    public static final Position F6 = new Position(Column.F, Row.SIX);
    public static final Position F7 = new Position(Column.F, Row.SEVEN);
    public static final Position F8 = new Position(Column.F, Row.EIGHT);

    public static final Position G1 = new Position(Column.G, Row.ONE);
    public static final Position G2 = new Position(Column.G, Row.TWO);
    public static final Position G3 = new Position(Column.G, Row.THREE);
    public static final Position G4 = new Position(Column.G, Row.FOUR);
    public static final Position G5 = new Position(Column.G, Row.FIVE);
    public static final Position G6 = new Position(Column.G, Row.SIX);
    public static final Position G7 = new Position(Column.G, Row.SEVEN);
    public static final Position G8 = new Position(Column.G, Row.EIGHT);

    public static final Position H1 = new Position(Column.H, Row.ONE);
    public static final Position H2 = new Position(Column.H, Row.TWO);
    public static final Position H3 = new Position(Column.H, Row.THREE);
    public static final Position H4 = new Position(Column.H, Row.FOUR);
    public static final Position H5 = new Position(Column.H, Row.FIVE);
    public static final Position H6 = new Position(Column.H, Row.SIX);
    public static final Position H7 = new Position(Column.H, Row.SEVEN);
    public static final Position H8 = new Position(Column.H, Row.EIGHT);
    public static final Board generalBoard = new Board(makeGeneralBoard());

    private Fixtures() {
    }

    private static Map<Position, Piece> makeGeneralBoard() {
        Map<Position, Piece> board = new HashMap<>();
        for (Column column : Column.values()) {
            board.put(new Position(TWO, column), new Pawn(WHITE));
            board.put(new Position(SEVEN, column), new Pawn(BLACK));
        }
        board.put(new Position(ONE, A), new Rook(WHITE));
        board.put(new Position(ONE, H), new Rook(WHITE));
        board.put(new Position(EIGHT, A), new Rook(BLACK));
        board.put(new Position(EIGHT, H), new Rook(BLACK));

        board.put(new Position(ONE, B), new Knight(WHITE));
        board.put(new Position(ONE, G), new Knight(WHITE));
        board.put(new Position(EIGHT, B), new Knight(BLACK));
        board.put(new Position(EIGHT, G), new Knight(BLACK));

        board.put(new Position(ONE, C), new Bishop(WHITE));
        board.put(new Position(ONE, F), new Bishop(WHITE));
        board.put(new Position(EIGHT, C), new Bishop(BLACK));
        board.put(new Position(EIGHT, F), new Bishop(BLACK));

        board.put(new Position(ONE, D), new Queen(WHITE));
        board.put(new Position(EIGHT, D), new Queen(BLACK));

        board.put(new Position(ONE, E), new King(WHITE));
        board.put(new Position(EIGHT, E), new King(BLACK));

        return board;
    }
}
