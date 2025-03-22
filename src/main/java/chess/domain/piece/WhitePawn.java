package chess.domain.piece;

import chess.domain.Movement;
import java.util.List;

public class WhitePawn extends LimitedMovingChessPiece {

    // TODO: 색깔에 따라 방향 달라지고, 초기 위치인가에 따라 UP_UP 가능 / 불가능, 대각선 기물 먹기 가능
    public WhitePawn() {
        super(List.of(Movement.UP_UP, Movement.UP, Movement.LEFT_UP, Movement.RIGHT_UP));
    }
}
