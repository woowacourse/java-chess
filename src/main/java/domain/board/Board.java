package domain.board;

import domain.piece.Piece;
import domain.position.Position;
import domain.position.Positions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Board {

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create(ChessAlignment chessStrategy) {
        final HashMap<Position, Piece> board = new HashMap<>();

        chessStrategy.addInitialPawns(board);
        chessStrategy.addInitialKings(board);
        chessStrategy.addInitialBishops(board);
        chessStrategy.addInitialKnights(board);
        chessStrategy.addInitialQueens(board);
        chessStrategy.addInitialRooks(board);

        return new Board(board);
    }

    public void move(Position source, Position destination) {
        validateSourceExistence(source);
        validatePieceExistenceInRoute(source, destination);
        Piece piece = board.get(source);
        validatePieceExistenceOnDestination(destination, piece);

        if (board.containsKey(destination)) {
            validatePieceEatable(source, destination, piece);
            board.put(destination, board.remove(source));
            return;
        }

        validatePieceMovable(source, destination, piece);
        board.put(destination, board.remove(source));
    }

    private void validateSourceExistence(final Position source) {
        if (!board.containsKey(source)) {
            throw new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다.");
        }
    }

    private void validatePieceMovable(final Position source, final Position destination, final Piece piece) {
        if (!piece.isMovable(source, destination)) {
            throw new IllegalArgumentException("해당 위치로 말을 이동할 수 없습니다.");
        }
    }

    private void validatePieceEatable(final Position source, final Position destination, final Piece piece) {
        if (!piece.isEatable(source, destination)) {
            throw new IllegalArgumentException("해당 위치로 말을 이동할 수 없습니다.");
        }
    }

    private void validatePieceExistenceOnDestination(final Position destination, final Piece piece) {
        if (board.containsKey(destination) && piece.isBlack() == board.get(destination).isBlack()) {
            throw new IllegalArgumentException("해당 위치로 말을 이동할 수 없습니다.");
        }
    }

    private void validatePieceExistenceInRoute(final Position source, final Position destination) {
        if (Positions.between(source, destination).stream().anyMatch(board::containsKey)) {
            throw new IllegalArgumentException("해당 위치로 말을 이동할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(board);
    }
}
