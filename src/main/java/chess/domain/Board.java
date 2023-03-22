package chess.domain;

import chess.cache.BoardCache;
import chess.cache.PieceCache;
import chess.domain.direction.BasicDirection;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.PieceType.EMPTY;
import static chess.domain.PieceType.PAWN;

public class Board {
    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        final Map<Position, Piece> board = new HashMap<>();
        board.putAll(BoardCache.create());
        board.putAll(PieceCache.create());
        return new Board(board);
    }

    public void move(final Position source, final Position target, final Color currentPlayer) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        validateInvalidColor(currentPlayer, sourcePiece, targetPiece);
        validateTargetBlocked(source, target, sourcePiece);
        validateIfPawnUnmovable(source, target, sourcePiece);

        board.put(target, sourcePiece);
        board.put(source, Empty.create());
    }

    private void validateInvalidColor(final Color currentPlayer, final Piece sourcePiece, final Piece targetPiece) {
        if (sourcePiece.isNotSameColor(currentPlayer)) {
            throw new IllegalArgumentException("자신의 기물을 선택해야 합니다.");
        }
        if (targetPiece.isSameColor(currentPlayer)) {
            throw new IllegalArgumentException("같은 색깔의 기물을 선택할 수 없습니다.");
        }
    }

    private void validateTargetBlocked(final Position source, final Position target, final Piece sourcePiece) {
        final List<Position> positions = sourcePiece.findPositions(source, target);

        if (isTargetBlocked(target, positions)) {
            throw new IllegalArgumentException("이동 위치가 다른 기물에 의해 막혀 있습니다.");
        }
    }

    private boolean isTargetBlocked(final Position target, final List<Position> positions) {
        return positions.subList(0, positions.indexOf(target))
                .stream()
                .anyMatch(this::isBlocked);
    }

    private boolean isBlocked(final Position position) {
        return board.getOrDefault(position, Empty.create()).isNotSamePieceType(EMPTY);
    }

    private void validateIfPawnUnmovable(final Position source, final Position target, final Piece sourcePiece) {
        if (sourcePiece.isSamePieceType(PAWN) && isPawnUnmovable(source, target)) {
            throw new IllegalArgumentException("폰이 이동할 수 없는 위치입니다.");
        }
    }

    private boolean isPawnUnmovable(final Position source, final Position target) {
        final Piece targetPiece = board.get(target);

        if (BasicDirection.isDiagonal(source, target)) {
            return targetPiece.isSamePieceType(EMPTY);
        }
        return targetPiece.isNotSamePieceType(EMPTY);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
