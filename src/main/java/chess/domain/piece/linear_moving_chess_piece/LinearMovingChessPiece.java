package chess.domain.piece.linear_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.ChessPiece;
import java.util.Collections;
import java.util.List;

public abstract class LinearMovingChessPiece implements ChessPiece {

    protected final List<Movement> directions;
    protected final Color side;
    protected boolean isCaptured;

    public LinearMovingChessPiece(List<Movement> directions, Color side) {
        this.directions = directions;
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
        for (Movement direction : directions) {
            Position origin2 = origin;
            int i = 0;
            while (origin2.canMove(direction)) {
                origin2 = origin2.move(direction);
                i++;
                if (origin2.equals(destination)) {
                    return Collections.nCopies(i, direction);
                }
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
