package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.CrossType;
import chess.domain.movefactory.MoveType;

public class Bishop extends Piece {
    public Bishop(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(MoveType moveType) {
        return moveType instanceof CrossType;
    }

    @Override
    public String pieceName() {
        return teamStrategy.bishopName();
    }
}
