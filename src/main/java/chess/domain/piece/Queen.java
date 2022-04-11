package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.List;
import java.util.Map;

public final class Queen extends Piece {
    private static final List<Integer> QUEEN_ANGLES = List.of(90, 45, 0, -45, -90, -135, 180, 135);
    private static final Map<Color, Symbol> SYMBOL = Map.of(
            Color.WHITE, Symbol.QUEEN_WHITE,
            Color.BLACK, Symbol.QUEEN_BLACK);

    public Queen(Color color) {
        super(color);
    }

    @Override
    public MoveResult movable(Position from, Position to, Board board) {
        validateAngle(QUEEN_ANGLES, from, to);   // 1. 각도 확인
        validatePieceOnWay(from, to, board);     // 2. 중간 기물 확인
        validateTarget(board, to);               // 3. 목표 기물 확인
        return MoveResult.SUCCESS;
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
