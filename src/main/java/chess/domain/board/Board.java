package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public class Board {
    private final List<Piece> board;

    protected Board(List<Piece> board) {
        this.board = board;
    }

    public Piece findByPosition(Position position) {
       return board.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 없습니다."));
    }
}
