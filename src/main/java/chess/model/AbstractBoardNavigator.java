package chess.model;

import chess.model.coordinate.Point;

public interface AbstractBoardNavigator {
    AbstractChessPiece getPieceAt(Point point);
}
