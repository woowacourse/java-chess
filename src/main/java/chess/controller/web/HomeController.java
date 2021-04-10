package chess.controller.web;

import static spark.Spark.get;

import chess.controller.web.utils.RenderUtil;
import java.util.Collections;

public class HomeController {

    public HomeController() {
        get("/", (req, res) ->
            RenderUtil.render(Collections.emptyMap(), "home.html")
        );
    }
}
