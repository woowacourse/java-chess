package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.DiagonalMove;

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
        movingMap.put("diagonal", DiagonalMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        return movingMap.get("diagonal").move(source, target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
