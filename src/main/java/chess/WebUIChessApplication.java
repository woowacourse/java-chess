package chess;

import chess.domain.board.BoardDTO;
import chess.domain.command.Command;
import chess.domain.result.ChessResultDTO;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {

        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources";
        staticFiles.externalLocation(projectDir + staticDir);

        ChessService service = new ChessService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            putBoard(model, service);

            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            service.placeInitialPieces();

            putBoard(model, service);
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Command moveCommand = Command.from(Arrays.asList("move", req.queryParams("source"), req.queryParams("target")));
                service.move(moveCommand.getSource(), moveCommand.getTarget());
            } catch (RuntimeException e) {
                model.put("error", e.getMessage());
            }

            putBoard(model, service);
            return render(model, "index.html");
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("result", ChessResultDTO.create(service.calculateResult()));

            putBoard(model, service);
            return render(model, "index.html");
        });
    }

    private static void putBoard(Map<String, Object> model, ChessService service) {
        model.put("board", BoardDTO.create(service.getBoard()));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
