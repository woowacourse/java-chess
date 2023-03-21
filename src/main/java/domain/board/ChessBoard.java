package domain.board;

import static domain.piece.Camp.BLACK;
import static domain.piece.Camp.WHITE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.type.Bishop;
import domain.piece.type.Empty;
import domain.piece.type.King;
import domain.piece.type.Knight;
import domain.piece.type.Pawn;
import domain.piece.type.Queen;
import domain.piece.type.Rook;

public class ChessBoard {
    private final Map<Square, Piece> board;

    public ChessBoard() {
        board = new HashMap<>();
        for (File file : File.values()) {
            setRank(file);
        }
    }

    private void setRank(File file) {
        for (Rank rank : Rank.values()) {
            board.put(new Square(file, rank), Empty.getInstance());
        }
    }

    public void initialize() {
        initializePawns();
        initializeKings();
        initializeQueens();
        initializeBishops();
        initializeKnights();
        initializeRooks();
    }

    private void initializePawns() {
        List<Square> initialWhitePawnSquares = new ArrayList<>();
        List<Square> initialBlackPawnSquares = new ArrayList<>();

        for (File value : File.values()) {
            initialWhitePawnSquares.add(new Square(value, Rank.TWO));
        }
        for (File value : File.values()) {
            initialBlackPawnSquares.add(new Square(value, Rank.SEVEN));
        }
        for (Square initialWhitePawnSquare : initialWhitePawnSquares) {
            board.put(initialWhitePawnSquare, new Pawn(WHITE));
        }
        for (Square initialBlackPawnSquare : initialBlackPawnSquares) {
            board.put(initialBlackPawnSquare, new Pawn(BLACK));
        }
    }

    private void initializeKings() {
        board.put(new Square(File.E, Rank.ONE), new King(WHITE));
        board.put(new Square(File.E, Rank.EIGHT), new King(BLACK));
    }

    private void initializeQueens() {
        board.put(new Square(File.D, Rank.ONE), new Queen(WHITE));
        board.put(new Square(File.D, Rank.EIGHT), new Queen(BLACK));
    }

    private void initializeBishops() {
        List<File> initialBishopFiles = List.of(File.C, File.F);
        for (File initialBishopFile : initialBishopFiles) {
            Square square = new Square(initialBishopFile, Rank.ONE);
            board.put(square, new Bishop(WHITE));
        }
        for (File initialBishopFile : initialBishopFiles) {
            Square square = new Square(initialBishopFile, Rank.EIGHT);
            board.put(square, new Bishop(BLACK));
        }
    }

    private void initializeKnights() {
        List<File> initialKnightFiles = List.of(File.B, File.G);

        for (File initialKnightFile : initialKnightFiles) {
            Square square = new Square(initialKnightFile, Rank.ONE);
            board.put(square, new Knight(WHITE));
        }

        for (File initialKnightFile : initialKnightFiles) {
            Square square = new Square(initialKnightFile, Rank.EIGHT);
            board.put(square, new Knight(BLACK));
        }
    }

    private void initializeRooks() {
        List<File> initialRookFiles = List.of(File.A, File.H);

        for (File initialRookFile : initialRookFiles) {
            Square square = new Square(initialRookFile, Rank.ONE);
            board.put(square, new Rook(WHITE));
        }

        for (File initialRookFile : initialRookFiles) {
            Square square = new Square(initialRookFile, Rank.EIGHT);
            board.put(square, new Rook(BLACK));
        }
    }

    public Square toSquare(int fileCoordinate, int rankCoordinate) {
        File targetFile = File.findFile(fileCoordinate);
        Rank targetRank = Rank.findRank(rankCoordinate);
        return new Square(targetFile, targetRank);
    }

    public void move(Square currentSquare, Square targetSquare) {
        Piece currentPiece = board.get(currentSquare);
        List<Square> path = currentPiece.fetchMovePath(currentSquare, targetSquare);
        Map<Square, Camp> pathInfo = path.stream()
                .collect(Collectors.toMap(Function.identity(), square -> board.get(square).getCamp()));
        if (currentPiece.canMove(pathInfo, targetSquare)) {
            board.put(targetSquare, currentPiece);
            board.put(currentSquare, Empty.getInstance());
            return;
        }
        throw new IllegalStateException("움직일 수 없는 경로입니다.");
    }

    public Map<Square, Piece> getBoard() {
        return board;
    }

    public Square findSquare(String file, String rank) {
        return new Square(File.findFile(file.charAt(0)), Rank.findRank(rank.charAt(0)));
    }

    public boolean isCorrectCamp(Camp currentCamp, Square currentSquare) {
        return currentCamp.equals(board.get(currentSquare).getCamp());
    }
}
