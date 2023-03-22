package chess;

import chess.domain.piece.Piece;
import chess.domain.piece.maker.PiecesGenerator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TestPiecesGenerator implements PiecesGenerator {

    private final Set<Piece> pieces;

    public TestPiecesGenerator(final Collection<Piece> pieces) {
        this.pieces = new HashSet<>(pieces);
    }

    @Override
    public Set<Piece> generate() {
        return pieces;
    }
}
