package chess.domain.board;

import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.square.Empty;
import chess.domain.square.Square;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Square> squares;

    public ChessBoard(Map<Position, Square> squares) {
        this.squares = new HashMap<>(squares);
    }

    public void move(PathFinder pathFinder) {
        Square startSquare = squares.get(pathFinder.startPosition());
        validateCanMove(startSquare, pathFinder);

        squares.put(pathFinder.targetPosition(), startSquare);
        squares.put(pathFinder.startPosition(), Empty.getInstance());
    }

    private void validateCanMove(Square startSquare, PathFinder pathFinder) {
        if (!startSquare.canArrive(pathFinder, squares)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }
    }

    public Square findSquare(Position position) {
        return squares.get(position);
    }

    public Map<Position, Square> getSquares() {
        return Collections.unmodifiableMap(squares);
    }
}
