package chess.domain.piece.state.started;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.state.PieceState;

public class StartedKnight extends NonContinuous {

    @Override
    public List<Position> findMovablePositions(Position source, Map<Position, Piece> board) {
        return findMovablePositions(directions(), source, board);
    }

    @Override
    public PieceState updateState() {
        return new StartedKnight();
    }

    private static List<Direction> directions() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UpUpRight);
        directions.add(Direction.UpRightRight);
        directions.add(Direction.DownRightRight);
        directions.add(Direction.DownDownRight);
        directions.add(Direction.DownDownLeft);
        directions.add(Direction.DownLeftLeft);
        directions.add(Direction.UpLeftLeft);
        directions.add(Direction.UpUpLeft);
        return directions;
    }
}
