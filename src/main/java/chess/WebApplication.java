package chess;

import static spark.Spark.*;

import chess.controller.WebController;
import chess.dao.ChessGameDao;
import chess.dao.PieceDao;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        port(8080);
        WebController webController = new WebController();

        ChessGameDao chessGameDao = new ChessGameDao();
        PieceDao pieceDao = new PieceDao();

        get("/", webController.ready());
        get("/findgame", webController.askGameID());
        post("/findgame", webController.findGame(chessGameDao, pieceDao));
        get("/ingame", webController.runGame(chessGameDao, pieceDao));
        post("/ingame", webController.movePiece(chessGameDao, pieceDao));
        get("/status", webController.showResult(chessGameDao, pieceDao));
    }
}
