package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardDTO;
import chess.domain.command.Command;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Spark;
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
            Board board = service.placeInitialPieces();
            model.put("board", BoardDTO.from(board));

            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Board board = service.placeInitialPieces();
            model.put("board", BoardDTO.from(board));

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Command moveCommand = Command.from(Arrays.asList("move", req.queryParams("source"), req.queryParams("target")));
            Board board = service.move(moveCommand.getSource(), moveCommand.getTarget());
            model.put("board", BoardDTO.from(board));

            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
