package chess.domain;

import static chess.domain.square.File.*;
import static chess.domain.square.Rank.*;
import static chess.domain.Team.*;

import chess.domain.piece.Bishop;
import chess.domain.piece.InitialPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.square.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Pieces {
    private static final Map<Square, Piece> pieces = new HashMap<>();

    static {
        generatePieces();
    }

    private Pieces() {}

    public static Map<Square, Piece> init() {
        return new HashMap<>(pieces);
    }

    private static void generatePieces() {
        initPieceExceptPawn(ONE, WHITE);
        initPawn(SEVEN, BLACK);
        initPieceExceptPawn(EIGHT, BLACK);
        initPawn(TWO, WHITE);
    }

    private static void initPieceExceptPawn(final Rank rank, final Team team) {
        pieces.put(Squares.getSquare(E, rank), new King(team));
        pieces.put(Squares.getSquare(D, rank), new Queen(team));
        pieces.put(Squares.getSquare(A, rank), new Rook(team));
        pieces.put(Squares.getSquare(H, rank), new Rook(team));
        pieces.put(Squares.getSquare(B, rank), new Knight(team));
        pieces.put(Squares.getSquare(G, rank), new Knight(team));
        pieces.put(Squares.getSquare(C, rank), new Bishop(team));
        pieces.put(Squares.getSquare(F, rank), new Bishop(team));
    }

    private static void initPawn(final Rank rank, final Team team) {
        Arrays.stream(File.values())
                .forEach(file -> pieces.put(Squares.getSquare(file, rank), new InitialPawn(team)));
    }
}
