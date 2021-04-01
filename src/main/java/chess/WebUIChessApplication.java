package chess;

import chess.controller.ChessWebController;
import chess.domain.command.Ready;
import chess.domain.piece.PieceFactory;
import chess.domain.player.ChessGame;
import chess.domain.state.StateFactory;
import chess.service.ChessService;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessService chessService = new ChessService(new ChessGame(StateFactory.initialization(PieceFactory.whitePieces()),
                StateFactory.initialization(PieceFactory.blackPieces()), new Ready()));
        ChessWebController chessWebController = new ChessWebController(chessService);
        chessWebController.run();
    }
}
