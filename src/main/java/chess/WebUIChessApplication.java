package chess;

import chess.service.*;
import chess.web.ChessController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/templates");

        ChessController chessController = new ChessController(
                new StartService(),
                new EndService(),
                new MoveService(),
                new SaveService(),
                new StatusService(),
                new LoadService()
        );
        chessController.run();
    }

}
