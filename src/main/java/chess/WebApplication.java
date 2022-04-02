package chess;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

import chess.router.WebRouter;

public class WebApplication {

    private static final WebRouter router = new WebRouter();

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/static");
        router.initHomeRouteHandler();
        router.initSearchRouteHandler();
        router.initGameRouterHandler();
        router.initResultRouterHandler();
    }
}
