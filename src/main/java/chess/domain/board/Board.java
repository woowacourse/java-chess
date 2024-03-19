package chess.domain.board;

import chess.domain.position.Square;
import chess.domain.piece.Piece;
import chess.dto.BoardOutput;

import java.util.Map;

public class Board {

    private final Map<Square, Piece> board;

    public Board() {
        this.board = new BoardFactory().create();
    }

    public BoardOutput toBoardOutput() {
        return new BoardOutput(board);
    }
}
