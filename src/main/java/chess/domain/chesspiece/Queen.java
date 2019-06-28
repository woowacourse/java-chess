package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.DiagonalMove;
import chess.domain.chessmove.Direction;
import chess.domain.chessmove.HorizontalMove;
import chess.domain.chessmove.VerticalMove;

import java.util.EnumMap;
import java.util.List;

public class Queen extends ChessPiece {
    public Queen(Team team) {
        super(team);
        chessScore = ChessScore.QUEEN;
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new EnumMap<>(Direction.class);
        movingMap.put(Direction.HORIZONTAL, HorizontalMove.getInstance());
        movingMap.put(Direction.VERTICAL, VerticalMove.getInstance());
        movingMap.put(Direction.DIAGONAL, DiagonalMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        Direction moveName = Direction.DIAGONAL;

        if (source.isInLine(target) == Position.VERTICAL_LINE) {
            moveName = Direction.VERTICAL;
        }
        if (source.isInLine(target) == Position.HORIZONTAL_LINE) {
            moveName = Direction.HORIZONTAL;
        }

        return movingMap.get(moveName).move(source, target);
    }
}
