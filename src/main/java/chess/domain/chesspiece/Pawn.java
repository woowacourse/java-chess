package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.Direction;
import chess.domain.chessmove.PawnMove;

import java.util.EnumMap;
import java.util.List;

public class Pawn extends ChessPiece {
    public Pawn(Team team) {
        super(team);
        chessScore = ChessScore.PAWN;
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new EnumMap<>(Direction.class);
        movingMap.put(Direction.PAWN, PawnMove.getInstance(team));
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        return movingMap.get(Direction.PAWN).move(source, target);
    }
}
