package chess.domain.piece;

import chess.domain.board.Position;

public abstract class Piece {

    private static final String INVALID_POSITION_MESSAGE = "잘못된 위치를 입력했습니다.";
    private static final String INVALID_PIECE_MOVE_MESSAGE = "본인의 말만 옮길 수 있습니다.";

    protected final Team team;
    protected final Position position;
    protected final PieceType pieceType;

    protected Piece(Team team, Position position, PieceType pieceType) {
        this.team = team;
        this.position = position;
        this.pieceType = pieceType;
    }

    public abstract boolean canMove(Position targetPosition, Team team);

    public abstract boolean isEmpty();

    public abstract Piece move(Position targetPosition, Team nowPlayingTeam, Team targetTeam);

    protected void validate(Position targetPosition, Team nowPlayingTeam, Team targetTeam) {
        validateCanMove(targetPosition, targetTeam);
        validateTeam(nowPlayingTeam);
    }

    private void validateCanMove(Position targetPosition, Team targetTeam) {
        if (!canMove(targetPosition, targetTeam)) {
            throw new IllegalArgumentException(INVALID_POSITION_MESSAGE);
        }
    }

    private void validateTeam(Team nowPlayingTeam) {
        if (isDifferentTeam(nowPlayingTeam)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVE_MESSAGE);
        }
    }

    protected boolean isNotMyPosition(Position targetPosition) {
        return !position.equals(targetPosition);
    }

    protected boolean isDifferentTeam(Team targetTeam) {
        return !team.equals(targetTeam);
    }

    public Team getTeam() {
        return team;
    }
}
