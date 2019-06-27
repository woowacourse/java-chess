package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.DiagonalMove;
import chess.domain.chessmove.Direction;

import java.util.EnumMap;
import java.util.List;

public class Bishop extends ChessPiece {
    public Bishop(Team team) {
        super(team);
        chessScore = ChessScore.BISHOP;
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new EnumMap<>(Direction.class);
        movingMap.put(Direction.DIAGONAL, DiagonalMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        return movingMap.get(Direction.DIAGONAL).move(source, target);
    }
}
