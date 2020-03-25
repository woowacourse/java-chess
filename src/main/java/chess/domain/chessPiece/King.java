package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public class King extends Piece {

    public King(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }

    @Override
    public String pieceName() {
        return teamStrategy.kingName();
    }
}
