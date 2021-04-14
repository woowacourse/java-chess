package chess;

import chess.controller.web.WebChessController;
import chess.dao.CommandDao;
import chess.service.LoadService;
import chess.service.MoveService;
import chess.service.StartService;

public class WebUIChessApplication {

    public static void main(String[] args) {
        CommandDao commandDao = new CommandDao();

        WebChessController chessController = new WebChessController(
                new StartService(commandDao),
                new MoveService(commandDao),
                new LoadService(commandDao)
        );

        chessController.run();
    }

}
