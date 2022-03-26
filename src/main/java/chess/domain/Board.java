package chess.domain;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final String NOT_EXIST_PIECE = "[ERROR] 입력한 위치에 말이 존재하지 않습니다.";
    private static final String NON_MOVABLE_POSITION = "[ERROR] 해당 위치는 말이 움직일 수 없습니다.";
    private static final String NON_MOVABLE_ROUTE = "[ERROR] 해당 위치로 말이 도달할 수 없습니다.";
    private static final String NON_CATCHABLE_PIECE = "[ERROR] 잡을 수 없는 말 입니다.";

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }

    public void movePiece(Position fromPosition, Position toPosition) {
        validateExistPiecePosition(fromPosition);
        Piece piece = board.get(fromPosition);
        validateMovablePosition(piece, fromPosition, toPosition);
        if (!piece.isKnight()) {
            checkRoute(fromPosition, toPosition);
        }
        validateMyTeam(piece, toPosition);
        board.remove(fromPosition);
        board.put(toPosition, piece);
    }

    private void checkRoute(Position fromPosition, Position toPosition) {
        Position initialPosition = fromPosition;
        Direction direction = Direction.judge(fromPosition, toPosition);
        while (initialPosition != toPosition) {
            initialPosition = Direction.step(initialPosition, direction);
            validateRoute(initialPosition,toPosition);
        }
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
    }

    private boolean isCatchable(Piece piece, Position fromPosition, Position toPosition) {
        return board.containsKey(toPosition) && piece.isCatchable(fromPosition, toPosition);
    }

    private void validateRoute(Position position, Position toPosition) {
        if(position == toPosition) {
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
        if (piece.isSameColor(board.get(toPosition))) {
            throw new IllegalArgumentException(NON_CATCHABLE_PIECE);
        }
    }

    public boolean isAllKingExist() {
        return board.values()
            .stream()
            .filter(Piece::isKing)
            .map(piece -> 1)
            .reduce(0, Integer::sum) == 2;
    }
}
