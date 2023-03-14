package chess.model.board;


import chess.model.Type;
import chess.model.piece.Piece;

public interface Square {

    Type getType();

    Square receivePiece(final Piece piece);

    Square movePiece(final Position position);

    Square removePiece();
}
