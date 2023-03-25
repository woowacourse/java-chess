package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;

import java.util.Map;

public final class Waiting implements GameStatus {

    public Waiting() {
        super();
    }

    @Override
    public GameStatus start() {
        return new Running(Board.initializeBoard(), Color.WHITE);
    }

    @Override
    public GameStatus load(final GameStatus gameStatus) {
        return gameStatus;
    }

    @Override
    public GameStatus playTurn(final Position source, final Position target) {
        throw new UnsupportedOperationException("시작하기 전에 이동 명령을 내릴 수 없습니다.");
    }

    @Override
    public GameStatus end() {
        return new Finished();
    }

    @Override
    public boolean isOnGoing() {
        return true;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException("보드가 존재하지 않습니다.");
    }

    @Override
    public double computeScore(final Color color) {
        throw new UnsupportedOperationException("시작하지 않은 게임의 점수를 볼 수 없습니다.");
    }

    @Override
    public Color getTurn() {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }
}
