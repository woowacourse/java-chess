package chess.domain.piece.strategy;

import java.util.List;

public interface PieceStrategy {

    List<Direction> directions();

    double score();

    boolean isPawn();

    boolean isKing();
}
