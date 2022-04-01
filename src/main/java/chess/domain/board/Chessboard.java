package chess.domain.board;

import chess.domain.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chessboard {

    public static final List<Integer> SIZE = IntStream.range(0, 8)
            .boxed()
            .collect(Collectors.toList());
    private static final double EXIST_PAWN_SAME_COLUMN = 0.5;
    private static final int DUPLICATE = 2;
    private static final String ERROR_SOURCE_TARGET_SAME_POSITION = "현재 위치와 같은 위치로 이동할 수 없습니다.";
    private static final String ERROR_EMPTY_SOURCE_PIECE = "이동하려는 위치에 기물이 없습니다.";
    private static final String ERROR_CATCH_PIECE_SAME_TEAM = "같은편의 기물을 공격할 수 없습니다.";
    private static final String ERROR_MOVE_OPPOSITE_TEAM_PIECE = "상대편의 기물은 움직일 수 없습니다.";
    private static final String ERROR_IMPOSSIBLE_MOVE = "해당 위치로 이동할 수 없습니다.";

    private final Map<Position, Piece> board;

    public Chessboard(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Chessboard create() {
        return new Chessboard(BoardCache.create());
    }

    public void movePiece(Position source, Position target, Turn turn) {
        if (board.containsKey(target)) {
            validateSamePosition(source, target);
            validateSameTeam(source, target);
        }
        validateBlank(source);
        validateTurn(source, turn);
        validateMovable(source, target);

        board.put(target, board.get(source));
        board.remove(source);
    }

    private void validateSamePosition(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException(ERROR_SOURCE_TARGET_SAME_POSITION);
        }
    }

    private void validateBlank(Position source) {
        if (!board.containsKey(source)) {
            throw new IllegalArgumentException(ERROR_EMPTY_SOURCE_PIECE);
        }
    }

    private void validateTurn(Position source, Turn turn) {
        if (!turn.isRightTurn(board.get(source).getColor())) {
            throw new IllegalArgumentException(ERROR_MOVE_OPPOSITE_TEAM_PIECE);
        }
    }

    private void validateSameTeam(Position source, Position target) {
        if (board.get(source).isColor(board.get(target))) {
            throw new IllegalArgumentException(ERROR_CATCH_PIECE_SAME_TEAM);
        }
    }

    private void validateMovable(Position source, Position target) {
        if (!isMovablePosition(source, target)) {
            throw new IllegalArgumentException(ERROR_IMPOSSIBLE_MOVE);
        }
    }

    public boolean isMovablePosition(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        if (isExistPiece(target) && isPawnMovable(sourcePiece, source, target)) {
            return true;
        }
        return sourcePiece.isMovablePosition(source, target, board);
    }

    private boolean isPawnMovable(Piece sourcePiece, Position source, Position target) {
        if (!sourcePiece.isSameType(Pawn.class)) {
            return false;
        }
        Pawn pawn = (Pawn) sourcePiece;
        return pawn.isMovableDiagonal(source, target);
    }

    private boolean isExistPiece(Position target) {
        return board.containsKey(target);
    }

    public boolean isExistPosition(int row, int column) {
        return board.keySet()
                .stream()
                .anyMatch(position -> position.isSamePosition(row, column));
    }

    public boolean isKing(Position target) {
        return board.get(target).isSameType(King.class);
    }

    public double computeScore(Color color) {
        double score = board.keySet()
                .stream()
                .filter(piece -> board.get(piece).isColor(color))
                .mapToDouble(piece -> board.get(piece).getScore())
                .sum();

        for (int column = 0; column < SIZE.size(); column++) {
            score -= EXIST_PAWN_SAME_COLUMN * countSameColumnPawn(column, color);
        }
        return score;
    }

    private long countSameColumnPawn(int column, Color color) {
        long count = SIZE.stream()
                .filter(row -> board.containsKey(new Position(row, column)))
                .filter(row -> board.get(new Position(row, column)).isColor(color)
                        && board.get(new Position(row, column)).isSameType(Pawn.class))
                .count();
        if (count < DUPLICATE) {
            return 0;
        }
        return count;
    }

    public Piece getPiece(int row, int column) {
        return board.keySet()
                .stream()
                .filter(position -> position.isSamePosition(row, column))
                .map(board::get)
                .findAny()
                .orElseThrow();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
