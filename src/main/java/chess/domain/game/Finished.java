package chess.domain.game;

import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.domain.position.Position;

import java.util.Map;

public final class Finished implements GameStatus {
    @Override
    public GameStatus start() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public GameStatus load(final GameStatus gameStatus) {
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
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public double computeScore(final Color color) {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public Color getTurn() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public Color computeWinner() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }
}
