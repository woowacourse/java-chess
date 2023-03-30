package chess.domain.piece;

import chess.domain.Price;
import chess.domain.board.Board;
import chess.domain.position.Position;
import java.util.List;

public interface Piece {

    List<Position> findMovablePosition(final Position source, final Board board);

    String name();

    Type type();

    Side side();

    Price price();

    boolean isPawn();

    void changePawnMoveState();
}
