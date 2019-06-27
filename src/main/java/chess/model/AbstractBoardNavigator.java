package chess.model;

import chess.model.piece.AbstractChessPiece;

public interface AbstractBoardNavigator {
    AbstractChessPiece getPieceAt(Point point);
}
