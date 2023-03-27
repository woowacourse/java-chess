package chess;

import chess.config.ChessConfig;
import chess.controller.ChessController;
import chess.service.ChessGameService;

public class ChessApplication {

    public static void main(String[] args) {
        final ChessConfig chessConfig = new ChessConfig();
        final ChessController chessController = chessConfig.chessController();
        final ChessGameService chessGameService = chessConfig.chessGameService();

        chessController.start(chessGameService);
    }
}
