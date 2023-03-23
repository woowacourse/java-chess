package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.math.Direction;
import java.util.List;

public abstract class Piece {

    public static final String INVALID_MOVE_EXIST_ALLY = "이동하려는 위치에 아군이 존재합니다.";
    public static final String INVALID_DIRECTION = "해당 기물이 갈 수 없는 방향입니다.";

    private final Team team;
    private final List<Direction> movableDirections;

    public Piece(final Team team, final List<Direction> movableDirections) {
        validateTeam(team);
        this.team = team;
        this.movableDirections = List.copyOf(movableDirections);
    }

    abstract protected void validateTeam(final Team team);

    abstract public void validateMove(final Direction correctDirection, final List<Piece> onRoutePieces);

    public void validateDirection(final Direction correctDirection) {
        if (movableDirections.contains(correctDirection)) {
            return;
        }
        throw new IllegalArgumentException(INVALID_DIRECTION);
    }

    protected final void validateOnRoutePiecesExistAlly(final List<Piece> onRoutePieces) {
        for (Piece otherPiece : onRoutePieces) {
            validateAlly(otherPiece);
        }
    }

    protected final void validateAlly(final Piece otherPiece) {
        if (this.team.equals(otherPiece.team)) {
            throw new IllegalArgumentException(INVALID_MOVE_EXIST_ALLY);
        }
    }

    public boolean isAlly(final Piece otherPiece) {
        return this.team == otherPiece.team;
    }

    public boolean isNeutrality() {
        return team.isNeutrality();
    }

    public boolean isAlly(final Team otherTeam) {
        return team.isAlly(otherTeam);
    }

    public final Team getTeam() {
        return team;
    }

    public List<Direction> getMovableDirections() {
        return List.copyOf(movableDirections);
    }
}
