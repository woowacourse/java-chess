package chess.domain.state;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Map;

public class BlankMoveState extends MoveState {

    public BlankMoveState(final Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public void move(final Color turnColor, final Position source, final Position destination) {
        throw new IllegalArgumentException("이동할 수 있는 말이 없습니다.");
    }
}
