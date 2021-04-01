package chess.controller;

import chess.domain.dto.PieceDto;
import chess.serivce.chess.ChessService;
import java.sql.SQLException;
import java.util.List;

public class ChessController {

    private final ChessService service = new ChessService();

    public List<PieceDto> start(String roomName) throws SQLException {
        return service.start(roomName);
    }

    public List<PieceDto> end(String roomName) throws SQLException {
        return service.end(roomName);
    }

    public List<PieceDto> move(String roomName, String source, String target) throws SQLException {
        return service.move(roomName, source, target);
    }

    public List<PieceDto> findPiecesByRoomName(String roomName) throws SQLException {
        return service.findPiecesByRoomName(roomName);
    }

    public void createRoom(String roomName) throws SQLException {
        service.createRoom(roomName);
    }
}
