package chess.domain.piece.strategy;

import chess.domain.board.Path;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;

public interface PieceStrategy {

    List<Direction> directions();

    Path pathFrom(final Direction direction, final Position position);

    boolean canReplace(final Piece piece);

    boolean blockedBy(final Piece piece);

    boolean isPawn();

    boolean isKing();

    double score();
}
