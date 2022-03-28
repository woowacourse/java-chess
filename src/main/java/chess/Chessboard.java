package chess;

import chess.piece.*;
import chess.position.PieceCache;
import chess.position.Position;
import chess.position.PositionCache;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chessboard {

    public static final List<Integer> SIZE = IntStream.range(0, 8)
            .boxed()
            .collect(Collectors.toList());

    private final Map<Position, Piece> board;

    private Chessboard(Map<Position, Piece> pieces) {
        this.board = pieces;
    }

    private Chessboard() {
        board = new LinkedHashMap<>();
        Map<String, Piece> pieces = PieceCache.create();
        Map<String, Position> positions = PositionCache.create();
        for (String position : positions.keySet()) {
            board.put(positions.get(position), pieces.get(position));
        }
    }

    public static Chessboard emptyChessboard() {
        return new Chessboard(new LinkedHashMap<>());
    }

    public static Chessboard initializedChessboard() {
        return new Chessboard();
    }

    public void movePiece(Position source, Position target, Turn turn) {
        validate(source, target, turn);
        board.put(target, board.get(source));
        board.put(source, new Blank());
    }

    private void validate(Position source, Position target, Turn turn) {
        validateSamePosition(source, target);
        validateBlank(source);
        validateTurn(source, turn);
        validateMovable(source, target);
        validateMovableBetweenPosition(source, target);
    }

    private void validateSamePosition(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("현재 위치와 같은 위치로 이동할 수 없습니다.");
        }
    }

    private void validateBlank(Position source) {
        if (board.get(source).isSameType(Type.BLANK)) {
            throw new IllegalArgumentException("이동하려는 위치에 기물이 없습니다.");
        }
    }

    private void validateTurn(Position source, Turn turn) {
        if (!turn.isRightTurn(board.get(source).getColor())) {
            throw new IllegalArgumentException("상대편의 기물은 움직일 수 없습니다.");
        }
    }

    private void validateMovable(Position source, Position target) {
        if (!isMovable(source, target)) {
            throw new IllegalArgumentException("움직일 수 없는 기물입니다.");
        }
    }

    private void validateMovableBetweenPosition(Position source, Position target) {
        List<Position> betweenPositions = findBetweenTwoPosition(source, target);
        for (Position position : betweenPositions) {
            if (!isExistPiece(position)) {
                continue;
            }
            throw new IllegalArgumentException("가로막는 기물이 있습니다.");
        }
    }

    public List<Position> findBetweenTwoPosition(Position source, Position target) {
        return board.get(source)
                .computeBetweenTwoPosition(source, target);
    }

    public boolean isMovable(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        if (isExistPiece(target) && isPawn(sourcePiece, source, target)) {
            return true;
        }
        return sourcePiece.isMovable(source, target);
    }

    public boolean isExistPiece(Position target) {
        return !board.get(target).isSameType(Type.BLANK);
    }

    private boolean isPawn(Piece sourcePiece, Position source, Position target) {
        if (!sourcePiece.isSameType(Type.PAWN)) {
            return false;
        }
        Pawn pawn = (Pawn) sourcePiece;
        return pawn.isDiagonal(source, target);
    }

    public boolean isKing(Position target) {
        return board.get(target).isSameType(Type.KING);
    }

    public double computeScore(Color color) {
        double score = board.keySet()
                .stream()
                .filter(piece -> board.get(piece).isColor(color))
                .mapToDouble(piece -> board.get(piece).getType().getScore())
                .sum();

        for (int column = 0; column < SIZE.size(); column++) {
            score -= 0.5 * countSameColumnPawn(column, color);
        }
        return score;
    }

    private long countSameColumnPawn(int column, Color color) {
        long count = SIZE.stream()
                .filter(num -> board.get(new Position(num, column)).isColor(color)
                        && board.get(new Position(num, column)).isSameType(Type.PAWN)).count();
        if (count < 2) {
            return 0;
        }
        return count;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
