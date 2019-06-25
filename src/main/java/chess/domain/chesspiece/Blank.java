package chess.domain.chesspiece;

import chess.domain.Position;

import java.util.HashMap;
import java.util.List;

public class Blank extends ChessPiece {
    private static final double SCORE = 0;

    public Blank(Team team) {
        super(team);
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new HashMap<>();
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position position) {
        return null;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
