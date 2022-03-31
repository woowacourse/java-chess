package chess.domain.game.state;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;
import java.util.Map;

public interface State {

    State start();

    State move(Coordinate from, Coordinate to);

    State end();

    Map<Coordinate, Piece> getValue();

    boolean isFinished();
}
