package chess.service;

import chess.service.dto.ChessGameDto;

import java.sql.SQLException;

public interface BoardInitializer {

    ChessGameDto initialize() throws SQLException;
}
