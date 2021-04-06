package chess;

import chess.dao.ChessBoardDao;
import chess.dto.MovementDto;
import chess.service.ChessServiceImpl;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final ChessServiceImpl chessService = new ChessServiceImpl(new ChessBoardDao());

    public static void main(String[] args) {
        staticFileLocation("/static");
        Gson gson = new Gson();

        post("/chessboard", (req, res) -> chessService.move(gson.fromJson(req.body(), MovementDto.class)), gson::toJson);

        post("/chessboard/save", (req, res) -> chessService.saveAndShowResult(), gson::toJson);

        get("/chessboard", (req, res) -> {
            res.type("application/json");
            return chessService.getDefaultChessBoard();
        }, gson::toJson);

        get("/chessboard/result", (req, res) -> {
            res.type("application/json");
            return chessService.getResult();
        }, gson::toJson);

        get("/chessboard/end", (req, res) -> {
            res.type("application/json");
            return chessService.terminateGameAndGetResult();
        }, gson::toJson);

        get("/chessboard/saved", (req, res) -> {
            res.type("application/json");
            return chessService.getSavedChessBoard();
        }, gson::toJson);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
