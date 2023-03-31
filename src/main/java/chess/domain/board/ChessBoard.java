package chess.domain.board;

import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ChessBoard {
    private final Map<Position, Piece> board;

    private ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static ChessBoard getInstance(final ChessGame chessGame) {
        final Map<Position, Piece> board = ChessBoardFactory.getInstance(chessGame).createBoard();
        return new ChessBoard(board);
    }

    public static ChessBoard create(final Map<Position, Piece> board) {
        return new ChessBoard(board);
    }

    public boolean contains(final Position position) {
        return board.containsKey(position);
    }

    public Piece getPiece(final Position position) {
        return board.get(position);
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

    public List<Piece> getAliveKings() {
        return board.keySet().stream()
                .map(board::get)
                .filter(Piece::isKing)
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<Position, Piece> getBoardByCamp(final CampType campType) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().isSameCamp(campType))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ChessBoard that = (ChessBoard) o;
        return Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
