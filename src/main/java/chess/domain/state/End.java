package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.TeamScore;
<<<<<<< HEAD
=======
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
>>>>>>> step1

public class End implements State {

    private final Board board;

    public End(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
<<<<<<< HEAD
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
=======
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
>>>>>>> step1
    }

    @Override
    public State end() {
<<<<<<< HEAD
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
=======
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
>>>>>>> step1
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
<<<<<<< HEAD
    public State move(Location source, Location target) {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
=======
    public Piece move(Team team, Location source, Location target) {
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
>>>>>>> step1
    }

    @Override
    public TeamScore getScore() {
<<<<<<< HEAD
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
=======
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public Team getTeam() {
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public State getNextState(Piece piece) {
        throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
>>>>>>> step1
    }
}
