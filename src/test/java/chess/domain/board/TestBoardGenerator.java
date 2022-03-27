package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;

public class TestBoardGenerator implements BoardGenerator {

    private final Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> create() {
        return Map.copyOf(board);
    }

    public void put(Position position, Piece piece) {
        board.put(position, piece);
    }
}