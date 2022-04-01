package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Board {

    private static final String NOT_EXIST_PIECE = "[ERROR] 입력한 위치에 말이 존재하지 않습니다.";
    private static final String NON_MOVABLE_POSITION = "[ERROR] 해당 위치는 말이 움직일 수 없습니다.";
    private static final String NON_MOVABLE_ROUTE = "[ERROR] 해당 위치로 말이 도달할 수 없습니다.";
    private static final String NON_CATCHABLE_PIECE = "[ERROR] 잡을 수 없는 말 입니다.";
    private static final String ALL_KING_NOT_EXIST = "[ERROR] 킹이 보드에 존재하지 않습니다.";
    private static final double PAWN_SCORE_IN_SAME_FILE = 0.5;
    private static final int KING_COUNT = 2;

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public void movePiece(Position fromPosition, Position toPosition) {
        validateExistPiecePosition(fromPosition);
        Piece piece = board.get(fromPosition);
        validateMovablePosition(piece, fromPosition, toPosition);
        checkRoute(fromPosition, toPosition);
        validateMyTeam(piece, toPosition);
        board.remove(fromPosition);
        board.put(toPosition, piece);
    }

    private void validateExistPiecePosition(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException(NOT_EXIST_PIECE);
        }
    }

    private void validateMovablePosition(Piece piece, Position fromPosition, Position toPosition) {
        if (!piece.isMovable(fromPosition, toPosition) && !isCatchable(piece, fromPosition,
                toPosition)) {
            throw new IllegalArgumentException(NON_MOVABLE_POSITION);
        }
    }

    private boolean isCatchable(Piece piece, Position fromPosition, Position toPosition) {
        return board.containsKey(toPosition) && piece.isCatchable(fromPosition, toPosition);
    }

    private void checkRoute(Position fromPosition, Position toPosition) {
        Position initialPosition = fromPosition;
        Direction direction = Direction.giveDirection(fromPosition, toPosition);
        while (initialPosition != toPosition) {
            initialPosition = direction.step(initialPosition);
            validateRoute(initialPosition, toPosition);
        }
    }

    private void validateRoute(Position position, Position toPosition) {
        if (position == toPosition) {
            return;
        }
        if (board.containsKey(position)) {
            throw new IllegalArgumentException(NON_MOVABLE_ROUTE);
        }
    }

    private void validateMyTeam(Piece piece, Position toPosition) {
        if (!board.containsKey(toPosition)) {
            return;
        }
        if (piece.isMyTeam(board.get(toPosition))) {
            throw new IllegalArgumentException(NON_CATCHABLE_PIECE);
        }
    }

    public boolean isAllKingExist() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count() == KING_COUNT;
    }

    public double calculateScore(Color color) {
        return board.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum() - calculateSameLinePawnScore(color);
    }

    private double calculateSameLinePawnScore(Color color) {
        List<Position> pawnPositions = board.keySet().stream()
                .filter(
                        position -> board.get(position).isSameColor(color) && board.get(position).isPawn())
                .collect(Collectors.toList());

        return pawnPositions.stream()
                .filter(position -> isSameLine(pawnPositions, position))
                .mapToDouble(position -> PAWN_SCORE_IN_SAME_FILE)
                .sum();
    }

    private boolean isSameLine(List<Position> pawnPositions, Position position) {
        return pawnPositions.stream()
                .filter(p -> p != position)
                .anyMatch(p -> p.isSameFile(position));
    }

    public Color getWinnerTeamColor() {
        return board.values().stream()
                .filter(Piece::isKing)
                .findAny()
                .orElseThrow(() -> new IllegalStateException(ALL_KING_NOT_EXIST))
                .getColor();
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
