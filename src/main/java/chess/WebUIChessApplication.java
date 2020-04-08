package chess;

import chess.controller.WebChessController;
import chess.controller.dao.Player;
import chess.controller.dto.MoveResultDto;
import chess.controller.dto.TeamDto;
import chess.controller.dto.TileDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");
        WebChessController webChessController = new WebChessController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "index.html");
        });

        post("/name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "name.html");
        });

        post("/load", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Player> players = webChessController.players();

            model.put("gameData", players);

            return render(model, "table.html");
        });

        post("/newGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String whitePlayer = req.queryParams("white-player");
            String blackPlayer = req.queryParams("black-player");

            Player player = new Player(whitePlayer, blackPlayer);
            webChessController.newGame(player);
            List<TileDto> tileDtos = webChessController.getTiles();
            TeamDto teamDto = webChessController.getCurrentTeam();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("player", player);

            return render(model, "game.html");
        });

        post("/continueGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int chessBoardId = Integer.parseInt(req.queryParams("chess-board-id"));
            String whitePlayer = req.queryParams("white-player");
            String blackPlayer = req.queryParams("black-player");

            webChessController.continueGame(chessBoardId, whitePlayer, blackPlayer);
            List<TileDto> tileDtos = webChessController.getTiles();
            TeamDto teamDto = webChessController.getCurrentTeam();
            Player player = webChessController.getPlayer();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("player", player);

           return render(model, "game.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String source = req.queryParams("source");
            String target = req.queryParams("target");

            MoveResultDto moveResultDto = webChessController.move(source, target);
            List<TileDto> tileDtos = webChessController.getTiles();
            TeamDto teamDto = webChessController.getCurrentTeam();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("message", moveResultDto.getMessage());
            model.put("style", moveResultDto.getStyle());

            if (webChessController.isEndGame()) {
                webChessController.deleteChessGame();
                return render(model, "end.html");
            }
            return render(model, "game.html");
        });

//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "index2.html");
//        });
//
//        post("/game", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//
//            webChessController.start();
//            List<TileDto> tileDtos = webChessController.getTiles();
//            TeamDto teamDto = webChessController.getCurrentTeam();
//
//            model.put("tiles", tileDtos);
//            model.put("currentTeam", teamDto);
//
//            return render(model, "game.html");
//        });
//
//
//        post("/status", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//
//            List<TileDto> tileDtos = webChessController.getTiles();
//            TeamDto teamDto = webChessController.getCurrentTeam();
//            String message = webChessController.getScores();
//
//            model.put("tiles", tileDtos);
//            model.put("currentTeam", teamDto);
//            model.put("message", message);
//
//            return render(model, "game.html");
//        });
//
//        post("/end", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//
//            webChessController.deleteChessGame();
//            String message = webChessController.getScores();
//
//            model.put("message", message);
//
//            return render(model, "end.html");
//        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
