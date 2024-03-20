package chess.domain.board;

import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Empty;
import chess.domain.square.Square;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Square> squares;

    public ChessBoard(Map<Position, Square> squares) {
        this.squares = new LinkedHashMap<>(squares);
    }

    public Map<Position, Square> getSquares() {
        return Collections.unmodifiableMap(squares);
    }

    public void move(Path path) {
        validateStartEmpty(path);
        if (squares.get(path.getEnd()) == Empty.getInstance()) {
            tryExchange(path);
            return;
        }
        tryAttack(path);
    }

    private void tryExchange(Path path) {
        Square startSquare = squares.get(path.getStart());
        if (startSquare.canMove(path, squares)) {
            startSquare.move();
            Square tmp = squares.get(path.getEnd());
            squares.put(path.getEnd(), startSquare);
            squares.put(path.getStart(), tmp);
        }
    }

    private void tryAttack(Path path) {
        Square startSquare = squares.get(path.getStart());
        if (startSquare.canAttack(path, squares)) {
            startSquare.move();
            squares.put(path.getEnd(), startSquare);
            squares.put(path.getStart(), Empty.getInstance());
        }
    }

    private void validateStartEmpty(Path path) {
        if (squares.get(path.getStart()) == Empty.getInstance()) {
            throw new IllegalArgumentException("시작 위치에 체스말이 존재해야 합니다.");
        }
    }
}
