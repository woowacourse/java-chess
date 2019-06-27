package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.Direction;
import chess.domain.chessmove.PawnMove;

import java.util.HashMap;
import java.util.List;

public class Pawn extends ChessPiece {
    private static final int SCORE = 1;

    public Pawn(Team team) {
        super(team);
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new HashMap<>();
        movingMap.put(Direction.PAWN, PawnMove.getInstance(team));
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        return movingMap.get(Direction.PAWN).move(source, target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
