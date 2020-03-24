package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class Board {

    private Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board of(BoardInitializer boardInitializer) {
        return new Board(boardInitializer.create());
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        validateSource(sourcePiece);
        sourcePiece.move(target);
        board.remove(source);
        board.put(target, sourcePiece);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    private void validateSource(Piece sourcePiece) {
        if (Objects.isNull(sourcePiece)) {
            throw new IllegalArgumentException("잘못된 위치를 선택하셨습니다.");
        }
    }
}
