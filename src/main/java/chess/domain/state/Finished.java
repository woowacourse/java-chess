package chess.domain.state;

import chess.domain.*;
import chess.domain.piece.Piece;

import java.util.Map;

public class Finished implements GameState {
    private final Team team;
    private final Map<Row, Rank> board;

    public Finished(Team team, Map<Row, Rank> board) {
        this.team = team;
        this.board = board;
    }

    @Override
    public Piece getPiece(Position position) {
        return null;
    }

    @Override
    public Playing move(String source, String destination) {
        return null;
    }

    @Override
    public Map<Row, Rank> getBoard() {
        return board;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int getTeamScore() {
        throw new IllegalArgumentException("게임이 끝나 점수 조회가 불가능 합니다.");
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
