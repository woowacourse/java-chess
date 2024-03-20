package chess.domain;

import chess.domain.strategy.MoveStrategy;
import java.util.HashMap;
import java.util.Map;

public class BlankMoveStrategy implements MoveStrategy {
    private final Map<Position, Piece> board;

    public BlankMoveStrategy(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    @Override
    public MoveStrategy changeStrategy(Position from) {
        Piece currentPiece = board.get(from);
        if (currentPiece.pieceType() == PieceType.NONE) {
            return this;
        }
        return null;
    }

    @Override
    public void move(Color turnColor, Position from, Position to) {
        throw new IllegalArgumentException("이동할 수 있는 말이 없습니다.");
    }
}
