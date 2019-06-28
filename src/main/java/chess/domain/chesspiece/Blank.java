package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.Direction;

import java.util.EnumMap;
import java.util.List;

public class Blank extends ChessPiece {
    public Blank(Team team) {
        super(team);
        chessScore = ChessScore.BLANK;
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new EnumMap<>(Direction.class);
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position position) {
        return null;
    }
}
