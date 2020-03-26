package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.MoveType;

public class King extends Piece {

    public King(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(MoveType moveType) {
        return true;
    }

    @Override
    public String pieceName() {
        return teamStrategy.kingName();
    }
}
