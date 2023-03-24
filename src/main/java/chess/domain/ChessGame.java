package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Square;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Optional;

public class ChessGame {

    private final Board board = BoardFactory.generate();

    public void move(final Square source, final Square destination) {
        final Optional<Piece> pieceOf = board.findPieceOf(source);
        if (pieceOf.isEmpty()) {
            throw new IllegalArgumentException("움직일 기물이 존재하지 않습니다.");
        }
        moveToDestination(pieceOf.get(), source, destination);
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

    public Board getBoard() {
        return board;
    }
}
