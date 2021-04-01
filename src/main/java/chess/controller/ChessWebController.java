package chess.controller;

import chess.service.ChessGameService;
import chess.view.dto.ChessGameDto;

import java.sql.SQLException;

public class ChessWebController {

    private final ChessGameService chessGameService;

    public ChessWebController(final ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public ChessGameDto create() throws SQLException {
        return chessGameService.createNewChessGame();
    }

}
