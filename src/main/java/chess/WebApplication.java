package chess;

import chess.web.WebConfig;
import chess.web.WebRouter;

public class WebApplication {

    private static final WebConfig config = new WebConfig();
    private static final WebRouter router = new WebRouter();

    public static void main(String[] args) {
        config.init();
        router.init();
    }
}
