package chess.domain.board;

import chess.domain.board.BoardGenerator;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardGeneratorStub implements BoardGenerator {
    private Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> generate() {
        return board;
    }

    public void setBoard(Map<Position, Piece> board) {
        this.board = board;
    }
}
