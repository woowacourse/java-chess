package chess.model.board;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.position.Position;

public interface Square extends SquareValidator {

    Piece pick();

    Square movePiece(final Position position);

    Square receivePiece(final Piece piece);

    Square removePiece();

    boolean hasPawn();

    boolean isSameTeam(final PieceColor pieceColor);

    boolean isEmpty();

    Type getType();

    Color getColor();
}
