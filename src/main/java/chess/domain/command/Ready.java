package chess.domain.command;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.point.Point;
import chess.dto.Command;

public final class Ready extends Active {
    private static final long NOT_EXISTS_GAME_ID = 0L;

    private Ready(final long gameId, final Chess chess) {
        super(gameId, chess);
    }

    public static Active create() {
        return new Ready(0, null);
    }

    @Override
    public Active execute(final Command command) {
        throw new UnsupportedOperationException("현재 대기중인 게임입니다.");
    }

    @Override
    public Point getPointBy(final Color color) {
        throw new UnsupportedOperationException("현재 대기중인 게임은 점수 합산을 구할 수 없습니다.");
    }

    @Override
    public Color getWinner() {
        throw new UnsupportedOperationException("현재 대기중인 게임은 승리자를 구할 수 없습니다.");
    }

    @Override
    public Chess getChess() {
        throw new UnsupportedOperationException("현재 대기중인 게임의 체스 정보를 구할 수 없습니다.");
    }

    @Override
    public long getGameId() {
        return NOT_EXISTS_GAME_ID;
    }

    @Override
    public Color getCurrentPlayer() {
        throw new UnsupportedOperationException("현재 대기중인 게임의 현재 플레이어를 구할수 없습니다.");
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
