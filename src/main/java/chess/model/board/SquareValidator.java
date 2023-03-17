package chess.model.board;

import chess.model.piece.PieceColor;
import chess.model.position.Distance;

public interface SquareValidator {

    void validateMovable(final Distance distance);

    void validateExistence(final PieceColor pieceColor);

    void validateEnemyPiece(final PieceColor pieceColor);

    void validateWaypoint();

}
