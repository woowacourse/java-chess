package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public abstract class ColoredPiece implements Piece {
    protected final Position position;
    protected final TeamStrategy teamStrategy;

    public ColoredPiece(Position position, TeamStrategy teamStrategy) {
        this.position = position;
        this.teamStrategy = teamStrategy;
    }

    public boolean isEqualPosition(Position position) {
        return this.position.equals(position);
    }
}

