package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.square.Color;
import chess.domain.square.Square;
import chess.domain.square.Team;

public class Running implements State {
    private final Board board;

    public Running() {
        this.board = BoardFactory.create();
    }

    @Override
    public State start() {
        throw new IllegalStateException("보드판 초기화 이후에 다시 초기화할 수 없습니다.");
    }

    @Override
    public State move(final Square source, final Square target) {
        board.makeMove(source, target);
        if (isEnd()) {
            return new End(board.findWinner());
        }
        return this;
    }

    @Override
    public State end() {
        return new End(Team.from(Color.EMPTY));
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public double calculateScore(final Team team) {
        return board.calculateScore(team);
    }

    @Override
    public boolean isEnd() {
        return board.isEnd();
    }

    @Override
    public Team getWinner() {
        throw new IllegalStateException("게임이 끝나지 않아 우승자가 없습니다.");
    }
}
