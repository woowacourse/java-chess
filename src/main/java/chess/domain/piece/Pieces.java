package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Source;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Pieces {
    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public boolean isBlack() {
        return pieces.stream()
                .allMatch(Piece::isBlack);
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public Optional<Piece> findPiece(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findAny();
    }

    public void move(final Source source, final Target target, final Pieces targetPieces) {
        source.move(target, this, targetPieces);
    }

    public void changePiecePosition(final Piece source, final Target target) {
        source.changePosition(target.getPosition());
    }

    public void remove(final Position position) {
        if (findPiece(position).isPresent()) {
            pieces.remove(findPiece(position).get());
        }
    }

    public boolean isKing(final Position position) {
        if (findPiece(position).isPresent()) {
            return findPiece(position).get().isKing();
        }
        return false;
    }

    public double calculateScore() {
        return this.pieces.stream()
                .mapToDouble(piece -> piece.score(pieces))
                .sum();
    }
}
