package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.piece.strategy.Direction;
import chess.domain.position.Position;
import java.util.List;

public interface Piece {

    List<Direction> directions();

    Path pathFrom(Direction direction, Position position);

    boolean isColor(PieceColor color);

    boolean isPawn();

    boolean isKing();

    boolean isEmpty();
}
