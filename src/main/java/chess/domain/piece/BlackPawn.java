package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackPawn extends Pawn {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.SOUTH,
                    Direction.SE,
                    Direction.SW
            )
    );

    public BlackPawn(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    public List<Position> getPossiblePositions(Board board) {
        List<Position> possiblePositions = new ArrayList<>();

        if (representation == 'P') {
            for (Direction direction : DIRECTIONS) {
                Position nextPosition = direction.move(position);

                if (isForwardDirection(direction, Direction.SOUTH)) {
                    if (isInBoardRange(nextPosition) && board.isBlank(nextPosition)) {
                        possiblePositions.add(nextPosition);
                        if (isFirstMove(7)) {
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
