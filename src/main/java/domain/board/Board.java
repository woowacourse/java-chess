package domain.board;

import domain.piece.Color;
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
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("source 위치와 target 위치가 같을 수 없습니다.");
        }
        if (isNoPieceAt(sourcePosition)) {
            throw new IllegalArgumentException("source 위치에 말이 없습니다.");
        }
        if (isPieceAt(targetPosition) && (findPieceColorAt(sourcePosition) == findPieceColorAt(targetPosition))) {
            throw new IllegalArgumentException("한 칸에 말이 2개 존재할 수 없습니다.");
        }
        Piece piece = squares.get(sourcePosition);
        if (piece.canMove(sourcePosition, targetPosition)) {
            squares.remove(sourcePosition);
            squares.put(targetPosition, piece);
            return;
        }
        throw new IllegalArgumentException("말의 규칙에 맞지 않는 이동입니다.");
    }

    private boolean isPieceAt(Position position) {
        return squares.containsKey(position);
    }

    private boolean isNoPieceAt(Position position) {
        return !squares.containsKey(position);
    }

    private Color findPieceColorAt(Position position) {
        if (isNoPieceAt(position)) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
        if (squares.get(position).isWhite()) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    // TODO: getter 사용을 지양하는 방법을 고민
    public Map<Position, Piece> getSquares() {
        return squares;
    }
}
