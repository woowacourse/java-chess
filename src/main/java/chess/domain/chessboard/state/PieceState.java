package chess.domain.chessboard.state;

import chess.domain.chessboard.state.piece.Piece;

public interface PieceState {

    boolean isEmpty();

    boolean isSameTeam(final Piece piece);

    Team getTeam();
}
