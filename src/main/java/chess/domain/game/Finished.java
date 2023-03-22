package chess.domain.game;

import chess.domain.board.Position;
import chess.domain.board.Squares;

import java.util.List;

public final class Finished implements GameStatus {
    @Override
    public GameStatus start() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public GameStatus playTurn(final Position source, final Position target) {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public GameStatus end() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public boolean isOnGoing() {
        return false;
    }

    @Override
    public List<Squares> getBoard() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public GameStatus save() {
        return null;
    }
}
