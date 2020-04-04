package chess.domain.gamestatus;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.score.Score;
import java.util.Map;

public class NothingHappened implements GameStatus {

    @Override
    public GameStatus start() {
        return new Running();
    }

    @Override
    public GameStatus move(String fromPosition, String toPosition) {
        throw new UnsupportedOperationException("현 상태에서 수행할 수 없는 동작입니다.");
    }

    @Override
    public Score scoring() {
        throw new UnsupportedOperationException("현 상태에서 수행할 수 없는 동작입니다.");
    }

    @Override
    public GameStatus finish() {
        return new Finished();
    }

    @Override
    public String getBoardString() {
        throw new UnsupportedOperationException("현 상태에서 수행할 수 없는 동작입니다.");
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException("현 상태에서 수행할 수 없는 동작입니다.");
    }
}
