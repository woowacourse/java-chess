package domain;

import java.util.Map;
import java.util.function.Predicate;

import domain.piece.Piece;
import domain.piece.PieceType;

public class KingFinder {

    public boolean isExistKing(Map<Square, Piece> pieceLocations, Predicate<Piece> predicate) {
        return pieceLocations.values().stream()
                .filter(s -> s.pieceType() == PieceType.KING)
                .filter(predicate)
                .findAny()
                .isPresent();
    }

}
