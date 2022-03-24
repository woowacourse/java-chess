package chess;

import chess.piece.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chessboard {

    private final List<List<Piece>> board;

    public static Chessboard emptyChessboard() {
        return new Chessboard(new ArrayList<>());
    }

    public static Chessboard initializedChessboard() {
        return new Chessboard();
    }

    private Chessboard(List<List<Piece>> board) {
        this.board = board;
    }

    private Chessboard() {
        board = new ArrayList<>();
        initializePieceWithoutPawn(Color.BLACK);
        initializePawn(Color.BLACK);

        for (int i = 0; i < 4; i++) {
            initializeBlank();
        }
        initializePawn(Color.WHITE);
        initializePieceWithoutPawn(Color.WHITE);
    }

    public boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        Piece sourcePiece = board.get(source.getLeft()).get(source.getRight());

        if (isTherePiece(target) && checkPawn(sourcePiece, source, target)) {
            return true;
        }
        return sourcePiece.isMovable(source, target);
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }

    private boolean checkPawn(Piece sourcePiece, Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (!sourcePiece.isSameType(Type.PAWN)) {
            return false;
        }
        Pawn pawn = (Pawn) sourcePiece;

        return pawn.isDiagonal(source, target);
    }

    private boolean isTherePiece(Pair<Integer, Integer> target) {
        return !board.get(target.getLeft()).get(target.getRight()).isSameType(Type.BLANK);
    }

    private void initializePieceWithoutPawn(Color color) {
        List<Piece> line = new ArrayList<>();
        line.add(new Rook(color));
        line.add(new Knight(color));
        line.add(new Bishop(color));
        line.add(new Queen(color));
        line.add(new King(color));
        line.add(new Bishop(color));
        line.add(new Knight(color));
        line.add(new Rook(color));
        board.add(line);
    }

    private void initializePawn(Color color) {
        List<Piece> line = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            line.add(new Pawn(color));
        }
        board.add(line);
    }

    private void initializeBlank() {
        List<Piece> line = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            line.add(new Blank());
        }
        board.add(line);
    }
}
