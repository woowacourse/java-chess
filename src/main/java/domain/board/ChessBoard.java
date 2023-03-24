package domain.board;

import static domain.piece.Camp.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.empty.Empty;
import domain.piece.jumper.Knight;
import domain.piece.pawn.Pawn;
import domain.piece.slider.Bishop;
import domain.piece.slider.King;
import domain.piece.slider.Queen;
import domain.piece.slider.Rook;

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
            board.put(new Square(initialBishopFile, Rank.ONE), new Bishop(WHITE));
            board.put(new Square(initialBishopFile, Rank.EIGHT), new Bishop(BLACK));
        }
    }

    private void initializeKnights() {
        List<File> initialKnightFiles = List.of(File.B, File.G);
        for (File initialKnightFile : initialKnightFiles) {
            board.put(new Square(initialKnightFile, Rank.ONE), new Knight(WHITE));
            board.put(new Square(initialKnightFile, Rank.EIGHT), new Knight(BLACK));
        }
    }

    private void initializeRooks() {
        List<File> initialRookFiles = List.of(File.A, File.H);
        for (File initialRookFile : initialRookFiles) {
            board.put(new Square(initialRookFile, Rank.ONE), new Rook(WHITE));
            board.put(new Square(initialRookFile, Rank.EIGHT), new Rook(BLACK));
        }
    }

    public void move(Square currentSquare, Square targetSquare) {
        Piece currentPiece = board.get(currentSquare);
        List<Square> path = currentPiece.fetchMovableSquares(currentSquare, targetSquare);
        Map<Square, Piece> pathInfo = path.stream()
            .collect(Collectors.toMap(Function.identity(), board::get));
        if (currentPiece.canMove(pathInfo, targetSquare)) {
            board.put(targetSquare, currentPiece);
            board.put(currentSquare, Empty.getInstance());
        }
    }

    public Map<Square, Piece> getBoard() {
        return board;
    }

    public boolean isCorrectCamp(Camp currentCamp, Square currentSquare) {
        return (currentCamp == WHITE) == board.get(currentSquare).isWhite();
    }
}
