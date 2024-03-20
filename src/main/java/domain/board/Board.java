package domain.board;

import domain.piece.None;
import domain.piece.Piece;
import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> squares;

    public Board(final Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public void move(final Position source, final Position target) {
        Piece currentPiece = squares.get(source);
        List<Direction> directions = currentPiece.movableDirections();
        List<Position> movablePositions = currentPiece.strategy().movablePositions(source, directions);

        boolean targetMovable = movablePositions.stream()
                .anyMatch(position -> position.equals(target));

        if (currentPiece.isPawn()) {
            
        }
        if (!targetMovable) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        if (squares.get(target).isNotNone()) {
            if (squares.get(target).color() == currentPiece.color()) {
                throw new IllegalArgumentException("같은 팀의 말이 있습니다.");
            }
            squares.remove(target);
            squares.put(target, currentPiece);
            squares.put(source, new None(Color.NONE, Type.NONE));
        }
        squares.put(target, currentPiece);
        squares.put(source, new None(Color.NONE, Type.NONE));
    }

    public Map<Position, Piece> squares() {
        return squares;
    }
}
