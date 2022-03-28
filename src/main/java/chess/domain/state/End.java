package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Position;

public abstract class End extends GameStarted {

    protected End(Board board) {
        super(board);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isBlackTurn() {
        throw new IllegalStateException("게임이 끝나서 턴이 없습니다.");
    }

    @Override
    public GameState move(Position start, Position target) {
        throw new IllegalStateException("게임이 끝나서 말을 움직일 수 없습니다.");
    }

    @Override
    public GameState terminate() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

    @Override
    public double calculateBlackScore() {
        return board.calculateBlackScore();
    }

    @Override
    public double calculateWhiteScore() {
        return board.calculateWhiteScore();
    }
}
