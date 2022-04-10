package chess.database.dao;

import chess.database.dto.BoardDto;
import chess.database.dto.PointDto;
import chess.database.dto.RouteDto;

public interface BoardDao {
    void saveBoard(BoardDto boardDto, String roomName);

    BoardDto readBoard(String roomName);

    void deletePiece(PointDto destination, String roomName);

    void updatePiece(RouteDto routeDto, String roomName);
}
