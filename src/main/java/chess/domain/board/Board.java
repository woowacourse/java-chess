package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.Position;

import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new BoardFactory().create();
    }
}
