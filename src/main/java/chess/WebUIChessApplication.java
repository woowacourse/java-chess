package chess;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.team.Team;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/public");
        final ChessGame chessGame = new ChessGame(Team.blackTeam(), Team.whiteTeam());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/", (req, res) -> {
            Gson gson = new Gson();
            final MoveDTO moveDTO = gson.fromJson(req.body(), MoveDTO.class);
            final String start = moveDTO.getStart();
            final String destination = moveDTO.getDestination();
            try {
                chessGame.move(Position.of(start), Position.of(destination));
                res.status(200);
                return gson.toJson(new MoveDTO(start, destination));
            } catch (Exception e) {
                res.status(404);
                return gson.toJson(new MoveDTO(start, destination));
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
