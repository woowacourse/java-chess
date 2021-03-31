package chess.controller;

import chess.domain.dto.PieceDto;
import chess.domain.dto.RoomDto;
import chess.serivce.chess.ChessService;
import java.sql.SQLException;
import java.util.List;

public class ChessController {

    private final ChessService service = new ChessService();

    public List<PieceDto> start(long roomId) throws SQLException {
        return service.start(roomId);
    }

    public List<PieceDto> end(Long roomId) throws SQLException {
        return service.end(roomId);
    }

    public List<PieceDto> move(Long roomId, String source, String target) throws SQLException {
        return service.move(roomId, source, target);
    }

    public List<PieceDto> findPiecesByRoomId(Long roomId) throws SQLException {
        return service.findPiecesByRoomId(roomId);
    }
}
