package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.MoveFactory;
import chess.domain.movefactory.StraightType;

public class Rook extends Piece {
    public Rook(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }


    @Override
    public boolean isMovable(Piece targetPiece, Position targetPosition) {
        return MoveFactory.of(this.position, targetPosition) instanceof StraightType;
    }

    @Override
    public String pieceName() {
        return teamStrategy.rookName();
    }
}
