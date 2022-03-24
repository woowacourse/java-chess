package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.move.MoveStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class ChessGame {

    private final Board board;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public Piece move(final Position source, final Position target, final Turn turn) {
        Piece sourcePiece = board.getPiece(source);
        validateTurn(turn, sourcePiece);
        MoveStrategy moveStrategy = sourcePiece.getMoveStrategy();
        validateMove(source, target, moveStrategy);
        return board.movePiece(source, target);
    }

    private void validateTurn(final Turn turn, final Piece sourcePiece) {
        if (!turn.isRightTurn(sourcePiece.getColor())) {
            throw new IllegalStateException("[ERROR] 당신의 차례가 아닙니다.");
        }
    }

    private void validateMove(final Position source, final Position target, final MoveStrategy moveStrategy) {
        if (!moveStrategy.isMovable(board, source, target)) {
            throw new IllegalStateException("[ERROR] 이동할 수 없습니다.");
        }
    }

    public double calculateScore(final Color color) {
        return calculateFirstLinePieces(color) + calculatePawn(color);
    }

    private double calculateFirstLinePieces(final Color color) {
        return getBoard().values().stream()
                .filter(piece -> piece.getColor() == color)
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    private double calculatePawn(final Color color) {
        Map<Position, Piece> board = getBoard();
        Map<Column, Integer> pawnCount = new EnumMap<>(Column.class);
        for (final Entry<Position, Piece> boardEntry : board.entrySet()) {
            putPawnCount(color, pawnCount, boardEntry);
        }

        return pawnCount.values().stream()
                .mapToDouble(this::adjustPawnPoint)
                .sum();
    }

    private void putPawnCount(final Color color, final Map<Column, Integer> pawnCount, final Entry<Position, Piece> boardEntry) {
        Piece piece = boardEntry.getValue();
        if (piece.isPawn() && piece.getColor() == color) {
            Column column = boardEntry.getKey().getColumn();
            pawnCount.put(column, pawnCount.getOrDefault(column, 0) + 1);
        }
    }

    private double adjustPawnPoint(final int count) {
        if (count > 1) {
            return count * 0.5;
        }
        return count;
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
