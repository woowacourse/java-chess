package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.WebUIChessGameController;
import chess.dao.ChessDao;
import chess.domain.game.ChessGame;
import chess.dto.ChessGameDto;
import chess.dto.CommandsDto;
import chess.dto.CreateRequestDto;
import chess.dto.StartRequestDto;
import chess.dto2.MovableRequestDto;
import chess.dto2.MoveRequestDto;
import chess.utils.DtoAssembler;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        Gson gson = new Gson();
        staticFiles.location("/public");
        WebUIChessGameController webUIChessGameController = new WebUIChessGameController();
        ChessDao chessDao = new ChessDao();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("gameNames", chessDao.gameNames());
            return render(model, "chess-game-list.html");
        });

        post("/tryCreateGame", (req, res) -> {
            CreateRequestDto createRequestDto = gson.fromJson(req.body(), CreateRequestDto.class);
            chessDao.createGameByName(createRequestDto);
            ChessGame chessGame = webUIChessGameController.chessGame();
            return DtoAssembler.chessGameDto(chessGame);
        }, gson::toJson);

        get("/:name", (req, res) -> {
            String gameName = req.params(":name");
            CommandsDto commandsDto = chessDao.findCommandsByName(gameName);
            ChessGame chessGame = webUIChessGameController.chessGame(commandsDto.getCommands());

            Map<String, Object> model = new HashMap<>();
            model.put("gameName", req.params(":name"));
            model.put("gameState", chessGame.state());

            return render(model, "chess-game.html");
        });

        post("/start", (req, res) -> {
            StartRequestDto startRequestDto = gson.fromJson(req.body(), StartRequestDto.class);
            chessDao.insertStartCommand(startRequestDto);
            chessDao.updatePlayerIds(startRequestDto);
            CommandsDto commandsDto = chessDao.findCommandsByName(startRequestDto.getGameName());
            ChessGame chessGame = webUIChessGameController.chessGame(commandsDto.getCommands());
            return DtoAssembler.chessGameDto(chessGame);
        }, gson::toJson);

        post("/movable", (req, res) -> {
            MovableRequestDto movableRequestDto = gson
                .fromJson(req.body(), MovableRequestDto.class);
            return webUIChessGameController.movablePath(movableRequestDto);
        }, gson::toJson);

        post("/move", (req, res) -> {
            MoveRequestDto moveRequestDto = gson.fromJson(req.body(), MoveRequestDto.class);
            ChessGameDto chessGameDto = webUIChessGameController.move(moveRequestDto);
            chessDao.updateBoard(chessGameDto);
            return chessGameDto;
        }, gson::toJson);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
