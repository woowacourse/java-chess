package chess.domain.piece;

import java.util.Objects;
import chess.domain.board.Coordinate;
import chess.domain.board.Pieces;

abstract class AbstractPiece implements Piece {

    protected final PieceType type;
    protected final Team team;

    public AbstractPiece(PieceType type, Team team) {
        this.type = type;
        this.team = team;
    }

    @Override
    public void validateMovable(Coordinate source, Coordinate target, Pieces pieces) {
        validateSameCoordinate(source, target);
        validateSameTeam(target, pieces);
        validatePieceMoveRule(source, target, pieces);
    }

    private void validateSameCoordinate(Coordinate source, Coordinate target) {
        if (source.equals(target)) {
            throw new IllegalStateException("제자리 이동은 할 수 없습니다.");
        }
    }

    private void validateSameTeam(Coordinate target, Pieces pieces) {
        Piece targetPiece = pieces.findByCoordinate(target);
        if (targetPiece.isSameTeam(team)) {
            throw new IllegalStateException("아군 기물은 공격할 수 없습니다.");
        }
    }

    abstract void validatePieceMoveRule(Coordinate source, Coordinate target, Pieces pieces);

    @Override
    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    @Override
    public boolean isEnemy(Piece other) {
        return this.team.opposite() == other.getTeam();
    }

    @Override
    public PieceType getType() {
        return type;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPiece piece = (AbstractPiece) o;
        return type == piece.type && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, team);
    }
}
