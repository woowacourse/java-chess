package chess.service;

import chess.service.dto.ChessBoardDto;
import spark.Request;

import java.sql.SQLException;

public interface BoardInitializer {

    ChessBoardDto initialize(Request request) throws SQLException;
}
