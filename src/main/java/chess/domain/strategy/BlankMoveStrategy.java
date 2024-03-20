package chess.domain.strategy;

import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Position;
import java.util.Map;

public class BlankMoveStrategy extends MoveStrategy {

    public BlankMoveStrategy(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public void move(Color turnColor, Position from, Position to) {
        throw new IllegalArgumentException("이동할 수 있는 말이 없습니다.");
    }
}
