package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chesspieceMove.DiagonalMove;

import java.util.HashMap;
import java.util.List;

public class Bishop extends ChessPiece {
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
}
