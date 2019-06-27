package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.Direction;
import chess.domain.chessmove.KnightMove;

import java.util.HashMap;
import java.util.List;

public class Knight extends ChessPiece {
    public Knight(Team team) {
        super(team);
        chessScore = ChessScore.KNIGHT;
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
}
