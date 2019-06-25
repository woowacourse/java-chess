package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chesspieceMove.HorizontalMove;
import chess.domain.chesspieceMove.VerticalMove;

import java.util.HashMap;
import java.util.List;

public class Rook extends ChessPiece {
    public Rook(Team team) {
        super(team);
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new HashMap<>();
        movingMap.put("horizontal", HorizontalMove.getInstance());
        movingMap.put("vertical", VerticalMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        String moveName = "horizontal";

        if (source.isInLine(target) == 0) {
            moveName = "vertical";
        }

        return movingMap.get(moveName).move(source, target);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
