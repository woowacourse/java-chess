package chess.domain.piece.type;

import chess.domain.MultiDirection;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    private static final int DEFAULT_STEP = 1;

    public King(final Color color) {
        super(color);
    }

//    public King(final Color color, final Position position) {
//        super(color, position);
//    }
//
//    @Override
//    public boolean canMoveTo(final Position target) {
//        MultiDirection multiDirection = MultiDirection.of(source, target);
//        int rankDistance = source.getRankDistance(target);
//        int fileDistance = source.getFileDistance(target);
//
//        if (multiDirection == MultiDirection.VERTICAL && rankDistance == DEFAULT_STEP) {
//            return true;
//        }
//        if (multiDirection == MultiDirection.HORIZONTAL && fileDistance == DEFAULT_STEP) {
//            return true;
//        }
//        return (multiDirection == MultiDirection.LEFT_DIAGONAL || multiDirection == MultiDirection.RIGHT_DIAGONAL) && rankDistance == DEFAULT_STEP;
//    }
//
//    @Override
//    public Set<Position> getRoute(final Position target) {
//        return new HashSet<>();
//    }

    @Override
    public boolean canMoveTo(final Position source, final Position target) {
        MultiDirection multiDirection = MultiDirection.of(source, target);
        int rankDistance = source.getRankDistance(target);
        int fileDistance = source.getFileDistance(target);

        if (multiDirection == MultiDirection.VERTICAL && rankDistance == DEFAULT_STEP) {
            return true;
        }
        if (multiDirection == MultiDirection.HORIZONTAL && fileDistance == DEFAULT_STEP) {
            return true;
        }
        return (multiDirection == MultiDirection.LEFT_DIAGONAL || multiDirection == MultiDirection.RIGHT_DIAGONAL) && rankDistance == DEFAULT_STEP;
    }

    @Override
    public Set<Position> getRoute(final Position source, final Position target) {
        return new HashSet<>();
    }
}
