package chess.domain.piece.limited_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.piece.ChessPiece;
import java.util.List;

public class Knight extends LimitedMovingChessPiece {

    private static final List<Movement> ROUTES = List.of(
            Movement.DOWN_DOWN_LEFT, Movement.DOWN_DOWN_RIGHT,
            Movement.RIGHT_RIGHT_DOWN, Movement.RIGHT_RIGHT_UP,
            Movement.UP_UP_LEFT, Movement.UP_UP_RIGHT,
            Movement.LEFT_LEFT_DOWN, Movement.LEFT_LEFT_UP
    );

    public Knight(Color side) {
        super(ROUTES, side);
    }

    @Override
    public void validateCanMove(List<Movement> route, boolean isExistHurdleOnRoute, ChessPiece targetPiece) {
        if (this.getColor() == targetPiece.getColor()) {
            throw new IllegalStateException("도착지에 같은 팀의 말이 존재하기 때문에 이동할 수 없습니다.");
        }
    }

    @Override
    public String name() {
        return "N";
    }
}
