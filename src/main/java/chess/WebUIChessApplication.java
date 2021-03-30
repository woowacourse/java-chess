package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import chess.domain.dto.BoardDto;
import chess.utils.BoardUtil;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/test", (req, res) -> {
            res.status(200);
            res.type("application/json");
            BoardDto boardDto = BoardDto.from(BoardUtil.generateInitialBoard());
            return gson.toJson(boardDto);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
