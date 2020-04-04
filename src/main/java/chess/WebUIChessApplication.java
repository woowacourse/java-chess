package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.board.Board;
import domain.board.BoardFactory;
import domain.piece.team.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");
        Board board = BoardFactory.create();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            List<String> pieces = board.allPieces();
            Map<String, Object> model = new HashMap<>();
            model.put("pieces", pieces);
            model.put("blackTeamScore", board.calculateTeamScore(Team.BLACK));
            model.put("whiteTeamScore", board.calculateTeamScore(Team.WHITE));

            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
