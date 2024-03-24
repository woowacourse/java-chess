package chess.domain.state;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Map;

public class BlankMoveState extends MoveState {

    public BlankMoveState(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public void move(Color turnColor, Position source, Position destination) {
        throw new IllegalArgumentException("이동할 수 있는 말이 없습니다.");
    }
}
