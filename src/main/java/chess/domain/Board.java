package chess.domain;

import chess.cache.BoardCache;
import chess.cache.PieceCache;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Board {
    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create(){
        final Map<Position, Piece> board = new HashMap<>();
        board.putAll(BoardCache.create());
        board.putAll(PieceCache.create());
        return new Board(board);
    }

    public static Board from(Map<Position, Piece> piece) {
        final Map<Position, Piece> board = new HashMap<>();
        board.putAll(BoardCache.create());
        board.putAll(piece);
        return new Board(board);
    }

    public Board move(final Position source, final Position target, final Color color) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        validateInvalidColor(color, sourcePiece);
        validateInvalidMove(source, target, sourcePiece, targetPiece);

        Map<Position, Piece> newBoard = new HashMap<>(board);

        newBoard.put(source, Empty.create());
        newBoard.put(target, sourcePiece);
        return new Board(newBoard);
    }

    private void validateInvalidColor(final Color currentColor, final Piece sourcePiece) {
        if (sourcePiece.isNotSameColor(currentColor)) {
            throw new IllegalArgumentException("자신의 기물을 선택해야 합니다.");
        }
    }

    private void validateInvalidMove(final Position source, final Position target, final Piece sourcePiece, final Piece targetPiece) {
        final List<Position> positions = sourcePiece.findMoveAblePositions(source, target, targetPiece);

        final boolean isNotMoveAble = positions.subList(0, positions.indexOf(target))
                .stream()
                .anyMatch(this::isBlocked);

        if (isNotMoveAble) {
            throw new IllegalArgumentException("이동 위치가 다른 기물에 의해 막혀 있습니다.");
        }
    }

    private boolean isBlocked(final Position position) {
        return board.getOrDefault(position, Empty.create()).isNotSamePieceType(PieceType.EMPTY);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }
}
