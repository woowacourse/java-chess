package chess.domain.chessPiece;

import chess.domain.MoveType;
import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public class Rook extends Piece {
    public Rook(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
    }


    @Override
    public boolean isMovable(Position target, Piece targetPiece) {
        if (MoveType.of(position, target) == MoveType.STRAIGHT) {
            return true;
        }
        return false;
    }

    @Override
    public String pieceName() {
        return teamStrategy.rookName();
    }
}
