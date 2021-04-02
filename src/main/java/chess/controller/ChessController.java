package chess.controller;

import chess.domain.dto.move.MoveResponseDto;
import chess.serivce.chess.ChessService;
import java.sql.SQLException;

public class ChessController {

    private final ChessService service = new ChessService();

    public MoveResponseDto start(String roomName) throws SQLException {
        return service.start(roomName);
    }

    public MoveResponseDto end(String roomName) throws SQLException {
        return service.end(roomName);
    }

    public MoveResponseDto move(String roomName, String source, String target) throws SQLException {
        return service.move(roomName, source, target);
    }

    public MoveResponseDto findPiecesByRoomName(String roomName) throws SQLException {
        return service.findPiecesByRoomName(roomName);
    }

    public void createRoom(String roomName) throws SQLException {
        service.createRoom(roomName);
    }
}
