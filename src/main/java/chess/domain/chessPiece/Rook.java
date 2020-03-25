package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.MoveFactory;
import chess.domain.movefactory.Straight;

public class Rook extends Piece {
    public Rook(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }


    @Override
    public boolean isMovable(Position target, Piece targetPiece) {
        validSameTeam(targetPiece);
        return MoveFactory.of(position, target) instanceof Straight;
    }

    @Override
    public String pieceName() {
        return teamStrategy.rookName();
    }
}
