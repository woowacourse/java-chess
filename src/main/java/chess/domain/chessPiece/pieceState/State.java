package chess.domain.chessPiece.pieceState;

import chess.domain.chessPiece.Catchable;
import chess.domain.chessPiece.Movable;
import chess.domain.chessPiece.pieceType.PieceColor;

public interface State extends Movable, Catchable {

    State movedState(PieceColor pieceColor);
}
