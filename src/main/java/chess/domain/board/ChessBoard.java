package chess.domain.board;

import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.dto.BoardStatus;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private static final String INVALID_SOURCE = "source에 이동할 수 있는 기물이 존재하지 않습니다.";
    private static final String INVALID_TURN = "%s의 차례가 아닙니다.";
    private static final String INVALID_POSITIONS = "source와 target이 같을 수 없습니다.";
    private static final String INVALID_TARGET = "target으로 이동할 수 없습니다.";
    private static final String INVALID_MOVEMENT = "기물이 이동할 수 없는 방식입니다.";
    private static final String INVALID_PATH = "이동하고자 하는 경로 사이에 기물이 존재합니다.";

    private final Map<Position, Piece> board;

    public ChessBoard(final Map<Position,Piece> board) {
        this.board = new HashMap<>(board);
    }

    public BoardStatus status() {
        return BoardStatus.from(board);
    }

    public void move(final String from, final String to, final Turn turn) {
        Position source = Position.of(from);
        Position target = Position.of(to);

        validate(source, target, turn);

        Piece sourcePiece = board.get(source);
        board.put(target, sourcePiece);
        board.remove(source);
    }

    private void validate(final Position source, final Position target, final Turn turn) {
        validateSource(source);
        validateTurn(source, turn);
        validatePositions(source, target);
        validateTarget(source, target);
        Movement movement = new Movement(source, target);
        validateMovement(source, target, movement);
        validatePath(source, movement);
    }

    private void validateSource(final Position source) {
        if (!isExist(source)) {
            throw new IllegalArgumentException(INVALID_SOURCE);
        }
    }

    private void validateTurn(final Position source, final Turn turn) {
        Piece sourcePiece = board.get(source);
        if (!turn.hasTurn(sourcePiece.color())) {
            throw new IllegalArgumentException(String.format(INVALID_TURN, sourcePiece.color()));
        }
    }

    private void validatePositions(final Position source, final Position target) {
        if (source == target) {
            throw new IllegalArgumentException(INVALID_POSITIONS);
        }
    }

    private void validateTarget(final Position source, final Position target) {
        SquareStatus targetStatus = determineStatus(source, target);
        if (targetStatus.isPeer()) {
            throw new IllegalArgumentException(INVALID_TARGET);
        }
    }

    private void validateMovement(final Position source, final Position target, final Movement movement) {
        Piece sourcePiece = board.get(source);
        SquareStatus targetStatus = determineStatus(source, target);
        if (!sourcePiece.isMovable(movement, targetStatus)) {
            throw new IllegalArgumentException(INVALID_MOVEMENT);
        }
    }

    private SquareStatus determineStatus(final Position source, final Position position) {
        if (!isExist(position)) {
            return SquareStatus.EMPTY;
        }
        if (isSameColor(board.get(position), board.get(source))) {
            return SquareStatus.PEER;
        }
        return SquareStatus.ENEMY;
    }

    private void validatePath(final Position source, final Movement movement) {
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isType(PieceType.KNIGHT) && isBlocked(movement)) {
            throw new IllegalArgumentException(INVALID_PATH);
        }
    }

    private boolean isBlocked(final Movement movement) {
        return movement.findRoute().stream().anyMatch(this::isExist);
    }

    private boolean isExist(final Position position) {
        return board.containsKey(position);
    }

    private boolean isSameColor(final Piece sourcePiece, final Piece targetPiece) {
        return targetPiece.isColor(sourcePiece.color());
    }
}
