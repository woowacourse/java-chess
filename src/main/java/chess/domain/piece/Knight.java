package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.exception.IllegalMoveException;

import java.util.Arrays;
import java.util.List;

import static chess.domain.Direction.*;

public class Knight extends Piece {
    private static List<Direction> possibleDirections = Arrays.asList(NNE, NEE, NNW, NWW, SSE, SEE, SSW, SWW);

    public Knight(Position position, Team team) {
        super(position, team);
        this.representation = 'N';
        this.score = 2.5;
    }

    @Override
    protected void validateMove(Position destination) {
        Direction direction = position.calculateDirection(destination);
        if (!possibleDirections.contains(direction)) {
            throw new IllegalMoveException(ILLEGAL_MOVE);
        }
    }

    @Override
    public void validateDestination(Position destination, Piece destinationPiece, List<Piece> piecesInBetween) {
    }
}
