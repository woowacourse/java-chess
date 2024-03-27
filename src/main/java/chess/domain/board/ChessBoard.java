package chess.domain.board;

import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceRelation;
import chess.domain.piece.PieceType;
import chess.domain.position.Movement;
import chess.domain.position.Position;
import chess.dto.BoardStatus;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private static final String INVALID_SOURCE = "source에 이동할 수 있는 기물이 존재하지 않습니다.";
    private static final String INVALID_POSITIONS = "source와 target이 같을 수 없습니다.";
    private static final String INVALID_TARGET = "target으로 이동할 수 없습니다.";
    private static final String INVALID_TURN = "%s의 차례가 아닙니다.";
    private static final String INVALID_MOVEMENT = "기물이 이동할 수 없는 방식입니다.";
    private static final String INVALID_PATH = "이동하고자 하는 경로 사이에 기물이 존재합니다.";

    private final Map<Position, Piece> board;

    public ChessBoard(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public BoardStatus status() {
        return BoardStatus.from(board);
    }

    public void move(final String from, final String to, final Turn turn) {
        Position source = Position.of(from);
        Position target = Position.of(to);

        validatePosition(source, target);
        validateTurn(source, turn);
        validateMovement(source, target);

        updateBoard(source, target);
    }

    private void validatePosition(final Position source, final Position target) {
        validateSource(source);
        validateIdentity(source, target);
        validateTarget(source, target);
    }

    private void validateSource(final Position source) {
        if (doesNotExist(source)) {
            throw new IllegalArgumentException(INVALID_SOURCE);
        }
    }

    private void validateIdentity(final Position source, final Position target) {
        if (source == target) {
            throw new IllegalArgumentException(INVALID_POSITIONS);
        }
    }

    private void validateTarget(final Position source, final Position target) {
        PieceRelation targetStatus = determineStatus(source, target);
        if (targetStatus.isPeer()) {
            throw new IllegalArgumentException(INVALID_TARGET);
        }
    }

    private void validateTurn(final Position source, final Turn turn) {
        Piece sourcePiece = board.get(source);
        if (turn.isNotTurnOwner(sourcePiece.color())) {
            throw new IllegalArgumentException(String.format(INVALID_TURN, sourcePiece.color()));
        }
    }

    private void validateMovement(final Position source, final Position target) {
        Movement movement = new Movement(source, target);
        validateMovability(source, target, movement);
        validatePath(source, movement);
    }

    private void validateMovability(final Position source, final Position target, final Movement movement) {
        Piece sourcePiece = board.get(source);
        PieceRelation targetStatus = determineStatus(source, target);
        if (!sourcePiece.isMovable(movement, targetStatus)) {
            throw new IllegalArgumentException(INVALID_MOVEMENT);
        }
    }

    private PieceRelation determineStatus(final Position source, final Position position) {
        if (doesNotExist(position)) {
            return PieceRelation.EMPTY;
        }
        if (isSameColor(board.get(position), board.get(source))) {
            return PieceRelation.PEER;
        }
        return PieceRelation.ENEMY;
    }

    private boolean doesNotExist(final Position position) {
        return !board.containsKey(position);
    }

    private boolean isSameColor(final Piece sourcePiece, final Piece targetPiece) {
        return targetPiece.isColor(sourcePiece.color());
    }

    private void validatePath(final Position source, final Movement movement) {
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isType(PieceType.KNIGHT) && isBlocked(movement)) {
            throw new IllegalArgumentException(INVALID_PATH);
        }
    }

    private boolean isBlocked(final Movement movement) {
        return movement.findRoute().stream().anyMatch(this::doesNotExist);
    }

    private void updateBoard(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        board.put(target, sourcePiece);
        board.remove(source);
    }
}
