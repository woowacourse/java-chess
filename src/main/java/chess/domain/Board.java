package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.initial.BoardFactory;

import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create(final Map<Position, Piece> board) {
        return new Board(BoardFactory.create(board));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public void move(final Position source, final Position target) {
        validateDifferentPosition(source, target);
        validateSourceNotEmpty(source);
    }

    private void validateDifferentPosition(final Position source, final Position target) {
        if (source.file() == target.file() && source.rank() == target.rank()) {
            throw new IllegalArgumentException("출발지와 도착지는 같을 수 없습니다");
        }
    }

    private void validateSourceNotEmpty(final Position source) {
        if (isEmptyPosition(source)) {
            throw new IllegalArgumentException("출발점에 체스말이 존재하지 않습니다");
        }
    }

    private boolean isEmptyPosition(final Position source) {
        return board.get(source).getClass().equals(Empty.class);
    }
}
