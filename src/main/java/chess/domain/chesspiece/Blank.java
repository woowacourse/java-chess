package chess.domain.chesspiece;

import chess.domain.Position;

import java.util.HashMap;
import java.util.List;

public class Blank extends ChessPiece {
    public Blank(Team team) {
        super(team);
        chessScore = ChessScore.BLANK;
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
}
