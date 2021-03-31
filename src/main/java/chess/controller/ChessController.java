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
}
