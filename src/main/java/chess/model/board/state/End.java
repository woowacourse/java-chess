package chess.model.board.state;

import chess.controller.GameCommand;
import chess.model.Scores;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public class End implements GameState {

    @Override
    public GameState changeState(final GameCommand gameCommand) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute(final Position source, final Position target) {
        // 게임이 끝난 상태에서는 실행 할 수 없음.
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }

    @Override
    public GameState isGameEnd() {
        return this;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public Scores calculateScores() {
        throw new UnsupportedOperationException("지원하지 않는 기능 입니다.");
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException("지원하지 않는 기능 입니다.");
    }
}
