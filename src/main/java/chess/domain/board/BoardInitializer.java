package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.ArrayList;
import java.util.List;

public class BoardInitializer {

    private static final List<Piece> BASE_PIECES = new ArrayList<>();

    static {
        setUpWithOutPawn();
        setUpPawn();
    }

    private BoardInitializer() {
    }

    private static void setUpWithOutPawn() {
        setUpWithOutPawnByColor(Color.WHITE, Row.ONE);
        setUpWithOutPawnByColor(Color.BLACK, Row.EIGHT);
    }

    private static void setUpWithOutPawnByColor(Color color, Row row) {
        BASE_PIECES.add(new Rook(color, new Position(Column.A, row)));
        BASE_PIECES.add(new Knight(color, new Position(Column.B, row)));
        BASE_PIECES.add(new Bishop(color, new Position(Column.C, row)));
        BASE_PIECES.add(new Queen(color, new Position(Column.D, row)));
        BASE_PIECES.add(new King(color, new Position(Column.E, row)));
        BASE_PIECES.add(new Bishop(color, new Position(Column.F, row)));
        BASE_PIECES.add(new Knight(color, new Position(Column.G, row)));
        BASE_PIECES.add(new Rook(color, new Position(Column.H, row)));
    }

    private static void setUpPawn() {
        for (Column column : Column.orderedValues()) {
            BASE_PIECES.add(new Pawn(Color.WHITE, new Position(column, Row.TWO)));
            BASE_PIECES.add(new Pawn(Color.BLACK, new Position(column, Row.SEVEN)));
        }
    }

    public static List<Piece> init() {
        return new ArrayList<>(BASE_PIECES);
    }
}
