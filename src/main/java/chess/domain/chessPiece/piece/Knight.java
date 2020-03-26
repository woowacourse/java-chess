package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.MoveType;

public class Knight extends Piece {

    public Knight(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }

    @Override
    public boolean isMovable(MoveType moveType) {
        return true;
        //TODO 미구현 ;
    }

    @Override
    public String pieceName() {
        return teamStrategy.knightName();
    }
}
