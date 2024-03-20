package domain.board;

import domain.piece.Piece;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> squares;

    private Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public static Board generatedBy(BoardGenerator boardGenerator) {
        return new Board(boardGenerator.generate());
    }

    public void move(Position sourcePosition, Position targetPosition) {
        if (isNoPieceAt(sourcePosition)) {
            throw new IllegalArgumentException("source 위치에 말이 없습니다.");
        }
    }

    public boolean isNoPieceAt(Position position) {
        return !squares.containsKey(position);
    }

    // TODO: getter 사용을 지양하는 방법을 고민
    public Map<Position, Piece> getSquares() {
        return squares;
    }
}
