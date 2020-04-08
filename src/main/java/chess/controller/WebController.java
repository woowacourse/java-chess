package chess.controller;

import chess.controller.dto.BoardDTO;
import chess.controller.dto.ChessResultDTO;
import chess.controller.dto.UserDTO;
import chess.domain.board.Board;
import chess.domain.result.ChessResult;
import chess.service.ChessGame;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {

    private ChessGame chessGame;

    public WebController() {
        this.chessGame = new ChessGame();
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", BoardDTO.create(Board.createEmpty()));
            model.put("rankers", UserDTO.createList(chessGame.findRankers()));

            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                String whiteName = req.queryParams("white");
                String blackName = req.queryParams("black");

                Board board = chessGame.start(whiteName, blackName);

                model.put("white", whiteName);
                model.put("black", blackName);
                model.put("board", BoardDTO.create(board));
            } catch (Exception e) {
                model.put("error", e.getMessage());
            }

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                String whiteName = req.queryParams("white");
                String blackName = req.queryParams("black");
                String sourceName = req.queryParams("source");
                String targetName = req.queryParams("target");

                Board board = chessGame.move(whiteName, blackName, sourceName, targetName);

                model.put("white", whiteName);
                model.put("black", blackName);
                model.put("board", BoardDTO.create(board));
            } catch (Exception e) {
                model.put("error", e.getMessage());
            }

            return render(model, "index.html");
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                String whiteName = req.queryParams("white");
                String blackName = req.queryParams("black");

                ChessResult status = chessGame.status(whiteName, blackName);

                model.put("result", ChessResultDTO.create(status));
            } catch (Exception e) {
                model.put("error", e.getMessage());
            }
            return render(model, "result.html");
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
