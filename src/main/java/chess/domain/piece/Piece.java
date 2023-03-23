package chess.domain.piece;

import chess.domain.board.Position;

public abstract class Piece {

    private static final String INVALID_POSITION_MESSAGE = "잘못된 위치를 입력했습니다.";
    private static final String INVALID_PIECE_MOVE_MESSAGE = "본인의 말만 옮길 수 있습니다.";

    private final Team team;

    protected Piece(Team team) {
        this.team = team;
    }

    public abstract boolean canMove(Position sourcePosition, Position targetPosition, Team team);

    public abstract boolean isEmpty();

    public abstract Piece move(Position sourcePosition, Position targetPosition, Team nowPlayingTeam, Team targetTeam);

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    protected void validate(Position sourcePosition, Position targetPosition, Team nowPlayingTeam, Team targetTeam) {
        validateCanMove(sourcePosition, targetPosition, targetTeam);
        validateTeam(nowPlayingTeam);
    }

    private void validateCanMove(Position sourcePosition, Position targetPosition, Team team) {
        if (!canMove(sourcePosition, targetPosition, team)) {
            throw new IllegalArgumentException(INVALID_POSITION_MESSAGE);
        }
    }

    private void validateTeam(Team team) {
        if (!isSameTeam(team)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVE_MESSAGE);
        }
    }

    protected boolean isNotMyPosition(Position sourcePosition, Position targetPosition) {
        return !sourcePosition.equals(targetPosition);
    }

    protected boolean isDiagonal(Position sourcePosition, Position targetPosition) {
        int columnDifference = Math.abs(sourcePosition.getColumn() - targetPosition.getColumn());
        int rowDifference = Math.abs(sourcePosition.getRow() - targetPosition.getRow());
        return columnDifference == rowDifference;
    }

    protected boolean isStraight(Position sourcePosition, Position targetPosition) {
        return (sourcePosition.getFileCoordinate() == targetPosition.getFileCoordinate()
                || sourcePosition.getRankCoordinate() == targetPosition.getRankCoordinate());
    }

    protected boolean isNotSameTeam(Team team) {
        return this.team != team;
    }

    public Team getTeam() {
        return team;
    }
}
