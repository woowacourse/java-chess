package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public abstract class AbstractBoard implements Board {
    protected final Map<Position, Piece> board;
    protected final Color turn;

    protected AbstractBoard(final Map<Position, Piece> board, final Color turn) {
        this.board = board;
        this.turn = turn;
    }

    @Override
    public abstract Board initialize();

    @Override
    public abstract boolean isInitialized();

    @Override
    public abstract boolean isEnd();

    @Override
    public abstract Board move(final String source, final String target);

    @Override
    public abstract GameResult getResult();
}
