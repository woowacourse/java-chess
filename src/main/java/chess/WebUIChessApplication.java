package chess;

import chess.controller.BoardController;
import chess.controller.GameController;
import chess.controller.HomeController;
import chess.controller.ScoreController;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("static");
        externalStaticFileLocation("src/main/resources/templates");

        HomeController homeController = HomeController.getInstance();
        get(HomeController.URL, homeController::getGameRooms);
        post(HomeController.URL, homeController::setUpGame);

        GameController gameController = GameController.getInstance();
        get(GameController.URL, gameController::moveToGamePage);
        post(GameController.URL, gameController::updatePiece);

        BoardController boardController = BoardController.getInstance();
        get(boardController.URL, boardController::setUpBoard);

        ScoreController scoreController = ScoreController.getInstance();
        get(ScoreController.URL, scoreController::getScore);

        exception(Exception.class, (e, req, res) -> {
            e.printStackTrace();
            res.body("오류: " + e.getMessage());
        });
    }
}
