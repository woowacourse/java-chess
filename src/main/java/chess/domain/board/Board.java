package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Board {

    private static final String NOT_EXIST_PIECE = "[ERROR] 입력한 위치에 말이 존재하지 않습니다.";
    private static final String NON_MOVABLE_POSITION = "[ERROR] 해당 위치는 말이 움직일 수 없습니다.";
    private static final String NON_MOVABLE_ROUTE = "[ERROR] 해당 위치로 말이 도달할 수 없습니다.";
    private static final String NON_CATCHABLE_PIECE = "[ERROR] 잡을 수 없는 말 입니다.";

    private final Map<Position, Piece> value;

    public Board() {
        this.value = new HashMap<>();
    }

    public Board(final Map<Position, Piece> value) {
        this.value = new HashMap<>(value);
    }

    public Color getPieceColor(Position position) {
        return findPiece(position).getColor();
    }

    private Piece findPiece(Position position) {
        if (!value.containsKey(position)) {
            throw new IllegalArgumentException(NOT_EXIST_PIECE);
        }
        return value.get(position);
    }

    public void movePiece(Position fromPosition, Position toPosition) {
        Piece pieceToMove = findPiece(fromPosition);
        validateMovablePosition(pieceToMove, fromPosition, toPosition);
        if (!pieceToMove.isKnight()) {
            validateNotExistOtherPieceInRoute(fromPosition, toPosition);
        }
        value.remove(fromPosition);
        value.put(toPosition, pieceToMove);
    }

    private void validateMovablePosition(Piece piece, Position fromPosition, Position toPosition) {
        if (!piece.isMovable(fromPosition, toPosition) && !isCatchable(piece, fromPosition, toPosition)) {
            throw new IllegalArgumentException(NON_MOVABLE_POSITION);
        }
        if (value.containsKey(toPosition) && piece.isSameTeam(value.get(toPosition))) {
            throw new IllegalArgumentException(NON_CATCHABLE_PIECE);
        }
    }

    private boolean isCatchable(Piece piece, Position fromPosition, Position toPosition) {
        return value.containsKey(toPosition) && piece.isCatchable(fromPosition, toPosition);
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
        if (value.containsKey(nextPosition)) {
            throw new IllegalArgumentException(NON_MOVABLE_ROUTE);
        }
    }

    public Map<Position, Piece> getValue() {
        return Map.copyOf(value);
    }
}
