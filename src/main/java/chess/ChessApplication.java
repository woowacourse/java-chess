package chess;

import chess.application.ChessGameService;
import chess.common.ChessGameConfig;
import chess.controller.ChessGameController;
import chess.domain.board.ChessBoardFactory;

public class ChessApplication {

    public static void main(String[] args) {
        final ChessGameConfig chessGameConfig = new ChessGameConfig();
        final ChessGameService chessGameService = chessGameConfig.chessGameService();
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        new ChessGameController(chessGameService, chessBoardFactory).control();
    }
}
