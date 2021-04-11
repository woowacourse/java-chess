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

    @Override
    public boolean isKing(final Position position) {
        return pieces.isKing(position);
    }

    @Override
    public double calculateScore() {
        return pieces.calculateScore();
    }

    @Override
    public State toRunningTurn() {
        return new RunningTurn(pieces);
    }

    @Override
    public State toFinishedTurn() {
        return new FinishedTurn(pieces);
    }
}
