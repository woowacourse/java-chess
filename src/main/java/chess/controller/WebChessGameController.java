package chess.controller;

import chess.JsonTransformer;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.dto.PiecesDTO;
import chess.dto.StatusDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessGameController {
    public void start() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/enter", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("button", "시작");
            model.put("isWhite", true);
            return render(model, "chess.html");
        });

        ChessGame chessGame = new ChessGame();
        chessGame.initialize();

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            gameInformation(chessGame, model);
            return render(model, "chess.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.move(req.queryParams("startPoint"), req.queryParams("endPoint"));
            gameInformation(chessGame, model);
            return render(model, "chess.html");
        });

        post("/myTurn", "application/json", (req, res) -> {
            String clickedSection = req.queryParams("clickedSection");
            return chessGame.checkRightTurn(clickedSection);
        }, new JsonTransformer());

        post("/movablePositions", (req, res) -> {
            String startPoint = req.queryParams("clickedSection");
            return chessGame.movablePositionsByStartPoint(startPoint);
        }, new JsonTransformer());

        post("/move", "application/json", (req, res) -> {
            String startPoint = req.queryParams("startPoint");
            String endPoint = req.queryParams("endPoint");
            chessGame.move(startPoint, endPoint);
            return currentStatus(chessGame);
        }, new JsonTransformer());
    }

    private StatusDTO currentStatus(ChessGame chessGame) {
        String turn = chessGame.turn().name();
        return new StatusDTO(turn, chessGame);
    }

    private void gameInformation(ChessGame chessGame, Map<String, Object> model) {
        PiecesDTO piecesDTOs = PiecesDTO.create(chessGame.board());
        model.put("pieces", piecesDTOs.getPieceDTOs());
        model.put("button", "초기화");
        model.put("isWhite", Team.WHITE.equals(chessGame.turn()));
        model.put("black-score", chessGame.scoreByTeam(Team.BLACK));
        model.put("white-score", chessGame.scoreByTeam(Team.WHITE));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
