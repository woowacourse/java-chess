package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.Optional;

public abstract class Turn implements State {
    private final Pieces pieces;

    protected Turn(final Pieces pieces) {
        this.pieces = pieces;
    }

    @Override
    public Optional<Piece> findPiece(final Position position) {
        return pieces.findPiece(position);
    }

    @Override
    public final Pieces pieces() {
        return pieces;
    }

    @Override
    public void removePiece(final Position position) {
        pieces.remove(position);
    }
}
