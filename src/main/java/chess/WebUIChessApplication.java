package chess;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
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
            whiteTeam = Team.whiteTeam();
            blackTeam = Team.blackTeam();
            chessGame = new ChessGame(blackTeam, whiteTeam);
            final Map<Position, Piece> piecePosition = chessGame.generateChessBoard();
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/", (req, res) -> {
            Gson gson = new Gson();
            final MoveRequest moveRequest = gson.fromJson(req.body(), MoveRequest.class);
            final String start = moveRequest.getStart();
            final String destination = moveRequest.getDestination();
            try {
                chessGame.move(Position.of(start), Position.of(destination));
                res.status(200);
                return gson.toJson(generateMoveResponse(start, destination));
            } catch (Exception e) {
                res.status(404);
                return gson.toJson(generateMoveResponse(start, destination));
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static MoveResponse generateMoveResponse(String start, String destination) {
        final double whiteTeamScore = chessGame.calculateWhiteTeamScore();
        final double blackTeamScore = chessGame.calculateBlackTeamScore();
        final String currentTurnTeam = convertCurrentTurnTeamToString();
        final boolean isPlaying = chessGame.isPlaying();
        return new MoveResponse(start, destination, whiteTeamScore, blackTeamScore, currentTurnTeam, isPlaying);
    }

    private static String convertCurrentTurnTeamToString() {
        final Team currentTurnTeam = chessGame.getCurrentTurnTeam();
        if (currentTurnTeam.equals(whiteTeam)) {
            return "whiteTeam";
        }
        return "blackTeam";
    }
}
