package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhitePawn extends Pawn {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NORTH,
                    Direction.NE,
                    Direction.NW
            )
    );

    public WhitePawn(char representation, Team team, Position position) {
        super(representation, team, position);
    }


    @Override
    public List<Position> getPossiblePositions(Board board) {
        List<Position> possiblePositions = new ArrayList<>();

        if (representation == 'p') {
            for (Direction direction : DIRECTIONS) {
                Position nextPosition = direction.move(position);

                if (isForwardDirection(direction, Direction.NORTH)) {
                    if (isInBoardRange(nextPosition) && board.isBlank(nextPosition)) {
                        possiblePositions.add(nextPosition);
                        if (isFirstMove(2)) {
                            possiblePositions.add(direction.move(nextPosition));
                        }
                    }
                }

                if (board.isOtherTeam(position, nextPosition)) {
                    possiblePositions.add(nextPosition);
                }
            }
        }
        return possiblePositions;
    }
}
