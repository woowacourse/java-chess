package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Map;

public final class Play extends Initialized {

    private static final int VALID_KING_COUNT = 2;

    protected Play(final Map<Position, Piece> board, final Color turn) {
        super(board, turn);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Board move(final String source, final String target) {
        final Position sourcePosition = Position.from(source);
        final Position targetPosition = Position.from(target);
        final Piece piece = board.get(sourcePosition);

        validate(sourcePosition, targetPosition, piece);
        movePiece(sourcePosition, targetPosition, piece);
        if (isGameOver()) {
            return new End(board, turn.nextTurn());
        }
        return new Play(board, turn.nextTurn());
    }

    private void validate(final Position sourcePosition, final Position targetPosition, final Piece piece) {
        if (piece.isNotSameColor(turn)) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
        if (piece.isNotMovable(sourcePosition, targetPosition, board.get(targetPosition))) {
            throw new IllegalArgumentException("올바르지 않은 이동 명령어 입니다.");
        }
        if (isPieceExistsBetweenPosition(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있을 수 없습니다.");
        }
    }

    private boolean isPieceExistsBetweenPosition(final Position sourcePosition, final Position targetPosition) {
        return sourcePosition.between(targetPosition).stream()
                .anyMatch(this::isPieceExists);
    }

    private boolean isPieceExists(final Position position) {
        return !board.get(position).equals(Empty.instance());
    }

    private void movePiece(final Position sourcePosition, final Position targetPosition, final Piece piece) {
        board.put(targetPosition, piece);
        board.put(sourcePosition, Empty.instance());
    }

    private boolean isGameOver() {
        return VALID_KING_COUNT != board.values().stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .count();
    }
}
