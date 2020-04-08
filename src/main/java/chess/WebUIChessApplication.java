package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.player.User;
import chess.dto.LineDto;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {

        Spark.staticFiles.location("/templates");

        ChessService chessService = new ChessService();

        get("/main", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Board board = chessService.createEmpty();
            List<LineDto> rows = board.getRows();
            model.put("rows", rows);
            return render(model, "main.html");
        });

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String firstUserName = req.queryParams("black");
            String secondUserName = req.queryParams("white");
            User first = new User(firstUserName);
            User second = new User(secondUserName);

            Board board = chessService.findByUserName(first, second);
            List<LineDto> rows = board.getRows();
            model.put("firstUser", firstUserName);
            model.put("secondUser", secondUserName);
            model.put("rows", rows);
            return render(model, "board.html");
        });

        post("/path", (req, res) -> {
            String source = req.queryParams("source");
            String firstUserName = req.queryParams("black");
            Board board;
            try {
                board = chessService.getBoard(new User(firstUserName));
                return board.searchPath(source);
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        });

        post("/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            String firstUserName = req.queryParams("black");
            Board board;
            try {
                board = chessService.move(new User(firstUserName), source, target);
            } catch (RuntimeException e) {
                return e.getMessage();
            }
            if (board.isNotFinished()) {
                return true;
            }
            return "finished";
        });

        post("/save", (req, res) -> {
            String firstUserName = req.queryParams("black");
            String secondUserName = req.queryParams("white");
            chessService.save(new User(firstUserName), new User(secondUserName));
            Map<String, Object> model = new HashMap<>();
            Board board = chessService.createEmpty();
            List<LineDto> rows = board.getRows();
            model.put("rows", rows);
            return render(model, "main.html");
        });

        post("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String firstUserName = req.queryParams("black");
            return render(model, "status.html");
        });

        post("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String firstUserName = req.queryParams("black");
            String secondUserName = req.queryParams("white");
            chessService.delete(new User(firstUserName), new User(secondUserName));
            Board board = chessService.createEmpty();
            List<LineDto> rows = board.getRows();
            model.put("rows", rows);
            return render(model, "main.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
