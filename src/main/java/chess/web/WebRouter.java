package chess.web;

import chess.controller.GameController;
import chess.controller.HomeController;
import chess.controller.ResultController;
import chess.controller.SearchController;

public class WebRouter {

    public void init() {
        new HomeController().initRouteHandler();
        new SearchController().initRouteHandler();
        new GameController().initRouteHandler();
        new ResultController().initRouteHandler();
        new ExceptionHandler().init();
    }
}
