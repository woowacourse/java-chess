package chess;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.team.Team;
import chess.webdto.ChessGameInfoDTO;
import chess.webdto.MoveRequestDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static ChessGame chessGame;
    private static Team whiteTeam;
    private static Team blackTeam;

    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/startNewGame", (req, res) -> {
            Gson gson = new Gson();
            whiteTeam = Team.whiteTeam();
            blackTeam = Team.blackTeam();
            chessGame = new ChessGame(blackTeam, whiteTeam);
            return gson.toJson(ChessGameInfoDTO.of(chessGame, convertCurrentTurnTeamToString()));
        });

        post("/move", (req, res) -> {
            Gson gson = new Gson();
            final MoveRequestDTO moveRequestDTO = gson.fromJson(req.body(), MoveRequestDTO.class);
            final String start = moveRequestDTO.getStart();
            final String destination = moveRequestDTO.getDestination();
            try {
                chessGame.move(Position.of(start), Position.of(destination));
                res.status(200);
                return gson.toJson(ChessGameInfoDTO.of(chessGame, convertCurrentTurnTeamToString()));
            } catch (Exception e) {
                res.status(404);
                return gson.toJson(ChessGameInfoDTO.of(chessGame, convertCurrentTurnTeamToString()));
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }


    private static String convertCurrentTurnTeamToString() {
        final Team currentTurnTeam = chessGame.getCurrentTurnTeam();
        if (currentTurnTeam.equals(whiteTeam)) {
            return "whiteTeam";
        }
        return "blackTeam";
    }
}
