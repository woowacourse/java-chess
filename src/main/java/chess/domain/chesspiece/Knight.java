package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chesspieceMove.KnightMove;

import java.util.HashMap;
import java.util.List;

public class Knight extends ChessPiece {
    private static final double SCORE = 2.5;

    public Knight(Team team) {
        super(team);
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new HashMap<>();
        movingMap.put(MoveDirection.KNIGHT_MOVING, KnightMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        return movingMap.get(MoveDirection.KNIGHT_MOVING).move(source, target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
