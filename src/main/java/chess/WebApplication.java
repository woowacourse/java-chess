package chess;

import chess.web.WebConfig;
import chess.web.WebRouter;

public class WebApplication {

    private final static WebConfig config = new WebConfig();
    private static final WebRouter router = new WebRouter();

    public static void main(String[] args) {
        config.init();
        router.init();
    }
}
