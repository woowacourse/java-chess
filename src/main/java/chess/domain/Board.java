package chess.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class Board {

    private static final String NOT_EXIST_PIECE = "[ERROR] 입력한 위치에 말이 존재하지 않습니다.";
    private static final String NON_MOVABLE_POSITION = "[ERROR] 해당 위치는 말이 움직일 수 없습니다.";
    private static final String NON_MOVABLE_ROUTE = "[ERROR] 해당 위치로 말이 도달할 수 없습니다.";
    private static final String NON_CATCHABLE_PIECE = "[ERROR] 잡을 수 없는 말 입니다.";
    private static final int TOTAL_KING_COUNT = 2;
    private static final int ALLOWED_ONE_LINE_PAWN_COUNT = 2;
    private static final double PAWN_PENALTY_RATE = 0.5;

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new HashMap<>();
    }

    public Board(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public void movePiece(Position fromPosition, Position toPosition) {
        validateExistPiecePosition(fromPosition);
        Piece pieceToMove = board.get(fromPosition);
        validateMovablePosition(pieceToMove, fromPosition, toPosition);
        if (!pieceToMove.isKnight()) {
            validateNotExistOtherPieceInRoute(fromPosition, toPosition);
        }
        board.remove(fromPosition);
        board.put(toPosition, pieceToMove);
    }

    private void validateExistPiecePosition(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException(NOT_EXIST_PIECE);
        }
    }

    private void validateMovablePosition(Piece piece, Position fromPosition, Position toPosition) {
        if (!piece.isMovable(fromPosition, toPosition) && !isCatchable(piece, fromPosition, toPosition)) {
            throw new IllegalArgumentException(NON_MOVABLE_POSITION);
        }
        if (board.containsKey(toPosition) && piece.isSameTeam(board.get(toPosition))) {
            throw new IllegalArgumentException(NON_CATCHABLE_PIECE);
        }
    }

    private boolean isCatchable(Piece piece, Position fromPosition, Position toPosition) {
        return board.containsKey(toPosition) && piece.isCatchable(fromPosition, toPosition);
    }

    private void validateNotExistOtherPieceInRoute(Position fromPosition, Position toPosition) {
        Direction direction = Direction.judge(fromPosition, toPosition);
        Position nextPosition = Direction.getNextPosition(fromPosition, direction);
        while (nextPosition != toPosition) {
            validateNotExistPiecePosition(nextPosition);
            nextPosition = Direction.getNextPosition(nextPosition, direction);
        }
    }

    private void validateNotExistPiecePosition(Position nextPosition) {
        if (board.containsKey(nextPosition)) {
            throw new IllegalArgumentException(NON_MOVABLE_ROUTE);
        }
    }

    public boolean isAllKingExist() {
        return board.values().stream()
            .filter(Piece::isKing)
            .map(piece -> 1)
            .reduce(0, Integer::sum) == TOTAL_KING_COUNT;
    }

    public double calculateScore(Color color) {
        return board.values().stream()
            .filter(piece -> piece.isSameColor(color))
            .mapToDouble(Piece::getScore)
            .sum() - calculateSameLinePawnSubtraction(color);
    }

    private double calculateSameLinePawnSubtraction(Color color) {
        List<File> pawnFiles = board.keySet().stream()
            .filter(position -> board.get(position).isPawn() && board.get(position).isSameColor(color))
            .map(position -> position.getFile())
            .collect(Collectors.toList());

        return new HashSet<>(pawnFiles).stream()
            .filter(file -> Collections.frequency(pawnFiles, file) >= ALLOWED_ONE_LINE_PAWN_COUNT)
            .mapToDouble(file -> Collections.frequency(pawnFiles, file) * PAWN_PENALTY_RATE)
            .sum();
    }

    public Color getWinnerTeamColor() {
        if (isAllKingExist()) {
            return Color.NONE;
        }
        return board.values().stream()
            .filter(Piece::isKing)
            .map(Piece::getColor)
            .findAny()
            .get();
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
