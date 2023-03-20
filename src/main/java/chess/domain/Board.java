package chess.domain;

import chess.cache.BoardCache;
import chess.cache.PieceCache;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static final int PAWN_SINGLE_MOVE = 1;
    private static final int PAWN_DOUBLE_MOVE = 2;

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

    public void move(final Position source, final Position target, final Color color) {
        validateInvalidBoundary(source, target);

        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        validateInvalidColor(color, sourcePiece, targetPiece);
        validateInvalidPosition(source, target, sourcePiece);
        validateInvalidMove(source, target, sourcePiece);
        validateInvalidMovePawn(source, target, sourcePiece);

        board.put(target, sourcePiece);
        board.put(source, Empty.create());
    }

    private void validateInvalidPosition(final Position source, final Position target, final Piece sourcePiece) {
        final List<Position> positions = sourcePiece.findPositions(source, target);

        if (!positions.contains(target)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }
    }

    private void validateInvalidMove(final Position source, final Position target, final Piece sourcePiece) {
        final List<Position> positions = sourcePiece.findPositions(source, target);

        final boolean isMoveAble = positions.subList(0, positions.indexOf(target))
                .stream()
                .anyMatch(this::isBlocked);

        if (isMoveAble) {
            throw new IllegalArgumentException("이동 위치가 다른 기물에 의해 막혀 있습니다.");
        }
    }

    private boolean isBlocked(final Position position) {
        return board.getOrDefault(position, Empty.create()).isNotSamePieceType(PieceType.EMPTY);
    }

    private void validateInvalidColor(final Color currentPlayer, final Piece sourcePiece, final Piece targetPiece) {
        if (sourcePiece.isNotSameColor(currentPlayer)) {
            throw new IllegalArgumentException("자신의 기물을 선택해야 합니다.");
        }

        if (targetPiece.isSameColor(currentPlayer)) {
            throw new IllegalArgumentException("같은 색깔의 기물을 선택할 수 없습니다.");
        }
    }

    private void validateInvalidMovePawn(final Position source, final Position target, final Piece sourcePiece) {
        if (sourcePiece.isSamePieceType(PieceType.PAWN)
                && isNotMovablePawn(source, target)
                && isNotDiagonalMoveAblePawn(source, target)) {
            throw new IllegalArgumentException("폰이 이동할 수 없는 위치입니다.");
        }
    }

    private boolean isNotMovablePawn(final Position source, final Position target) {
        return !(isMoveAble(source, target, PAWN_SINGLE_MOVE) || isMoveAble(source, target, PAWN_DOUBLE_MOVE));
    }

    private boolean isMoveAble(final Position source, final Position target, final int move) {
        return source.isSameRow(target.getRow())
                && source.calculateColumnDistance(target.getColumn()) == move
                && piece(target).isSamePieceType(PieceType.EMPTY);
    }

    private boolean isNotDiagonalMoveAblePawn(final Position source, final Position target) {
        return !(source.calculateRowDistance(target.getRow()) == 1
                && source.calculateColumnDistance(target.getColumn()) == 1
                && piece(target).isNotSamePieceType(PieceType.EMPTY));
    }

    private void validateInvalidBoundary(final Position source, final Position target) {
        final int boardMinIndex = 0;
        final int boardMaxIndex = (int) Math.sqrt(board.size());
        if (source.isOverBoundary(boardMinIndex, boardMaxIndex) || target.isOverBoundary(boardMinIndex, boardMaxIndex)) {
            throw new IllegalArgumentException("입력 값이 보드의 범위를 초과하였습니다.");
        }
    }

    private Piece piece(final Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
