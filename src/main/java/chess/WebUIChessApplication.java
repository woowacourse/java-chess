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
        get(HomeController.URL, homeController::get);
        post(HomeController.URL, homeController::post);

        GameController gameController = GameController.getInstance();
        get(GameController.URL, gameController::get);
        post(GameController.URL, gameController::post);

        BoardController boardController = BoardController.getInstance();
        get(boardController.URL, boardController::get);

        ScoreController scoreController = ScoreController.getInstance();
        get(ScoreController.URL, scoreController::get);

        exception(Exception.class, (e, req, res) -> {
            System.out.println("오류###발생");
            e.printStackTrace();
            res.body("오류: " + e.getMessage());
        });
    }
}
