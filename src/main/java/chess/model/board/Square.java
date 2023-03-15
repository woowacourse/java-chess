package chess.model.board;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.position.Distance;
import chess.model.position.Position;

public interface Square {

    Type getType();

    Color getColor();

    Piece piece();

    Square receivePiece(final Piece piece);

    Square movePiece(final Position position);

    Square removePiece();

    boolean isEmpty();

    boolean isSameTeam(final PieceColor pieceColor);

    boolean hasPawn();

    void validateMovable(final Distance distance);

    void validateExistence(final PieceColor pieceColor);

    void validateEnemyPiece(final PieceColor pieceColor);

    void validatePassable();
}
