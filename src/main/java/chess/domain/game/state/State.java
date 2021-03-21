package chess.domain.game.state;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public interface State {
    State start();

    State move(Position source, Position target);

    State end();

    boolean isNotFinished();

    List<Map<Position, Piece>> squares();
}
