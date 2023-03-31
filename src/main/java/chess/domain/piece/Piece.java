package chess.domain.piece;

import chess.domain.Team;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

public abstract class Piece {
    private final Team team;
    private final PieceType pieceType;

    protected Piece(final Team team, final PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public final void validateMovement(final Square source, final Square destination, final Piece target) {
        int fileInterval = File.calculate(source.getFile(), destination.getFile());
        int rankInterval = Rank.calculate(source.getRank(), destination.getRank());

        if (!isValidMove(fileInterval, rankInterval, target) || !isValidTeam(target) || !isValidPawnMove(source.getRank(), fileInterval, rankInterval)) {
            throw new IllegalArgumentException("같은 팀 말은 공격할 수 없습니다.");
        }
    }

    public abstract boolean isValidMove(final int fileInterval, final int rankInterval, final Piece target);

    public abstract boolean isValidTeam(final Piece target);

    public abstract boolean isValidPawnMove(Rank rank, int fileInterval, int rankInterval);

    public Team getTeam() {
        return team;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
