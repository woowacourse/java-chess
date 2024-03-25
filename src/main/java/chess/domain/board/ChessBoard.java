package chess.domain.board;

import chess.domain.Direction;
import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.dto.BoardStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this(boardGenerator.generate());
    }

    private ChessBoard(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public BoardStatus status() {
        return BoardStatus.from(board);
    }

    public void move(final Position source, final Position target, final Turn turn) {
        validate(source, target, turn);

        Piece sourcePiece = board.get(source);
        board.put(target, sourcePiece);
        board.remove(source);

        turn.next();
    }

    private void validate(final Position source, final Position target, final Turn turn) {
        validatePosition(source, target);
        validateTurn(source, turn);
        validateTarget(source, target);
        validateMovement(source, target);
        validatePath(source, target);
    }

    private void validatePosition(final Position source, final Position target) {
        if (!isExist(source) || isSamePosition(source, target)) {
            throw new IllegalArgumentException("입력하신 이동 위치가 올바르지 않습니다.");
        }
    }

    private void validateTurn(final Position source, final Turn turn) {
        Piece sourcePiece = board.get(source);
        if (!turn.isTurn(sourcePiece.color())) {
            throw new IllegalArgumentException("해당 색의 차례가 아닙니다.");
        }
    }

    private boolean isSamePosition(final Position source, final Position target) {
        return source.equals(target);
    }

    private void validateTarget(final Position source, final Position target) {
        if (isExist(target) && isSameColor(board.get(source), board.get(target))) {
            throw new IllegalArgumentException("이동할 수 없는 target입니다.");
        }
    }

    private void validateMovement(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isInMovableRange(source, target)) {
            throw new IllegalArgumentException("기물이 이동할 수 없는 방식입니다.");
        }
        if (PieceType.isPawn(sourcePiece)) {
            if (Direction.isDiagonal(source, target) && !isExist(target)) {
                throw new IllegalArgumentException("폰은 상대 기물이 존재할 때만 대각선 이동이 가능합니다.");
            }
            if (Direction.isVertical(source, target) && isExist(target) && !isSameColor(board.get(source), board.get(target))) {
                throw new IllegalArgumentException("폰은 대각선으로만 공격할 수 있습니다.");
            }
        }
    }

    private boolean isSameColor(final Piece sourcePiece, final Piece targetPiece) {
        return targetPiece.isColor(sourcePiece.color());
    }

    private void validatePath(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        if (!PieceType.isKnight(sourcePiece)) {
            Set<Position> positions = source.findBetween(target);
            for (Position position : positions) {
                if (isExist(position)) {
                    throw new IllegalArgumentException("이동하고자 하는 경로 사이에 기물이 존재합니다.");
                }
            }
        }
    }

    private boolean isExist(Position position) {
        return board.containsKey(position);
    }
}
