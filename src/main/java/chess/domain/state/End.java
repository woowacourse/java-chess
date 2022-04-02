package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.TeamScore;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class End implements State {

    private final Board board;

    public End(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public State end() {
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Piece move(Team team, Location source, Location target) {
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public TeamScore getScore() {

        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public Team getTeam() {
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public State getNextState(Piece piece) {
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
    }
}
