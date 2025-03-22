package chess.domain.piece.limited_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.ChessPiece;
import java.util.List;

public abstract class Pawn extends LimitedMovingChessPiece {

    protected boolean isFirstMove;

    public Pawn(List<Movement> movements, Color side) {
        super(movements, side);
        this.isFirstMove = true;
    }

    @Override
    public void validateCanMove(List<Movement> route, boolean isExistHurdleOnRoute, ChessPiece targetPiece) {
        if (isExistHurdleOnRoute) {
            throw new IllegalStateException("경로에 장애물이 존재하기 때문에 이동할 수 없습니다.");
        }
        if (route.getFirst().isDiagonal() && (side.opposite() != targetPiece.getColor())) {
            throw new IllegalStateException("대각선 위치에 상대 말이 없기 때문에 움직일 수 없습니다.");
        }
        if (!route.getFirst().isDiagonal() && !targetPiece.isEmpty()) {
            throw new IllegalStateException("도착지에 말이 존재하기 때문에 움직일 수 없습니다.");
        }
    }

    @Override
    public String name() {
        return "P";
    }

    public List<Movement> findRoute(Position origin, Position destination) {
        for (Movement movement : movements) {
            if (origin.canMove(movement) && origin.move(movement).equals(destination)) {
                if (movement.isTwoTimeVerticalMove() && !isFirstMove) {
                    throw new IllegalStateException("폰은 처음 움직일 때만 두 칸 움직일 수 있습니다.");
                }
                return List.of(movement);
            }
        }
        throw new IllegalStateException("해당 말은 해당 위치로 움직일 수 없습니다.");
    }

    public void isMoved() {
        this.isFirstMove = false;
    }
}
