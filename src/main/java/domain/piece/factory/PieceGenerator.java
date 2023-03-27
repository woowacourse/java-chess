package domain.piece.factory;

import domain.piece.Camp;
import domain.piece.Piece;

@FunctionalInterface
public interface PieceGenerator {
    Piece apply(Camp camp);
}
