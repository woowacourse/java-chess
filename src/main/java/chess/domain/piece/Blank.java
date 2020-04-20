package chess.domain.piece;

import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

public class Blank implements Piece {
    private final String name;
    private final Team team;

    private Blank(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public static Blank of() {
        String name = ".";
        Team team = Team.NOT_ASSIGNED;
        return new Blank(name, team);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        throw new IllegalStateException("해당 자리가 비어있습니다.");
    }

    @Override
    public boolean canNotMove(Position to, PiecesState piecesState) {
        return true;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public boolean isEnemy(Piece piece) {
        throw new IllegalStateException("체스 말이 아닙니다.");
    }

    @Override
    public boolean isKing() {
        throw new IllegalStateException("체스 말이 아닙니다.");
    }

    @Override
    public boolean attackedKing() {
        throw new IllegalStateException("체스 말이 아닙니다.");
    }

    @Override
    public boolean isSameTeam(Team team) {
        throw new IllegalStateException("체스 말이 아닙니다.");
    }

    @Override
    public boolean isSameTeam(Piece piece) {
        throw new IllegalStateException("체스 말이 아닙니다.");
    }

    @Override
    public Position getPosition() {
        throw new IllegalStateException("체스 말이 아닙니다.");
    }

    @Override
    public Score calculateScore(PiecesState piecesState) {
        throw new IllegalStateException("체스 말이 아닙니다.");
    }

    @Override
    public String toString() {
        return name;
    }
}
