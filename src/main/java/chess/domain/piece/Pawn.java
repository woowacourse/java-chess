package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Pawn extends Piece {

    public Pawn(final Color color, final MoveStrategy moveStrategy) {
        super(color, moveStrategy);
    }

    // TODO: 적이 있는 경우에만 대각 이동 가능하게 하기
    // TODO: 더블폰푸시 길막인 상태에서 불가하게 하기
    @Override
    public Direction findDirection(Square current, Square destination) {
        return null;
    }
}
