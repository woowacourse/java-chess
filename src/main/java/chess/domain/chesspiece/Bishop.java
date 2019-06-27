package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chesspieceMove.DiagonalMove;

import java.util.HashMap;
import java.util.List;

public class Bishop extends ChessPiece {
    private static final double SCORE = 3;

    public Bishop(Team team) {
        super(team);
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new HashMap<>();
        movingMap.put(MoveDirection.DIAGONAL, DiagonalMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        return movingMap.get(MoveDirection.DIAGONAL).move(source, target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
