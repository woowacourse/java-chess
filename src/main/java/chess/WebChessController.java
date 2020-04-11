package chess;

import chess.dto.ChessPositionDTO;
import chess.service.Service;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {

    private Service service;

    public WebChessController() {
        staticFileLocation("templates");
        service = new Service();
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/chessStart", (req, res) -> service.initialChessBoard());

        get("/chess", (req, res) -> {
            Map<String, Object> model = service.settingChessBoard();
            return render(model, "contents/chess.html");
        });

        post("/move", (req, res) -> {
            ChessPositionDTO chessPositionDTO =
                    new ChessPositionDTO(req.queryParams("source"), req.queryParams("target"));
            try {
                return service.moveChessBoard(chessPositionDTO);
            } catch (Exception e) {
                res.status(403);
                return e.getMessage();
            }
        });

        post("/status", (req, res) -> {
            res.body(String.format("%s점수: %.1f", service.getColor(), service.calculateScore()));
            return res.body();
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
