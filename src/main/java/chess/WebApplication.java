package chess;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

import chess.domain.board.BoardFactory;
import chess.view.JsonTransformer;
import chess.view.WebViewMapper;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", WebViewMapper.parse(BoardFactory.newInstance().getBoard()));
            return render(model, "index.html");
        });

        get("/board", (request, response) -> WebViewMapper.parse(BoardFactory.newInstance().getBoard())
                , new JsonTransformer());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
