package chess.domain.board;

import chess.domain.piece.Color;
import java.util.HashMap;

public final class Start extends AbstractBoard {

    public Start() {
        super(new HashMap<>(), Color.WHITE);
    }

    @Override
    public Board initialize() {
        board.putAll(BoardGenerator.generate());
        return new Play(board, turn);
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public Board move(final String source, final String target) {
        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }

    @Override
    public double score(final Color color) {
        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }
}
