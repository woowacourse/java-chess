package chess;

import chess.dto.ChessBoardDto;
import chess.dto.ChessPositionDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int roundId = ChessService.getInstance().getLastRoundId();
            req.session().attribute("roundId", roundId);
            return render(model, "index.html");
        });

        get("/chessBoard", ((request, response) -> {
            Gson gson = new Gson();
            int roundId = request.session().attribute("roundId");

            return gson.toJson(ChessService.getInstance().getChessBoardDTO(roundId));
        }));

        post("/chessBoard", ((request, response) -> {
            Gson gson = new Gson();
            ChessPositionDto chessPositionDto = gson.fromJson(request.body(), ChessPositionDto.class);

            int roundId = request.session().attribute("roundId");

            try {
                ChessBoardDto chessBoardDTO = ChessService.getInstance().getChessBoard(chessPositionDto, roundId);
                return gson.toJson(chessBoardDTO);
            } catch (IllegalArgumentException e) {
                return "error";
            }
        }));

        get("/chessScore", ((request, response) -> {
            Gson gson = new Gson();

            int roundId = request.session().attribute("roundId");

            return gson.toJson(ChessService.getInstance().getChessScore(roundId));
        }));

        post("/newRound", ((request, response) -> {
            Gson gson = new Gson();
            int roundId = request.session().attribute("roundId");
            roundId++;
            ChessService.getInstance().addRound(roundId);
            request.session().attribute("roundId", roundId);
            return gson.toJson(ChessService.getInstance().getChessBoardDTO(roundId));
        }));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
