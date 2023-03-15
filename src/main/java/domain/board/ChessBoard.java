package domain.board;

import static domain.piece.Camp.BLACK;
import static domain.piece.Camp.WHITE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.piece.Piece;
import domain.piece.type.Bishop;
import domain.piece.type.Empty;
import domain.piece.type.King;
import domain.piece.type.Knight;
import domain.piece.type.Pawn;
import domain.piece.type.Queen;
import domain.piece.type.Rook;

public class ChessBoard {
    private Map<Square, Piece> board = new HashMap<>();

    public ChessBoard() {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(new Square(file, rank), new Empty());
            }
        }
    }

    public void initialize() {

        List<File> initialRookFiles = List.of(File.A, File.H);
        List<File> initialKnightFiles = List.of(File.B, File.G);
        List<File> initialBishopFiles = List.of(File.C, File.F);
        Rank WHITE_WITHOUT_PAWN_RANK = Rank.ONE;
        Rank BLACK_WITHOUT_PAWN_RANK = Rank.EIGHT;
        Rank WHITE_PAWN_RANK = Rank.TWO;
        Rank BLACK_PAWN_RANK = Rank.SEVEN;

        List<Square> initialWhitePawnSquares = new ArrayList<>();
        List<Square> initialBlackPawnSquares = new ArrayList<>();

        for (File value : File.values()) {
            initialWhitePawnSquares.add(new Square(value, WHITE_PAWN_RANK));
        }

        for (File value : File.values()) {
            initialBlackPawnSquares.add(new Square(value, BLACK_PAWN_RANK));
        }

        // 룩
        for (File initialRookFile : initialRookFiles) {
            Square square = new Square(initialRookFile, WHITE_WITHOUT_PAWN_RANK);
            board.put(square, new Rook(WHITE));
        }

        for (File initialRookFile : initialRookFiles) {
            Square square = new Square(initialRookFile, BLACK_WITHOUT_PAWN_RANK);
            board.put(square, new Rook(BLACK));
        }

        // 나이트
        for (File initialKnightFile : initialKnightFiles) {
            Square square = new Square(initialKnightFile, WHITE_WITHOUT_PAWN_RANK);
            board.put(square, new Knight(WHITE));
        }

        for (File initialKnightFile : initialKnightFiles) {
            Square square = new Square(initialKnightFile, BLACK_WITHOUT_PAWN_RANK);
            board.put(square, new Knight(BLACK));
        }

        // 비숍
        for (File initialBishopFile : initialBishopFiles) {
            Square square = new Square(initialBishopFile, WHITE_WITHOUT_PAWN_RANK);
            board.put(square, new Bishop(WHITE));
        }
        for (File initialBishopFile : initialBishopFiles) {
            Square square = new Square(initialBishopFile, BLACK_WITHOUT_PAWN_RANK);
            board.put(square, new Bishop(BLACK));
        }

        board.put(new Square(File.D, WHITE_WITHOUT_PAWN_RANK), new Queen(WHITE));
        board.put(new Square(File.D, BLACK_WITHOUT_PAWN_RANK), new Queen(BLACK));
        board.put(new Square(File.E, WHITE_WITHOUT_PAWN_RANK), new King(WHITE));
        board.put(new Square(File.E, BLACK_WITHOUT_PAWN_RANK), new King(BLACK));

        for (Square initialWhitePawnSquare : initialWhitePawnSquares) {
            board.put(initialWhitePawnSquare, new Pawn(WHITE));
        }
        for (Square initialBlackPawnSquare : initialBlackPawnSquares) {
            board.put(initialBlackPawnSquare, new Pawn(BLACK));
        }
    }

    public Square toSquare(int fileCoordinate, int rankCoordinate) {
        File targetFile = Arrays.stream(File.values())
                .filter(file -> file.ordinal() == fileCoordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("adfa"));

        Rank targetRank = Arrays.stream(Rank.values())
                .filter(rank -> rank.ordinal() == rankCoordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("adfa"));

        return new Square(targetFile, targetRank);
    }

    public Map<Square, Piece> getBoard() {
        return board;
    }
}
