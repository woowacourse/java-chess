package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public final class EndState extends InitializedState {

    protected EndState(final Map<Position, Piece> board, final Color turn) {
        super(board, turn);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public Board move(final Position source, final Position target) {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }
}
