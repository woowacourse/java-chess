package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;

public class TestUtils {
    static final Piece WHITE_QUEEN = Piece.from(File.D, Rank.ONE);
    static final Piece BLACK_QUEEN = Piece.from(File.D, Rank.EIGHT);
    static final Piece WHITE_BISHOP = Piece.from(File.C, Rank.ONE);
    static final Piece WHITE_PAWN = Piece.from(File.A, Rank.TWO);
    static final Piece WHITE_ROOK = Piece.from(File.A, Rank.ONE);
}
