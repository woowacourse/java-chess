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

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    private static final Map<Square, Piece> initPiece = Map.ofEntries(
            Map.entry(new Square(A, ONE), new Rook(WHITE)),
            Map.entry(new Square(B, ONE), new Knight(WHITE)),
            Map.entry(new Square(C, ONE), new Bishop(WHITE)),
            Map.entry(new Square(D, ONE), new Queen(WHITE)),
            Map.entry(new Square(E, ONE), new King(WHITE)),
            Map.entry(new Square(F, ONE), new Bishop(WHITE)),
            Map.entry(new Square(G, ONE), new Knight(WHITE)),
            Map.entry(new Square(H, ONE), new Rook(WHITE)),
            Map.entry(new Square(A, TWO), new Pawn(WHITE)),
            Map.entry(new Square(B, TWO), new Pawn(WHITE)),
            Map.entry(new Square(C, TWO), new Pawn(WHITE)),
            Map.entry(new Square(D, TWO), new Pawn(WHITE)),
            Map.entry(new Square(E, TWO), new Pawn(WHITE)),
            Map.entry(new Square(F, TWO), new Pawn(WHITE)),
            Map.entry(new Square(G, TWO), new Pawn(WHITE)),
            Map.entry(new Square(H, TWO), new Pawn(WHITE)),
            Map.entry(new Square(A, SEVEN), new Pawn(BLACK)),
            Map.entry(new Square(B, SEVEN), new Pawn(BLACK)),
            Map.entry(new Square(C, SEVEN), new Pawn(BLACK)),
            Map.entry(new Square(D, SEVEN), new Pawn(BLACK)),
            Map.entry(new Square(E, SEVEN), new Pawn(BLACK)),
            Map.entry(new Square(F, SEVEN), new Pawn(BLACK)),
            Map.entry(new Square(G, SEVEN), new Pawn(BLACK)),
            Map.entry(new Square(H, SEVEN), new Pawn(BLACK)),
            Map.entry(new Square(A, EIGHT), new Rook(BLACK)),
            Map.entry(new Square(B, EIGHT), new Knight(BLACK)),
            Map.entry(new Square(C, EIGHT), new Bishop(BLACK)),
            Map.entry(new Square(D, EIGHT), new Queen(BLACK)),
            Map.entry(new Square(E, EIGHT), new King(BLACK)),
            Map.entry(new Square(F, EIGHT), new Bishop(BLACK)),
            Map.entry(new Square(G, EIGHT), new Knight(BLACK)),
            Map.entry(new Square(H, EIGHT), new Rook(BLACK))
    );

    public static Board create() {
        return new Board(new HashMap<>(initPiece));
    }
}
