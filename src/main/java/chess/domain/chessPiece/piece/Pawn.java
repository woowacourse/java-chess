package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.MoveType;

public class Pawn extends Piece {
    private final Position startPosition;

    public Pawn(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
        startPosition = position;
    }

    @Override
    public boolean isMovable(MoveType moveType) {
        return true;
    }

    @Override
    public String pieceName() {
        return teamStrategy.pawnName();
    }
}
