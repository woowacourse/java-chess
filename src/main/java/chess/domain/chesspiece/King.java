package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.Direction;
import chess.domain.chessmove.KingMove;

import java.util.HashMap;
import java.util.List;

public class King extends ChessPiece {
    public King(Team team) {
        super(team);
        chessScore = ChessScore.KING;
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new HashMap<>();
        movingMap.put(Direction.KING, KingMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        return movingMap.get(Direction.KING).move(source, target);
    }
}
