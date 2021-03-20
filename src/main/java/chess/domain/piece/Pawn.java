package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import java.util.List;

public final class Pawn extends Piece {

    public Pawn(TeamColor teamColor, Position position) {
        super(new Details("p", teamColor, Score.from(1), false),
            new Directions(Direction.forwardDirection(teamColor),
                Direction.forwardDiagonal(teamColor)),
            position);
    }

    @Override
    public void updateMovablePositions(List<Position> existPiecePositions,
        List<Position> enemiesPositions) {
        super.updateMovablePositions(existPiecePositions, enemiesPositions);
        if (isNotMoved()) {
            directions().pawnAdditionalPosition(existPiecePositions, currentPosition())
                .ifPresent(position -> movablePositions().add(position));
        }
    }
}
