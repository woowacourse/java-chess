package chess;

import chess.controller.ChessWebController;
import chess.domain.dao.MysqlChessDao;
import chess.domain.dao.MysqlMovementDao;
import chess.service.ChessService;

public class WebUIChessApplication {
    public static void main(String[] args) {
        ChessService chessService = new ChessService(new MysqlChessDao(), new MysqlMovementDao());
        ChessWebController chessWebController = new ChessWebController(chessService);
        chessWebController.run();
    }
}
