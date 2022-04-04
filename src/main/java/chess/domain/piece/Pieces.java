package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.PiecesSetup;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Pieces {

    public static final int KINGS_BOTH_ALIVE = 2;

    private final Map<Position, Piece> pieces;

    public Pieces(final PiecesSetup piecesSetup) {
        pieces = piecesSetup.initialize();
    }

    public Optional<Piece> findPiece(final Position position) {
        if (pieces.containsKey(position)) {
            return Optional.of(pieces.get(position));
        }
        return Optional.empty();
    }

    public boolean pieceExist(final Position position) {
        return pieces.containsKey(position);
    }

    public void move(Position source, Position target) {
        Piece piece = pieces.remove(source);
        pieces.put(target, piece);
    }

    public boolean kingCaught() {
        return pieces.values()
                .stream()
                .filter(Piece::isKing)
                .count() != KINGS_BOTH_ALIVE;
    }

    public Piece findAliveKing() {
        return pieces.values()
                .stream()
                .filter(Piece::isKing)
                .findFirst()
                .get();
    }

    public double calculateBasicScore(Color color) {
        return pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    public int countPenaltyPawns(Color color) {
        return Arrays.stream(Column.values())
                .mapToInt(column -> countPawnsOnSameColumn(column, color))
                .filter(count -> count > 1)
                .sum();
    }

    private int countPawnsOnSameColumn(Column column, Color color) {
        return (int) Arrays.stream(Row.values())
                .map(row -> findPiece(Position.valueOf(column, row)))
                .filter(piece -> piece.isPresent() && piece.get().isPawn() && piece.get().isSameColor(color))
                .count();
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
