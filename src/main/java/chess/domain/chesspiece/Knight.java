package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.Direction;
import chess.domain.chessmove.KnightMove;

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
        movingMap.put(Direction.KNIGHT, KnightMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        return movingMap.get(Direction.KNIGHT).move(source, target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
