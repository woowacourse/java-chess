package chess;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebApplication {

    public static void main(String[] args) {
        staticFileLocation("templates");

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        post("/board", (req, res) -> {
            Map<Position, AbstractPiece> pieces = PieceFactory.createChessPieces();
            Map<String, Object> model = new HashMap<>();
            List<PieceDto> pieceDtos = pieces.keySet()
                    .stream()
                    .map(position -> new PieceDto(position.getName(), pieces.get(position).signature()))
                    .collect(Collectors.toList());
            model.put("pieces", pieceDtos);
            return render(model, "board.html");
        });

        post("/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            System.out.println("move command : " + source + "->" + target);
            Map<String, Object> model = new HashMap<>();

            return render(model, "board.html");
        });

        post("/afdf", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("name", req.queryParams("name"));
            model.put("age", req.queryParams("age"));

            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}