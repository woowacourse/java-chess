package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.File.G;
import static chess.domain.board.File.H;
import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.BLACK_PAWN;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static chess.domain.piece.PieceType.WHITE_PAWN;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    private static final Map<Square, Piece> initPiece = Map.ofEntries(
            Map.entry(new Square(A, ONE), new Piece(WHITE, ROOK)),
            Map.entry(new Square(B, ONE), new Piece(WHITE, KNIGHT)),
            Map.entry(new Square(C, ONE), new Piece(WHITE, BISHOP)),
            Map.entry(new Square(D, ONE), new Piece(WHITE, QUEEN)),
            Map.entry(new Square(E, ONE), new Piece(WHITE, KING)),
            Map.entry(new Square(F, ONE), new Piece(WHITE, BISHOP)),
            Map.entry(new Square(G, ONE), new Piece(WHITE, KNIGHT)),
            Map.entry(new Square(H, ONE), new Piece(WHITE, ROOK)),
            Map.entry(new Square(A, TWO), new Piece(WHITE, WHITE_PAWN)),
            Map.entry(new Square(B, TWO), new Piece(WHITE, WHITE_PAWN)),
            Map.entry(new Square(C, TWO), new Piece(WHITE, WHITE_PAWN)),
            Map.entry(new Square(D, TWO), new Piece(WHITE, WHITE_PAWN)),
            Map.entry(new Square(E, TWO), new Piece(WHITE, WHITE_PAWN)),
            Map.entry(new Square(F, TWO), new Piece(WHITE, WHITE_PAWN)),
            Map.entry(new Square(G, TWO), new Piece(WHITE, WHITE_PAWN)),
            Map.entry(new Square(H, TWO), new Piece(WHITE, WHITE_PAWN)),
            Map.entry(new Square(A, SEVEN), new Piece(BLACK, BLACK_PAWN)),
            Map.entry(new Square(B, SEVEN), new Piece(BLACK, BLACK_PAWN)),
            Map.entry(new Square(C, SEVEN), new Piece(BLACK, BLACK_PAWN)),
            Map.entry(new Square(D, SEVEN), new Piece(BLACK, BLACK_PAWN)),
            Map.entry(new Square(E, SEVEN), new Piece(BLACK, BLACK_PAWN)),
            Map.entry(new Square(F, SEVEN), new Piece(BLACK, BLACK_PAWN)),
            Map.entry(new Square(G, SEVEN), new Piece(BLACK, BLACK_PAWN)),
            Map.entry(new Square(H, SEVEN), new Piece(BLACK, BLACK_PAWN)),
            Map.entry(new Square(A, EIGHT), new Piece(BLACK, ROOK)),
            Map.entry(new Square(B, EIGHT), new Piece(BLACK, KNIGHT)),
            Map.entry(new Square(C, EIGHT), new Piece(BLACK, BISHOP)),
            Map.entry(new Square(D, EIGHT), new Piece(BLACK, QUEEN)),
            Map.entry(new Square(E, EIGHT), new Piece(BLACK, KING)),
            Map.entry(new Square(F, EIGHT), new Piece(BLACK, BISHOP)),
            Map.entry(new Square(G, EIGHT), new Piece(BLACK, KNIGHT)),
            Map.entry(new Square(H, EIGHT), new Piece(BLACK, ROOK))
    );

    public static Board create() {
        return new Board(new HashMap<>(initPiece));
    }
}
