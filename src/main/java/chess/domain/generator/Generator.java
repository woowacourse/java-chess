package chess.domain.generator;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class Generator {

    private static final int PAWN_COUNT_PER_PLAYER = 8;

    private final int pawnRank;
    private final int defaultRank;

    public Generator(final int pawnRank, final int defaultRank) {
        this.pawnRank = pawnRank;
        this.defaultRank = defaultRank;
    }

    public List<Piece> generate() {
        final List<Piece> pieces = new ArrayList<>();
        createPawn(pieces);
        createRook(pieces);
        createKnight(pieces);
        createBishop(pieces);
        createQueen(pieces);
        createKing(pieces);
        return pieces;
    }

    void createPawn(final List<Piece> pieces) {
        for (int i = 0; i < PAWN_COUNT_PER_PLAYER; i++) {
            pieces.add(new Pawn(new Position(pawnRank, (char) ('a' + i))));
        }
    }

    private void createRook(final List<Piece> pieces) {
        pieces.add(new Rook(new Position(defaultRank, 'a')));
        pieces.add(new Rook(new Position(defaultRank, 'h')));
    }

    private void createKnight(final List<Piece> pieces) {
        pieces.add(new Knight(new Position(defaultRank, 'b')));
        pieces.add(new Knight(new Position(defaultRank, 'g')));
    }

    private void createBishop(final List<Piece> pieces) {
        pieces.add(new Bishop(new Position(defaultRank, 'c')));
        pieces.add(new Bishop(new Position(defaultRank, 'f')));
    }

    private void createQueen(final List<Piece> pieces) {
        pieces.add(new Queen(new Position(defaultRank, 'd')));
    }

    private void createKing(final List<Piece> pieces) {
        pieces.add(new King(new Position(defaultRank, 'e')));
    }
}
