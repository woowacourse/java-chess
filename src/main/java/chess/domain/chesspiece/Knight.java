package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chesspieceMove.KnightMove;

import java.util.HashMap;
import java.util.List;

public class Knight extends ChessPiece {
    public Knight(Team team) {
        super(team);
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new HashMap<>();
        movingMap.put("knight", KnightMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        return movingMap.get("knight").move(source, target);
    }
}
