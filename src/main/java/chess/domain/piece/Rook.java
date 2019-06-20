package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook implements Piece {
    private static final List<DirectionType> directions;

    private final TeamType teamType;

    static {
        directions = Arrays.asList(
                DirectionType.UP,
                DirectionType.DOWN,
                DirectionType.LEFT,
                DirectionType.RIGHT
        );
    }

    public Rook(final TeamType teamType) {
        this.teamType = teamType;
    }

    @Override
    public boolean isSameTeam(Piece piece) {
        return this.teamType == ((Rook) piece).teamType;
    }

    @Override
    public List<Position> makePossiblePositions(Board board, Position source) {
        List<Position> positions = new ArrayList<>();

        for (DirectionType direction : directions) {
            Position nextPosition = source.hopNextPosition(direction);

            positions.addAll(makePossiblePositionsByDirection(board, direction, nextPosition));
        }
        return positions;
    }

    private List<Position> makePossiblePositionsByDirection(Board board, DirectionType direction, Position nextPosition) {
        List<Position> positions = new ArrayList<>();

        while (board.isMovablePosition(this, nextPosition)) {
            positions.add(nextPosition);
            nextPosition = nextPosition.hopNextPosition(direction);
        }
        return positions;
    }
}
