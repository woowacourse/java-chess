package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rank {

    private final Map<Position, Piece> squares;

    public Rank(final Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public boolean hasPosition(final Position position) {
        return this.squares.containsKey(position);
    }

    public void replacePiece(final Position position, final Piece piece) {
        this.squares.replace(position, piece);
    }

    public Piece piece(Position position) {
        return this.squares.get(position);
    }

    public List<Piece> pieces() {
        return new ArrayList<>(this.squares.values());
    }

    public Map<Position, Piece> squares() {
        return this.squares;
    }
}
