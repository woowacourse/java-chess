package domain.board;

import domain.piece.*;

import java.util.HashMap;
import java.util.Map;

import static domain.board.File.D;
import static domain.board.File.E;
import static domain.board.File.A;
import static domain.board.File.B;
import static domain.board.File.C;
import static domain.board.File.F;
import static domain.board.File.G;
import static domain.board.File.H;
import static domain.board.Rank.ONE;
import static domain.board.Rank.TWO;
import static domain.board.Rank.SEVEN;
import static domain.board.Rank.EIGHT;
import static domain.piece.PieceColor.BLACK;
import static domain.piece.PieceColor.WHITE;

public class BoardInitializer {
    private static final Map<Position, Piece> initialPiecePositions = Map.ofEntries(
            Map.entry(position(E, ONE), new King(WHITE)),
            Map.entry(position(D, ONE), new Queen(WHITE)),
            Map.entry(position(C, ONE), new Bishop(WHITE)),
            Map.entry(position(F, ONE), new Bishop(WHITE)),
            Map.entry(position(B, ONE), new Knight(WHITE)),
            Map.entry(position(G, ONE), new Knight(WHITE)),
            Map.entry(position(A, ONE), new Rook(WHITE)),
            Map.entry(position(H, ONE), new Rook(WHITE)),
            Map.entry(position(A, TWO), new Pawn(WHITE)),
            Map.entry(position(B, TWO), new Pawn(WHITE)),
            Map.entry(position(C, TWO), new Pawn(WHITE)),
            Map.entry(position(D, TWO), new Pawn(WHITE)),
            Map.entry(position(E, TWO), new Pawn(WHITE)),
            Map.entry(position(F, TWO), new Pawn(WHITE)),
            Map.entry(position(G, TWO), new Pawn(WHITE)),
            Map.entry(position(H, TWO), new Pawn(WHITE)),
            Map.entry(position(E, EIGHT), new King(BLACK)),
            Map.entry(position(D, EIGHT), new Queen(BLACK)),
            Map.entry(position(C, EIGHT), new Bishop(BLACK)),
            Map.entry(position(F, EIGHT), new Bishop(BLACK)),
            Map.entry(position(B, EIGHT), new Knight(BLACK)),
            Map.entry(position(G, EIGHT), new Knight(BLACK)),
            Map.entry(position(A, EIGHT), new Rook(BLACK)),
            Map.entry(position(H, EIGHT), new Rook(BLACK)),
            Map.entry(position(A, SEVEN), new Pawn(BLACK)),
            Map.entry(position(B, SEVEN), new Pawn(BLACK)),
            Map.entry(position(C, SEVEN), new Pawn(BLACK)),
            Map.entry(position(D, SEVEN), new Pawn(BLACK)),
            Map.entry(position(E, SEVEN), new Pawn(BLACK)),
            Map.entry(position(F, SEVEN), new Pawn(BLACK)),
            Map.entry(position(G, SEVEN), new Pawn(BLACK)),
            Map.entry(position(H, SEVEN), new Pawn(BLACK))
    );

    public static Board initBoard() {
        return new Board(new HashMap<>(initialPiecePositions));
    }

    private static Position position(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
