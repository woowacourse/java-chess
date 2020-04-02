package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.EnumRepositoryBoardInitializer;
import chess.domain.game.Turn;
import chess.domain.player.Team;

public class EndState implements State {

    private final Board board;
    private final Team winner;

    public EndState(Board board, Team winner) {
        this.board = board;
        this.winner = winner;
    }

    @Override
    public State start() {
        return new RunningState(Board.of(new EnumRepositoryBoardInitializer()));
    }

    @Override
    public State move(MoveParameter moveParameter, Turn turn) {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("이미 종료 되었습니다.");
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public double getPoints(Team team) {
        return board.getScores(team);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public Team getWinner() {
        return winner;
    }
}
