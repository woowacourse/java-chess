package chess.model.board;


import chess.model.piece.Piece;
import chess.model.piece.Type;

public interface Square {

    Type getType();

    Square receivePiece(final Piece piece);

    Square movePiece(final Position position);

    Square removePiece();
}
