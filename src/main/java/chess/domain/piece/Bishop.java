package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NE,
                    Direction.SE,
                    Direction.SW,
                    Direction.NW
            )
    );

    public Bishop(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    public List<Position> getPossiblePositions(Board board) {
        List<Position> possiblePositions = new ArrayList<>();
        for (Direction direction : DIRECTIONS) {
            Position nextPosition = direction.move(position);

            while (isInBoardRange(nextPosition) && board.isBlank(nextPosition)) {
                possiblePositions.add(nextPosition);
                nextPosition = direction.move(nextPosition);
            }
            if (isInBoardRange(nextPosition) && board.isOtherTeam(position, nextPosition)) {
                possiblePositions.add(nextPosition);
            }
        }
        return possiblePositions;
    }

    private boolean isInBoardRange(Position nextPosition) {
        return nextPosition.getX() <= 8 && nextPosition.getX() >= 1 &&
                nextPosition.getY() <= 8 && nextPosition.getY() >= 1;
    }
}
