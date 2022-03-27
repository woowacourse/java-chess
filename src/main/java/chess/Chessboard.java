package chess;

import chess.piece.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chessboard {

    private final List<Rank> board;

    private Chessboard(List<Rank> board) {
        this.board = board;
    }

    public static Chessboard emptyChessboard() {
        return new Chessboard(new ArrayList<>());
    }

    public static Chessboard initializedChessboard() {
        return new Chessboard();
    }

    private Chessboard() {
        board = Ranks.create();
    }

    public void movePiece(Pair<Integer, Integer> source, Pair<Integer, Integer> target, Turn turn) {
        validate(source, target, turn);

        board.get(target.getLeft()).assignLocationPiece(source, target, board.get(source.getLeft()));
        board.get(source.getLeft()).removeLocationPiece(source);
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
        if (!board.get(source.getLeft()).isExistPiece(source)) {
            throw new IllegalArgumentException("이동하려는 위치에 기물이 없습니다.");
        }
    }

    private void validateTurn(Pair<Integer, Integer> source, Turn turn) {
        if (!board.get(source.getLeft()).isMovableColor(turn, source)) {
            throw new IllegalArgumentException("상대편의 기물은 움직일 수 없습니다.");
        }
    }

    private void validateMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (!isMovable(source, target)) {
            throw new IllegalArgumentException("움직일 수 없는 기물입니다.");
        }
    }

    private void validateMovableBetweenPosition(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        List<Pair<Integer, Integer>> betweenPositions = board.get(source.getLeft()).findBetweenTwoPosition(source, target);
        for (Pair<Integer, Integer> position : betweenPositions) {
            if (!board.get(position.getLeft()).isExistPiece(position)) {
                continue;
            }
            throw new IllegalArgumentException("가로막는 기물이 있습니다.");
        }
    }

    public boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return board.get(source.getLeft()).isMovable(source, target);
    }

    public boolean isKing(Pair<Integer, Integer> target) {
        return board.get(target.getLeft()).isKing(target);
    }

    public double computeScore(Color color) {
        double score = board.stream()
                .mapToDouble(rank -> rank.score(color))
                .sum();

        for (int column = 0; column < 8; column++) {
            score -= 0.5 * countSameColumnPawn(column, color);
        }
        return score;
    }

    private long countSameColumnPawn(int column, Color color) {
        long count = board.stream()
                .filter(rank -> rank.isSameColor(column, color) && rank.isSameType(column, Type.PAWN))
                .count();
        if (count < 2) {
            return 0;
        }
        return count;
    }

    public List<Rank> getBoard() {
        return Collections.unmodifiableList(board);
    }
}
