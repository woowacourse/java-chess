package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public abstract class Piece implements PieceAbility {
    protected Position position;
    protected final TeamStrategy teamStrategy;

    public Piece(Position position, TeamStrategy teamStrategy) {
        this.position = position;
        this.teamStrategy = teamStrategy;
    }

    public boolean isEqualPosition(Position position) {
        return this.position.equals(position);
    }

    public void move(Position target) {
        this.position = target;
    }

    public boolean isSameTeam(Piece targetPiece) {
        return teamStrategy.equals(targetPiece.teamStrategy);
    }

    protected void validSameTeam(Piece targetPiece) {
        if (targetPiece == null) {
            return;
        }
        if (isSameTeam(targetPiece)) {
            throw new IllegalArgumentException("같은팀이 있는 칸으로 이동할수 없습니다.");
        }
    }
}

