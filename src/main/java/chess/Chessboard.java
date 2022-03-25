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

    public double computeScore(Color color) {
        double score = 0;

        for (List<Piece> list : board) {
            score += list.stream()
                    .filter(p -> p.getColor() == color)
                    .mapToDouble(p -> p.getType().getScore())
                    .sum();
        }

        for (int i = 0; i < 8; i++) {
            int duplicatedPawn = computePawnCount(i, color);
            if (computePawnCount(i, color) >= 2) {
                score -= 0.5 * duplicatedPawn;
            }
        }
        return score;
    }

    private int computePawnCount(int i, Color color) {
        return (int) board.stream()
                .map(l -> l.get(i))
                .filter(p -> (p.getColor() == color) && p.isSameType(Type.PAWN))
                .count();
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

    public boolean movePiece(Pair<Integer, Integer> source, Pair<Integer, Integer> target, Turn turn) {
        validate(source, target, turn);
        boolean isKing = board.get(target.getLeft()).get(target.getRight()).isSameType(Type.KING);
        board.get(target.getLeft()).set(target.getRight(), board.get(source.getLeft()).get(source.getRight()));
        board.get(source.getLeft()).set(source.getRight(), new Blank());

        return isKing;
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

    private boolean isTherePiece(Pair<Integer, Integer> target) {
        return !board.get(target.getLeft()).get(target.getRight()).isSameType(Type.BLANK);
    }

    private boolean checkPawn(Piece sourcePiece, Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (!sourcePiece.isSameType(Type.PAWN)) {
            return false;
        }
        Pawn pawn = (Pawn) sourcePiece;
        return pawn.isDiagonal(source, target);
    }

    private void validate(Pair<Integer, Integer> source, Pair<Integer, Integer> target, Turn turn) {
        validateSamePosition(source, target);
        validateBlank(source);
        validateTurn(source, turn);
        validateMovable(source, target);
        validateMovableBetweenPosition(source, target);
    }

    private void validateSamePosition(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (source.getLeft() == target.getLeft() && source.getRight() == target.getRight()) {
            throw new IllegalArgumentException("현재 위치와 같은 위치로 이동할 수 없습니다.");
        }
    }

    private void validateBlank(Pair<Integer, Integer> source) {
        if (board.get(source.getLeft()).get(source.getRight()).getType() == Type.BLANK) {
            throw new IllegalArgumentException("이동하려는 위치에 기물이 없습니다.");
        }
    }

    private void validateTurn(Pair<Integer, Integer> source, Turn turn) {
        if (!turn.isRightTurn(board.get(source.getLeft()).get(source.getRight()).getColor())) {
            throw new IllegalArgumentException("상대편의 기물은 움직일 수 없습니다.");
        }
    }

    private void validateMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (!isMovable(source, target)) {
            throw new IllegalArgumentException("움직일 수 없는 기물입니다.");
        }
    }

    private void validateMovableBetweenPosition(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        List<Pair<Integer, Integer>> betweenPositions = board.get(source.getLeft()).get(source.getRight())
                .computeBetweenTwoPosition(source, target);

        for (Pair<Integer, Integer> position : betweenPositions) {
            if (board.get(position.getLeft()).get(position.getRight()).getType() == Type.BLANK) {
                System.out.println(board.get(position.getLeft()).get(position.getRight()).getType());
                continue;
            }
            throw new IllegalArgumentException("가로막는 기물이 있습니다.");
        }
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }
}
