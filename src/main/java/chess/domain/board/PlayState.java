package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Map;

public final class PlayState extends InitializedState {

    private static final int VALID_KING_COUNT = 2;

    protected PlayState(final Map<Position, Piece> board, final Color turn) {
        super(board, turn);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Board move(final Position source, final Position target) {
        final Piece piece = board.get(source);

        validate(source, target, piece);
        movePiece(source, target, piece);
        if (isGameOver()) {
            return new EndState(board, turn.nextTurn());
        }
        return new PlayState(board, turn.nextTurn());
    }

    private void validate(final Position source, final Position target, final Piece piece) {
        if (piece.isNotSameColor(turn)) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
        if (piece.isNotMovable(source, target, board.get(target))) {
            throw new IllegalArgumentException("올바르지 않은 이동 명령어 입니다.");
        }
        if (isPieceExistsBetweenPosition(source, target)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있을 수 없습니다.");
        }
    }

    private boolean isPieceExistsBetweenPosition(final Position source, final Position target) {
        return source.between(target).stream()
                .anyMatch(this::isPieceExists);
    }

    private boolean isPieceExists(final Position position) {
        return !board.get(position).equals(Empty.instance());
    }

    private void movePiece(final Position source, final Position target, final Piece piece) {
        board.put(target, piece);
        board.put(source, Empty.instance());
    }

    private boolean isGameOver() {
        return VALID_KING_COUNT != board.values().stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .count();
    }
}
