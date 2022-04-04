package chess.web.controller;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.Command;
import chess.domain.piece.Team;
import chess.web.service.ChessService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        staticFiles.location("/static");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (request,response) -> {
            Map<String, Object> model = new HashMap<>();

            String gameName = request.queryParams("gameName");

            List<String> chessBoard = chessService.createChessBoard(gameName);
            model.put("chessboard", chessBoard);

            return render(model, "chess.html");
        });

        post("/move", (request,response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> chessBoard = chessService.getCurrentChessBoard();

            String moveCommand = makeCommand(request);

            try {
                chessBoard = chessService.move(moveCommand);
                model.put("chessboard", chessBoard);
            } catch(IllegalArgumentException e) {
                model.put("error", e.getMessage());
                model.put("chessboard", chessBoard);
            }

            return render(model, "chess.html");
        });

        get("/status", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            Map<Team, Double> score = chessService.getScore();

            List<String> chessBoard = chessService.getCurrentChessBoard();

            model.put("blackScore", score.get(BLACK));
            model.put("whiteScore", score.get(WHITE));
            model.put("chessboard", chessBoard);

            return render(model, "chess.html");
        });

        get("/end", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String winTeamName = chessService.finish(Command.from("end"));

            List<String> chessBoard = chessService.getCurrentChessBoard();

            model.put("winTeam", winTeamName);
            model.put("chessboard", chessBoard);

            return render(model, "chess.html");
        });

        get("/save", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            chessService.save();

            List<String> chessBoard = chessService.getCurrentChessBoard();

            model.put("chessboard", chessBoard);

            return render(model, "chess.html");
        });
    }

    private String makeCommand(Request request) {
        String from = request.queryParams("from");
        String to = request.queryParams("to");

        return "move " + from + " " + to;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
