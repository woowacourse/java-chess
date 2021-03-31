package chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.Chess;
import chess.domain.board.BoardDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        List<Chess> chessList = new ArrayList<>();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/new", (req, res) -> {
            Chess chess = Chess.createWithEmptyBoard().start();
            chessList.add(chess);
            Set<Map.Entry<String, String>> board = BoardDTO.from(chess).getPieceDTOs();
            Map<String, Object> model = new HashMap<>();
            model.put("board", board);
            return render(model, "chess.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
