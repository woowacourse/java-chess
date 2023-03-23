package chess.domain.piece;

import chess.domain.board.Position;

public abstract class Piece {

    private static final String INVALID_POSITION_MESSAGE = "잘못된 위치를 입력했습니다.";
    private static final String INVALID_PIECE_MOVE_MESSAGE = "본인의 말만 옮길 수 있습니다.";

    protected final Team team;
    protected final Position position;

    protected Piece(Team team, Position position) {
        this.team = team;
        this.position = position;
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
        if (isNotSameTeam(nowPlayingTeam)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVE_MESSAGE);
        }
    }

    protected boolean isNotMyPosition(Position targetPosition) {
        return !position.equals(targetPosition);
    }

    protected boolean isDiagonal(Position targetPosition) {
        int columnDifference = Math.abs(position.getColumn() - targetPosition.getColumn());
        int rowDifference = Math.abs(position.getRow() - targetPosition.getRow());
        return columnDifference == rowDifference;
    }

    protected boolean isStraight(Position targetPosition) {
        return (position.getFileCoordinate() == targetPosition.getFileCoordinate()
                || position.getRankCoordinate() == targetPosition.getRankCoordinate());
    }

    protected boolean isNotSameTeam(Team team) {
        return this.team != team;
    }

    public Team getTeam() {
        return team;
    }
}
