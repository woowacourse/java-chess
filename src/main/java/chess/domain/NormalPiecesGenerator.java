package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import java.util.ArrayList;
import java.util.List;

public class NormalPiecesGenerator implements PiecesGenerator {

    private final static List<PieceName> piecesNamesOrder = new ArrayList<>(List.of(
            PieceName.ROOK,
            PieceName.KNIGHT,
            PieceName.BISHOP,
            PieceName.QUEEN,
            PieceName.KING,
            PieceName.BISHOP,
            PieceName.KNIGHT,
            PieceName.ROOK
    ));

    @Override
    public List<Piece> generate() {
        List<Piece> pieces = new ArrayList<>();

        int i = 0;
        for (Column column : Column.values()) {
            pieces.add(Piece.of(new Position(column, Row.EIGHT), piecesNamesOrder.get(i)));
            pieces.add(Piece.of(new Position(column, Row.ONE), piecesNamesOrder.get(i++)));
        }

        for (Column column : Column.values()) {
            pieces.add(new Pawn(new Position(column, Row.TWO)));
            pieces.add(new Pawn(new Position(column, Row.SEVEN)));
        }
        return pieces;
    }
}
