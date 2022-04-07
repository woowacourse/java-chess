package web;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.dto.MoveReqeust;
import web.service.ChessService;

public class WebApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");

        JsonTransformer jsonTransformer = new JsonTransformer();
        ChessService chessService = new ChessService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessService.init();
            return render(model, "index.html");
        });

        post("/board", (req, res) -> chessService.currentBoard(), jsonTransformer);

        post("/move", (req, res) -> {
            MoveReqeust moveReqeust = jsonTransformer.getGson()
                .fromJson(req.body(), MoveReqeust.class);
            return chessService.move(moveReqeust);
        }, jsonTransformer);

        post("/score", (req, res) -> chessService.score(), jsonTransformer);

        post("/turn", (req, res) -> chessService.turn(), jsonTransformer);

        post("/restart", (req, res) -> {
            chessService.initChessBoard();
            return "보드 초기화 성공";
        }, jsonTransformer);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
