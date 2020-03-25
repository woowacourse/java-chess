package domain.pieces;

import domain.pieces.exceptions.PiecesException;
import domain.point.Point;

import java.util.HashSet;
import java.util.Set;

public class Pieces {

    private Set<Piece> pieces;

    public Pieces(Set<Piece> pieces) {
        this.pieces = new HashSet<>(pieces);
    }

    public static Pieces of(PiecesFactory piecesFactory) {
        return new Pieces(piecesFactory.getInstance());
    }

    public void move(Point before, Point after) {
        Piece selected = pieces.stream()
            .filter(piece -> piece.getPoint().equals(before))
            .findFirst()
            .orElseThrow(() -> new PiecesException("움직이고자 하는 말이 없습니다."));

        Piece target = pieces.stream()
            .filter(piece -> piece.getPoint().equals(after))
            .findFirst()
            .orElse(null);

        MoveType moveType = MoveType.getInstance(before, after);

        if (moveType == MoveType.STRAIGHT)

        if (target == null) {
            pieces.remove(selected);
            pieces.add(selected.move(after));

        }


    }
}
