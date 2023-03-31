package chess.domain.command;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.point.Point;
import chess.dto.Command;

public final class End extends Active {
    private End(final long gameId, final Chess chess) {
        super(gameId, chess);
    }

    public static End create(final long gameId) {
        return new End(gameId, null);
    }

    @Override
    public Active execute(final Command command) {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }

    @Override
    public Point getPointBy(final Color color) {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }

    @Override
    public Color getWinner() {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }

    @Override
    public Chess getChess() {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }

    @Override
    public long getGameId() {
        return gameId;
    }

    @Override
    public Color getCurrentPlayer() {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }

    @Override
    public boolean isNotEnd() {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
    }
}
