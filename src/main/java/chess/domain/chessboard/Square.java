package chess.domain.chessboard;

import chess.domain.chessboard.state.Empty;
import chess.domain.chessboard.state.PieceState;
import chess.domain.chessboard.state.piece.Piece;
import java.util.List;

public final class Square {
    private PieceState pieceState;

    public Square() {
        pieceState = new Empty();
    }

    public Square(final Piece piece) {
        pieceState = piece;
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

    public void canMove(final List<Square> routeSquares) {
        pieceState.canMove(routeSquares);
    }

    public PieceState getPieceState() {
        return pieceState;
    }

    public void switchPieceState(final Square other){
        this.pieceState = other.pieceState;
    }
}
