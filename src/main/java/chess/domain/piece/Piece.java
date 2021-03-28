package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.piece.strategy.Direction;
import chess.domain.position.Position;
import java.util.List;

public interface Piece {

    List<Direction> directions();

    Path pathFrom(final Direction direction, final Position position);

    boolean canReplace(final Piece piece);

    boolean blockedBy(final Piece piece);

    boolean isColor(final PieceColor color);

    boolean isPawn();

    boolean isKing();

    boolean isEmpty();
}
