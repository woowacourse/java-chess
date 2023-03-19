package chess.domain.game;

import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public interface ChessGameStep {

    boolean playable();

    ChessGameStep initialize();

    ChessGameStep movePiece(final PiecePosition source, final PiecePosition destination);

    ChessGameStep end();

    List<Piece> pieces();
}
