package chess.domain.chessPiece;

import chess.domain.chessPiece.team.TeamStrategy;

public abstract class ColoredPiece implements Piece {
    protected final TeamStrategy teamStrategy;

    public ColoredPiece(TeamStrategy teamStrategy) {
        this.teamStrategy = teamStrategy;
    }
}
