package chess.dao;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Route;
import chess.domain.piece.Piece;

public interface BoardDao {
    void saveBoard(Map<Point, Piece> pointPieces, String roomName);

    Board readBoard(String roomName);

    void deletePiece(Point destination, String roomName);

    void updatePiece(Route route, String roomName);
}
