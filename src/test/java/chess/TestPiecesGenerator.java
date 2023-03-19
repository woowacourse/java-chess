package chess;

import chess.domain.piece.maker.PiecesGenerator;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class TestPiecesGenerator implements PiecesGenerator {

    private final List<Piece> pieces;

    public TestPiecesGenerator(final List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    @Override
    public List<Piece> generate() {
        return pieces;
    }
}
