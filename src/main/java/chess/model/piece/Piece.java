package chess.model.piece;

import chess.model.Board;
import chess.model.square.Direction;
import chess.model.square.Square;
import java.util.List;

public interface Piece {

    boolean isBlack();

    String name();

    boolean movable(Square source, Square target);

    boolean movable(Board board, Square source, Square target);

    boolean canMoveWithoutObstacle(Board board, Square source, Square target);

    List<Direction> getDirection();

    boolean isNotAlly(Piece target);

    boolean isNotEmpty();

    double getPoint();

    boolean isPawn();

    boolean isKing();

    boolean isSameColor(Color color);

    Color color();
}
