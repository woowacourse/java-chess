package chess.controller;

import chess.dao.Player;
import chess.dto.MoveResultDTO;
import chess.dto.TeamDTO;
import chess.dto.TileDTO;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {
    private ChessService chessService;

    public void playChess() {
        this.chessService = new ChessService();

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

            List<Player> players = this.chessService.players();

            model.put("gameData", players);

            return render(model, "table.html");
        });

        post("/newGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String whitePlayer = req.queryParams("white-player");
            String blackPlayer = req.queryParams("black-player");

            Player player = new Player(whitePlayer, blackPlayer);
            this.chessService.newGame(player);
            List<TileDTO> tileDtos = this.chessService.getTiles();
            TeamDTO teamDto = this.chessService.getCurrentTeam();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("player", player);

            return render(model, "game.html");
        });

        post("/continueGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int chessBoardId = Integer.parseInt(req.queryParams("chess-board-id"));

            this.chessService.continueGame(chessBoardId);
            List<TileDTO> tileDtos = this.chessService.getTiles();
            TeamDTO teamDto = this.chessService.getCurrentTeam();
            Player player = this.chessService.getPlayer();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("player", player);

            return render(model, "game.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String source = req.queryParams("source");
            String target = req.queryParams("target");

            MoveResultDTO moveResultDto = this.chessService.move(source, target);
            List<TileDTO> tileDtos = this.chessService.getTiles();
            TeamDTO teamDto = this.chessService.getCurrentTeam();
            Player player = this.chessService.getPlayer();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("message", moveResultDto.getMessage());
            model.put("style", moveResultDto.getStyle());
            model.put("player", player);

            if (this.chessService.isEndGame()) {
                this.chessService.deleteChessGame();
                return render(model, "end.html");
            }
            return render(model, "game.html");
        });

        post("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<TileDTO> tileDtos = this.chessService.getTiles();
            TeamDTO teamDto = this.chessService.getCurrentTeam();
            String message = this.chessService.getScores();
            Player player = this.chessService.getPlayer();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("message", message);
            model.put("player", player);

            return render(model, "game.html");
        });

        post("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            this.chessService.deleteChessGame();
            String message = this.chessService.getScores();

            model.put("message", message);

            return render(model, "end.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
