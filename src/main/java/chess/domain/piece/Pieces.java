package chess.domain.piece;

import static chess.domain.piece.PieceType.*;
import static chess.domain.square.File.*;
import static chess.domain.square.Rank.*;
import static chess.domain.Team.*;

import chess.domain.Team;
import chess.domain.square.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pieces {
    private static final List<Piece> cache = new ArrayList<>();
    private static final Map<Square, Piece> initPieces = new HashMap<>();

    static {
        createPieces();
        generatePieces();
    }

    private Pieces() {}

    public static Map<Square, Piece> init() {
        return new HashMap<>(initPieces);
    }

    private static void generatePieces() {
        initPieceExceptPawn(ONE, WHITE);
        initPawn(SEVEN, BLACK);
        initPieceExceptPawn(EIGHT, BLACK);
        initPawn(TWO, WHITE);
    }

    private static void initPieceExceptPawn(final Rank rank, final Team team) {
        initPieces.put(Squares.of(E, rank), of(team, KING));
        initPieces.put(Squares.of(D, rank), of(team, QUEEN));
        initPieces.put(Squares.of(A, rank), of(team, ROOK));
        initPieces.put(Squares.of(H, rank), of(team, ROOK));
        initPieces.put(Squares.of(B, rank), of(team, KNIGHT));
        initPieces.put(Squares.of(G, rank), of(team, KNIGHT));
        initPieces.put(Squares.of(C, rank), of(team, BISHOP));
        initPieces.put(Squares.of(F, rank), of(team, BISHOP));
    }

    private static void initPawn(final Rank rank, final Team team) {
        Arrays.stream(File.values())
                .forEach(file -> initPieces.put(Squares.of(file, rank), of(team, INITIAL_PAWN)));
    }

    private static void createPieces() {
        cache.add(new King(BLACK));
        cache.add(new Rook(BLACK));
        cache.add(new Queen(BLACK));
        cache.add(new Knight(BLACK));
        cache.add(new Bishop(BLACK));
        cache.add(new Pawn(BLACK));
        cache.add(new InitialPawn(BLACK));
        cache.add(new King(WHITE));
        cache.add(new Rook(WHITE));
        cache.add(new Queen(WHITE));
        cache.add(new Knight(WHITE));
        cache.add(new Bishop(WHITE));
        cache.add(new Pawn(WHITE));
        cache.add(new InitialPawn(WHITE));
    }

    public static Piece of(Team team, PieceType pieceType) {
        return cache.stream()
                .filter(piece -> piece.isSameType(pieceType) && piece.team() == team)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력한 조건의 Piece가 없습니다."));
    }
}
