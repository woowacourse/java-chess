package chess.request;

import chess.domain.board.Point;
import chess.domain.piece.Piece;

import java.util.Map;

public interface Response {

    Map<Point, Piece> getBoard();
}
