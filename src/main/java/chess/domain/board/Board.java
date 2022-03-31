package chess.domain.board;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.piece.Team;
import java.util.Map;

public class Board {
    public static final int BOTH_KING_ALIVE = 2;

    private final Map<Coordinate, Piece> value;

    private Board(Map<Coordinate, Piece> value) {
        this.value = value;
    }

    public static Board create() {
        return new Board(InitialBoard.initialize());
    }

    public Board move(String from, String to) {
        return move(Coordinate.of(from), Coordinate.of(to));
    }

    public Board move(Coordinate from, Coordinate to) {
        Piece piece = findPiece(from);
        if (piece.isEmpty()) {
            throw new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다.");
        }
        if (!piece.isMovable(this, from, to)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }

        swapPiece(from, to);
        return this;
    }

    public Piece findPiece(Coordinate coordinate) {
        return value.get(coordinate);
    }

    private void swapPiece(Coordinate from, Coordinate to) {
        Piece piece = findPiece(from);
        value.put(from, new Empty(Symbol.EMPTY, Team.NONE));
        value.put(to, piece);
    }

    public boolean isBothKingAlive() {
        return value.values()
                .stream()
                .filter(Piece::isKing)
                .count() == BOTH_KING_ALIVE;
    }

    public Map<Coordinate, Piece> getValue() {
        return value;
    }
}
