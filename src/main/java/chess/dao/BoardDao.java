package chess.dao;

import chess.database.BoardDto;
import chess.database.PointDto;
import chess.database.RouteDto;

public interface BoardDao {
    void saveBoard(BoardDto boardDto, String roomName);

    BoardDto readBoard(String roomName);

    void deletePiece(PointDto destination, String roomName);

    void updatePiece(RouteDto routeDto, String roomName);
}
