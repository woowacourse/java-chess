package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Board {

    private static final String NOT_EXIST_PIECE = "[ERROR] 입력한 위치에 말이 존재하지 않습니다.";
    private static final String CAN_NOT_MOVE_PIECE = "[ERROR] 해당 위치는 말이 움직일 수 없습니다.";
    private static final String CAN_NOT_PLACE_PIECE = "[ERROR] 해당 위치로 말이 도달할 수 없습니다.";
    private static final String CAN_NOT_CATCH_PIECE = "[ERROR] 잡을 수 없는 말 입니다.";
    private static final int TOTAL_KING_COUNT = 2;

    private final Map<Position, Piece> value;

    public Board() {
        this(new HashMap<>());
    }

    public Board(final Map<Position, Piece> value) {
        this.value = new HashMap<>(value);
    }

    public Color getPieceColor(final Position position) {
        return findPiece(position).getColor();
    }

    private Piece findPiece(final Position position) {
        if (!value.containsKey(position)) {
            throw new IllegalStateException(NOT_EXIST_PIECE);
        }
        return value.get(position);
    }

    public void movePiece(final Position source, final Position target) {
        final Piece pieceToMove = findPiece(source);
        validateMovablePosition(pieceToMove, source, target);
        if (!pieceToMove.isKnight()) {
            validateNotExistOtherPieceInRoute(source, target);
        }
        value.remove(source);
        value.put(target, pieceToMove);
    }

    private void validateMovablePosition(final Piece piece, final Position source, final Position target) {
        if (!piece.isMovable(source, target) && !isCatchable(piece, source, target)) {
            throw new IllegalStateException(CAN_NOT_MOVE_PIECE);
        }
        if (value.containsKey(target) && piece.isSameTeam(value.get(target))) {
            throw new IllegalStateException(CAN_NOT_CATCH_PIECE);
        }
    }

    private boolean isCatchable(final Piece piece, final Position source, final Position target) {
        return value.containsKey(target) && piece.isCatchable(source, target);
    }

    private void validateNotExistOtherPieceInRoute(final Position source, final Position target) {
        final Direction direction = Direction.judge(source, target);
        Position nextPosition = Direction.getIncreasedPositionByDirection(source, direction);
        while (nextPosition != target) {
            validateNotExistPiecePosition(nextPosition);
            nextPosition = Direction.getIncreasedPositionByDirection(nextPosition, direction);
        }
    }

    private void validateNotExistPiecePosition(final Position nextPosition) {
        if (value.containsKey(nextPosition)) {
            throw new IllegalStateException(CAN_NOT_PLACE_PIECE);
        }
    }

    public boolean isKingDead() {
        return value.values().stream()
            .filter(Piece::isKing)
            .count() < TOTAL_KING_COUNT;
    }

    public Map<Position, Piece> getValue() {
        return Map.copyOf(value);
    }
}
