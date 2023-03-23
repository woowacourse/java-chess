package chess.domain.board;

import chess.domain.Position;
import chess.domain.piece.BlankPiece;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Optional;

public class Pieces {
    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceOrThrow(final Position position) {
        return findPiece(position)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다."));
    }

    private Optional<Piece> findPiece(final Position position) {
        return pieces.stream()
                .filter(piece -> position.equals(piece.getPosition()))
                .findAny();
    }

    public Piece findPieceOrBlank(final Position position) {
        return findPiece(position)
                .orElseGet(() -> new BlankPiece(position.getFile(), position.getRank()));
    }

    public boolean hasPiece(List<Position> pathPositions) {
        return pieces.stream()
                .anyMatch(piece -> piece.existsIn(pathPositions));
    }

    public void add(final Piece piece) {
        pieces.add(piece);
    }

    public void remove(final Piece piece) {
        pieces.remove(piece);
    }

    public boolean isEmpty() {
        return pieces.isEmpty();
    }

    public boolean hasTwoKings() {
        return 2 == pieces.stream()
                .filter(Piece::isKing)
                .count();
    }

    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }
}
