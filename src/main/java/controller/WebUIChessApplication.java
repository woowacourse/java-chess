package controller;

import model.game.Game;
import service.GameService;
import view.WebView;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Game game = GameService.getGame();

    public static void main(String[] args) {
        port(4567);

        get("/", (req, res) -> WebView.printIndexPage(game));

        get("/select", (req, res) -> GameService.selectSrc(req.queryParams("choice")));

        get("/confirm", (req, res) -> GameService.selectDest(req.queryParams("choice"), res::redirect, res::status));

        get("/cancel", (req, res) -> GameService.reselectSrc(res::redirect, res::status));

        get("/restart", (req, res) -> {
            game = GameService.restartGame();
            res.redirect("/");
            res.status(200);
            return 0;
        });
    }
}