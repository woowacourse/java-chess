package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public abstract class Initialized extends AbstractBoard {

    protected Initialized(final Map<Position, Piece> board, final Color turn) {
        super(board, turn);
    }

    @Override
    public final Board initialize() {
        throw new IllegalStateException("이미 초기화된 보드입니다.");
    }

    @Override
    public final boolean isInitialized() {
        return true;
    }

    @Override
    public final GameResult getResult() {
        return new GameResult(Collections.unmodifiableMap(board));
    }
}
