package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import chess.domain.position.Source;
import chess.domain.position.Target;

import java.util.Optional;

public abstract class Turn implements State {
    private final Pieces pieces;

    protected Turn(final Pieces pieces) {
        this.pieces = pieces;
    }

    @Override
    public State move(Source source, Target target) {
        pieces.move(source, target);
        return null;
    }

    @Override
    public Optional<Piece> findPiece(final Position position) {
        return pieces.findPiece(position);
    }

    @Override
    public Pieces pieces() {
        return pieces;
    }
}
