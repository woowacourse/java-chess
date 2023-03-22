package chess.domain.board;

import chess.domain.chess.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;

import java.util.Map;

public final class ChessBoard {
    private final Map<Position, Piece> board;

    private ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static ChessBoard getInstance(final ChessGame chessGame) {
        final Map<Position, Piece> board = ChessBoardFactory.getInstance(chessGame).createBoard();
        return new ChessBoard(board);
    }

    public boolean contains(final Position possiblePosition) {
        return board.containsKey(possiblePosition);
    }

    public Piece checkPiece(final Position source) {
        if (!contains(source)) {
            throw new IllegalArgumentException("체스말이 존재하는 위치를 입력해 주세요.");
        }
        return board.get(source);
    }

    public void removePiece(final Position position) {
        board.remove(position);
    }

    public void putPiece(final Position position, final Piece piece) {
        board.put(position, piece);
    }

    public boolean isPossibleRoute(final Position source, final Position target) {
        final Position unitPosition = source.computeUnitPosition(target);
        Position currentPosition = Position.copy(source);
        currentPosition = currentPosition.calculate(unitPosition);
        if (isObstructed(target, unitPosition, currentPosition)) {
            return false;
        }
        final Piece targetPiece = board.get(target);
        return targetPiece == null || !targetPiece.isSameCamp(board.get(source));
    }

    private boolean isObstructed(final Position target, final Position unitPosition, final Position currentPosition) {
        if (currentPosition.isSame(target)) {
            return false;
        }
        if (board.containsKey(currentPosition)) {
            return true;
        }
        final Position nextPosition = currentPosition.calculate(unitPosition);
        return isObstructed(target, unitPosition, nextPosition);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
