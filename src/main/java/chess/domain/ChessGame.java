package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessGame {

    public static final Color FIRST_TURN = Color.WHITE;

    private final Board board;
    private Color movableTurn;

    public ChessGame(Board board, Color movableTurn) {
        this.board = board;
        this.movableTurn = movableTurn;
    }

    public void move(final Square source, final Square destination) {
        final Optional<Piece> piece = board.findPieceOf(source);
        validateEmpty(piece);
        validateTurn(piece);
        moveToDestination(piece.get(), source, destination);
        movableTurn = movableTurn.oppositeTurn();
    }

    private void validateTurn(Optional<Piece> piece) {
        if (piece.get().isBlack() != movableTurn.isBlack()) {
            throw new IllegalArgumentException(String.format("현재 이동 가능한 기물은 %s색 입니다.", movableTurn));
        }
    }

    private static void validateEmpty(Optional<Piece> piece) {
        if (piece.isEmpty()) {
            throw new IllegalArgumentException("움직일 기물이 존재하지 않습니다.");
        }
    }

    private void moveToDestination(final Piece piece, final Square source, final Square destination) {
        final List<Square> route = piece.findRoute(source, destination);
        if (!isMovable(piece, source, route)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        board.move(source, destination);
    }


    private boolean isMovable(final Piece piece, final Square source, final List<Square> route) {
        if (piece.isPawn()) {
            return board.canMovePawn(source, route);
        }
        return board.canMove(source, route);
    }

    public Map<Color, Double> getResult(final Color color) {
        Map<Color, Double> results = new HashMap<>();
        results.put(Color.BLACK, board.resultOf(Color.BLACK));
        results.put(Color.WHITE, board.resultOf(Color.WHITE));
        return results;
    }

    public Board getBoard() {
        return board;
    }
}
