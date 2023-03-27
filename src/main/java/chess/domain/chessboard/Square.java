package chess.domain.chessboard;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.piece.Score;
import chess.domain.piece.Team;
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

    public boolean isMyTeam(final Team team) {
        return this.pieceState.isMyTeam(team);
    }

    public boolean isTypeOf(final PieceType pieceType) {
        return pieceState.isTypeOf(pieceType);
    }

    public PieceState getPieceState() {
        return pieceState;
    }

    public Score getScore() {
        return this.pieceState.getScore();
    }
}
