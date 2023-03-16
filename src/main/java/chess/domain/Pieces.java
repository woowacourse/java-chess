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
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.InitialPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Pieces {
    private final Map<Square, Piece> pieces = new HashMap<>();

    private Pieces() {
        generatePieces();
    }

    public static Map<Square, Piece> init() {
        Pieces pieces = new Pieces();
        return new HashMap<>(pieces.pieces);
    }

    private void generatePieces() {
        initPieceExceptPawn(ONE, WHITE);
        initPawn(SEVEN, BLACK);
        initPieceExceptPawn(EIGHT, BLACK);
        initPawn(TWO, WHITE);
    }

    private void initPieceExceptPawn(final Rank rank, final Color color) {
        pieces.put(Squares.getSquare(E, rank), new King(color));
        pieces.put(Squares.getSquare(D, rank), new Queen(color));
        pieces.put(Squares.getSquare(A, rank), new Rook(color));
        pieces.put(Squares.getSquare(H, rank), new Rook(color));
        pieces.put(Squares.getSquare(B, rank), new Knight(color));
        pieces.put(Squares.getSquare(G, rank), new Knight(color));
        pieces.put(Squares.getSquare(C, rank), new Bishop(color));
        pieces.put(Squares.getSquare(F, rank), new Bishop(color));
    }

    private void initPawn(final Rank rank, final Color color) {
        Arrays.stream(File.values())
                .forEach(file -> pieces.put(Squares.getSquare(file, rank), new InitialPawn(color)));
    }
}
