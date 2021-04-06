package chess.domain.piece;

import chess.domain.PieceDirection;
import chess.domain.Position;
import chess.domain.PositionInformation;
import chess.domain.Score;
import chess.domain.TeamColor;
import java.util.List;

public final class Pawn extends Piece {

    public Pawn(TeamColor teamColor, Position position) {
        super(new PieceDetails("pawn", teamColor, Score.from(1), false),
            new AvailableDirections(PieceDirection.forwardDirection(teamColor),
                PieceDirection.forwardDiagonal(teamColor)),
            position);
    }

    @Override
    public void updateMovablePositions(List<PositionInformation> existPiecePositions) {
        super.updateMovablePositions(existPiecePositions);
        if (isNotMoved()) {
            directions().pawnAdditionalPosition(existPiecePositions, currentPosition())
                .ifPresent(position -> movablePositions().add(position));
        }
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
