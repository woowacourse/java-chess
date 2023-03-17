package chess.domain.chessboard;

import chess.domain.chessboard.state.Empty;
import chess.domain.chessboard.state.PieceState;
import chess.domain.chessboard.state.piece.Piece;
import java.util.List;

public final class Square {
    private PieceState pieceState;
    private static final Square EMPTY_SQUARE = new Square();

    private Square() {
        pieceState = new Empty();
    }

    public Square(final Piece piece) {
        pieceState = piece;
    }

    public static Square emptySquare() {
        return EMPTY_SQUARE;
    }

    public boolean isEmpty() {
        return pieceState.isEmpty();
    }

    public boolean isSameTeam(Piece piece) {
        return pieceState.isSameTeam(piece);
    }

    public List<Coordinate> findRoute(final Coordinate from, final Coordinate to) {
        return pieceState.findRoute(from, to);
    }

    public void validateRoute(final List<Square> routeSquares) {
        pieceState.validateRoute(routeSquares);
    }

    public void switchPieceState(final Square other) {
        this.pieceState = other.pieceState;
    }

    public PieceState getPieceState() {
        return pieceState;
    }
}
