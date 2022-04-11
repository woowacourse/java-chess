package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.Map;

public final class King extends Piece {
    private static final int MAXIMUM_DISTANCE_OF_KING = 1;
    private static final String EXCEEDED_MAXIMUM_DISTANCE_OF_KING = "King의 이동 범위를 초과했습니다";
    private static final Map<Color, Symbol> SYMBOL = Map.of(
            Color.WHITE, Symbol.KING_WHITE,
            Color.BLACK, Symbol.KING_BLACK);

    public King(Color color) {
        super(color);
    }

    @Override
    public MoveResult movable(Position from, Position to, Board board) {
        validateDistance(from, to); // 1. dx,dy로 이동 가능한 8점 확인
        validateTarget(board, to);  // 2. 목표 기물 확인
        return MoveResult.SUCCESS;
    }

    private void validateDistance(Position from, Position to) {
        if (Math.abs(from.dx(to)) > MAXIMUM_DISTANCE_OF_KING || Math.abs(from.dy(to)) > MAXIMUM_DISTANCE_OF_KING) {
            throw new IllegalStateException(EXCEEDED_MAXIMUM_DISTANCE_OF_KING);
        }
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getScore() {
        return Symbol.getScore(SYMBOL.get(this.color));
    }

    @Override
    public String getName() {
        return Symbol.getName(SYMBOL.get(this.color));
    }
}
