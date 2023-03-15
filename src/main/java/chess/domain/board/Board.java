package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> boards;

    public Board(Map<Position, Piece> boards) {
        this.boards = boards;
    }

    public Position findSquare(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate) {
        return null;
    }

    public Map<Position, Piece> getBoards() {
        return boards;
    }
}
