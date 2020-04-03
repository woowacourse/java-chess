package chess.domain.piece;

import chess.domain.piece.state.piece.Started;
import chess.domain.piece.team.Team;
import chess.domain.board.Board;
import chess.domain.piece.position.Position;

public class Blank extends Started {

    private Blank(String name, Team team) {
        super(name, team);
    }

    public static Blank of() {
        String name = ".";
        Team team = Team.NOT_ASSIGNED;
        return new Blank(name, team);
    }

    @Override
    public Piece move(Position to, Board board) {
        throw new IllegalStateException(String.format("해당 자리 %s가 비어있습니다.", to));
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
}
