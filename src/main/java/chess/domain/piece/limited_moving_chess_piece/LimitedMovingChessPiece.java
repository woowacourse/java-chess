package chess.domain.piece.limited_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.ChessPiece;
import java.util.List;

public abstract class LimitedMovingChessPiece implements ChessPiece {

    protected final List<Movement> movements;
    protected final Color side;
    protected boolean isCaptured;

    public LimitedMovingChessPiece(List<Movement> movements, Color side) {
        this.movements = movements;
        this.side = side;
        this.isCaptured = false;
    }

    @Override
    public void validateCanMove(List<Movement> route, boolean isExistHurdleOnRoute, ChessPiece targetPiece) {
        if (isExistHurdleOnRoute) {
            throw new IllegalStateException("경로에 장애물이 존재하기 때문에 이동할 수 없습니다.");
        }
        if (this.getColor() == targetPiece.getColor()) {
            throw new IllegalStateException("도착지에 같은 팀의 말이 존재하기 때문에 이동할 수 없습니다.");
        }
    }

    @Override
    public List<Movement> findRoute(Position origin, Position destination) {
        for (Movement movement : movements) {
            if (origin.canMove(movement) && origin.move(movement).equals(destination)) {
                return List.of(movement);
            }
        }
        throw new IllegalStateException("해당 기물은 해당 경로로 이동할 수 없습니다.");
    }

    @Override
    public Color getColor() {
        return side;
    }

    @Override
    public void capture() {
        this.isCaptured = true;
    }

    @Override
    public boolean isCaptured() {
        return isCaptured;
    }
}
