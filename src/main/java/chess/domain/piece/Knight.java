package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NNE,
                    Direction.EEN,
                    Direction.EES,
                    Direction.SSE,
                    Direction.SSW,
                    Direction.WWS,
                    Direction.WWN,
                    Direction.NNW
            )
    );

    public Knight(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    public List<Position> getPossiblePositions(Board board) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : DIRECTIONS) {
            Position nextPosition = direction.move(position);
            if (isInBoardRange(nextPosition) && board.isBlank(nextPosition)) {
                possiblePositions.add(nextPosition);
            }

            if (board.isOtherTeam(position, nextPosition)) {
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
