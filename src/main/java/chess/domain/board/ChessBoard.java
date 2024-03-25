package chess.domain.board;

import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.dto.BoardStatus;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(final BoardGenerator chessBoardGenerator) {
        this.board = new HashMap<>(chessBoardGenerator.generate());
    }

    public BoardStatus status() {
        return BoardStatus.from(board);
    }

    public void move(String from, String to, Turn turn) {
        Position source = Position.of(from);
        Position target = Position.of(to);

        validate(source, target, turn);

        Piece sourcePiece = board.get(source);
        board.put(target, sourcePiece);
        board.remove(source);
    }

    private void validate(Position source, Position target, Turn turn) {
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
            throw new IllegalArgumentException("source에 이동할 수 있는 기물이 존재하지 않습니다.");
        }
    }

    private void validateTurn(Position source, Turn turn) {
        Piece sourcePiece = board.get(source);
        if (!turn.hasTurn(sourcePiece.color())) {
            throw new IllegalArgumentException(String.format("%s의 차례가 아닙니다.", sourcePiece.color()));
        }
    }

    private void validatePositions(final Position source, final Position target) {
        if (source == target) {
            throw new IllegalArgumentException("source와 target이 같을 수 없습니다.");
        }
    }

    private void validateTarget(final Position source, Position target) {
        SquareStatus targetStatus = determineStatus(source, target);
        if (targetStatus.isPeer()) {
            throw new IllegalArgumentException("target으로 이동할 수 없습니다.");
        }
    }

    private void validateMovement(final Position source, final Position target, final Movement movement) {
        Piece sourcePiece = board.get(source);
        SquareStatus targetStatus = determineStatus(source, target);
        if (!sourcePiece.isMovable(movement, targetStatus)) {
            throw new IllegalArgumentException("기물이 이동할 수 없는 방식입니다.");
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
            throw new IllegalArgumentException("이동하고자 하는 경로 사이에 기물이 존재합니다.");
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
