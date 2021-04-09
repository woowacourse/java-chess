package chess;

import chess.dto.RequestDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");
        port(8080);

        final JsonTransformer jsonTransformer = new JsonTransformer();
        final ChessService chessService = new ChessService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessService.init();
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            RequestDto requestDto = jsonTransformer.getGson().fromJson(req.body(), RequestDto.class);
            return chessService.move(requestDto);
        }, jsonTransformer);

        post("/board", (req, res) -> {
            return chessService.getCurrentBoard();
        }, jsonTransformer);

        post("/restart", (req, res) -> {
            chessService.initChessBoard();
            return "초기화 성공";
        }, jsonTransformer);

        post("/turn", (req, res) -> {
            return chessService.turn();
        }, jsonTransformer);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
