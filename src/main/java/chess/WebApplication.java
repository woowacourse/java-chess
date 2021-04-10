package chess;

import chess.controller.web.GameController;
import chess.controller.web.MainController;
import chess.controller.web.RoomController;

import java.sql.Connection;

public class WebApplication {
    public static void main(String[] args) {
        final WebConfig webConfig = new WebConfig();
        final Connection connection = webConfig.getConnection();

        final GameController gameController = new GameController(connection);
        final RoomController roomController = new RoomController(connection);
        final MainController mainController = new MainController();

        gameController.mapping();
        roomController.mapping();
        mainController.mapping();
    }
}
