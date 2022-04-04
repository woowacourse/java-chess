package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.TeamScore;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Ready implements State {
    private final Board board = new Board();

    @Override
    public State start() {
        board.initializeBoard();
        return new White(board);
    }

    @Override
    public State end() {
        throw new IllegalStateException("[ERROR] 게임이 시작되지 않았습니다.");
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
    public Piece move(Team currentTeam, Location source, Location target) {
        throw new IllegalStateException("[ERROR] 게임이 시작되지 않았습니다.");

    }

    @Override
    public TeamScore getScore() {
        throw new IllegalStateException("[ERROR] 게임이 시작되지 않았습니다.");
    }

    @Override
    public Team getTeam() {
        throw new IllegalStateException("[ERROR] 게임이 시작되지 않았습니다.");
    }

    @Override
    public State getNextState(Piece piece) {
        throw new IllegalStateException("[ERROR] 게임이 시작되지 않았습니다.");
    }
}
