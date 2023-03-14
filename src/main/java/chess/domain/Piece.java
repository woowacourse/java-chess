package chess.domain;

import static chess.domain.File.A;
import static chess.domain.File.B;
import static chess.domain.File.C;
import static chess.domain.File.D;
import static chess.domain.File.E;
import static chess.domain.File.F;
import static chess.domain.File.G;
import static chess.domain.File.H;
import static chess.domain.Rank.EIGHT;
import static chess.domain.Rank.ONE;
import static chess.domain.Rank.SEVEN;
import static chess.domain.Rank.TWO;

import java.util.List;

public enum Piece {
    BLACK_KING("k", List.of(Square.of(E, EIGHT))),
    BLACK_QUEEN("q", List.of(Square.of(D, EIGHT))),
    BLACK_ROOK("r", List.of(Square.of(A, EIGHT), Square.of(H, EIGHT))),
    BLACK_KNIGHT("n", List.of(Square.of(B, EIGHT), Square.of(G, EIGHT))),
    BLACK_BISHOP("b", List.of(Square.of(C, EIGHT), Square.of(F, EIGHT))),
    BLACK_PAWN("p", List.of(
            Square.of(A, SEVEN),
            Square.of(B, SEVEN),
            Square.of(C, SEVEN),
            Square.of(D, SEVEN),
            Square.of(E, SEVEN),
            Square.of(F, SEVEN),
            Square.of(G, SEVEN),
            Square.of(H, SEVEN)
    )),

    WHITE_KING("K", List.of(Square.of(E, ONE))),
    WHITE_QUEEN("Q", List.of(Square.of(D, ONE))),
    WHITE_ROOK("R", List.of(Square.of(A, ONE), Square.of(H, ONE))),
    WHITE_KNIGHT("N", List.of(Square.of(B, ONE), Square.of(G, ONE))),
    WHITE_BISHOP("B", List.of(Square.of(C, ONE), Square.of(F, ONE))),
    WHITE_PAWN("P", List.of(
            Square.of(A, TWO),
            Square.of(B, TWO),
            Square.of(C, TWO),
            Square.of(D, TWO),
            Square.of(E, TWO),
            Square.of(F, TWO),
            Square.of(G, TWO),
            Square.of(H, TWO)
            ));
    private final String name;
    private final List<Square> initSquare;

    Piece(final String name, final List<Square> initSquare) {
        this.name = name;
        this.initSquare = initSquare;
    }
}
