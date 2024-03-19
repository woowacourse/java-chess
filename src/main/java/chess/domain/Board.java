package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PiecesFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private List<Piece> pieces;

    public Board() {
        this.pieces = initialize();
    }

    private List<Piece> initialize() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(PiecesFactory.createBlackPieces());
        pieces.addAll(PiecesFactory.createBlackPawns());
        pieces.addAll(PiecesFactory.createEmptyPieces());
        pieces.addAll(PiecesFactory.createWhitePawns());
        pieces.addAll(PiecesFactory.createWhitePieces());

        return pieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
