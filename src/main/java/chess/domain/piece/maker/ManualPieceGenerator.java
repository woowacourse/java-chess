package chess.domain.piece.maker;

import chess.domain.piece.Piece;

import java.util.Set;

public class ManualPieceGenerator implements PiecesGenerator {

    private final Set<Piece> pieces;

    public ManualPieceGenerator(final Set<Piece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public Set<Piece> generate() {
        return pieces;
    }
}
