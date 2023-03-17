package chess.domain.chessboard;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import java.util.List;
import java.util.stream.Collectors;

public final class Square {
    private PieceState pieceState;

    public Square() {
        pieceState = new Empty();
    }

    public Square(final Piece piece) {
        pieceState = piece;
    }

    public List<Coordinate> findRoute(final Coordinate from, final Coordinate to) {
        return pieceState.findRoute(from, to);
    }

    public void validateRoute(final List<Square> routeSquares) {
        final List<PieceState> pieceStates = routeSquares.stream()
                .map(square -> square.pieceState)
                .collect(Collectors.toList());

        pieceState.validateRoute(pieceStates);
    }

    public void switchPieceState(final Square other) {
        this.pieceState = other.pieceState;
    }

    public PieceState getPieceState() {
        return pieceState;
    }
}
