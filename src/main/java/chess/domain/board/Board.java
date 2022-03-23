package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> squares;

    public Board() {
        this.squares = initialize();
    }

    private Map<Position, Piece> initialize() {
        Map<Position, Piece> squares = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                squares.put(new Position(file, rank), new EmptyPiece());
            }
        }
        return squares;
    }

    public Piece findByPosition(Position position) {
        return squares.get(position);
    }
}
