package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;
import chess.exception.OutOfBoardRangeException;

import java.util.ArrayList;
import java.util.List;

public abstract class MultipleStep extends Piece {
    public MultipleStep(char representation, Team team, Position position, PieceType pieceType) {
        super(representation, team, position, pieceType);
    }

    @Override
    public List<Position> getPossiblePositions(Board board) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : getDirections()) {
            try {
                Position nextPosition = direction.move(position);

                while (isInBoardRange(nextPosition) && board.isBlank(nextPosition)) {
                    possiblePositions.add(nextPosition);
                    nextPosition = direction.move(nextPosition);
                }

                if (isInBoardRange(nextPosition) && board.isOtherTeam(position, nextPosition)) {
                    possiblePositions.add(nextPosition);
                }
            } catch (OutOfBoardRangeException ignored) {
            }
        }
        return possiblePositions;
    }

    protected abstract List<Direction> getDirections();
}
