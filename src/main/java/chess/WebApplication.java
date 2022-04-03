package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.command.Command;
import chess.command.Move;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    public static void main(String[] args) {
        staticFileLocation("/static");
        GameController gameController = new GameController();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            gameController.start();
            res.redirect("/play");
            return null;
        });

        get("/play", (req, res) -> {
            Map<Position, Piece> board = gameController.getBoard();
            Map<String, Object> model = board.entrySet().stream()
                    .collect(Collectors.toMap(entry -> entry.getKey().toString(), Map.Entry::getValue));
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, String> positions = Arrays.stream(req.body().split("&"))
                    .collect(Collectors.toMap(
                            data -> data.substring(0, data.indexOf("=")),
                            data -> data.substring(data.indexOf("=") + 1)
                    ));
            System.out.println("소스 위치 : " + positions.get("source"));
            gameController.move(Position.of(positions.get("source")), Position.of(positions.get("target")));
            res.redirect("/play");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
