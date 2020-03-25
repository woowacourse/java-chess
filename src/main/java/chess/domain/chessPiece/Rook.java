package chess.domain.chessPiece;

import chess.domain.MoveRule;
import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public class Rook extends Piece {
    public Rook(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(Position source, Position target) {

        MoveRule.validateStraightMove(source, target);
        return false;
    }

    @Override
    public String pieceName() {
        return teamStrategy.rookName();
    }
}
