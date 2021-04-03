package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.piece.strategy.Direction;
import chess.domain.position.Position;
import chess.domain.result.Score;
import java.util.List;

public interface Piece {

    List<Direction> directions();

    Path pathFrom(final Direction direction, final Position position);

    boolean isDifferentColor(final Piece piece);

    boolean isSameColor(final Piece piece);

    boolean isColor(final Color color);

    boolean isPawn();

    boolean isKing();

    boolean isEmpty();

    String getName();

    Score score();

    String type();

    String colorName();
}
