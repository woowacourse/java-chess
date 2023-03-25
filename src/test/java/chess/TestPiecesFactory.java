package chess;

import chess.domain.piece.Piece;
import chess.domain.piecesfactory.PiecesFactory;

import java.util.ArrayList;
import java.util.List;

public class TestPiecesFactory implements PiecesFactory {

    private final List<Piece> pieces;

    public TestPiecesFactory(final List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    @Override
    public List<Piece> generate() {
        return pieces;
    }
}
