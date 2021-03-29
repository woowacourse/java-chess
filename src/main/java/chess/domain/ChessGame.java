package chess.domain;

import chess.domain.piece.Piece;
import java.util.List;

public interface ChessGame {

    void movePiece(Position currentPosition, Position targetPosition);

    GameResult gameResult();

    boolean isKingDead();

    boolean isChecked();

    boolean isCheckmate();

    Pieces pieces();

    List<Piece> currentColorPieces();

    TeamColor currentColor();
}
