package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.WebUIChessGameController;
import chess.dao.ChessDao;
import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import chess.dto.ChessGameDto;
import chess.dto.CommandsDto;
import chess.dto.CreateRequestDto;
import chess.dto.MovableRequestDto;
import chess.dto.MoveRequestDto;
import chess.dto.PlayerIdsDto;
import chess.dto.ScoreDto;
import chess.dto.SquareDto;
import chess.dto.UserIdsDto;
import chess.utils.DtoAssembler;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            model.put("gameNames", chessDao.runningGameNames());
            return render(model, "chess-game-list.html");
        });

        post("/tryCreateGame", (req, res) -> {
            CreateRequestDto createRequestDto = gson.fromJson(req.body(), CreateRequestDto.class);
            ChessGameDto chessGameDto = webUIChessGameController.chessGame();
            chessDao.createGameByName(createRequestDto, chessGameDto);
            return chessGameDto;
        }, gson::toJson);

        get("/:name", (req, res) -> {
            String gameName = req.params(":name");
            Map<String, Object> model = new HashMap<>();
            model.put("gameName", gameName);
            return render(model, "chess-game.html");
        });

        post("/:name/load", (req, res) -> {
            String gameName = req.params(":name");
            String state = chessDao.findStateByName(gameName);
            List<SquareDto> squareDtos = chessDao.findSquaresByName(gameName);
            return DtoAssembler.chessGameDto(squareDtos, state);
        }, gson::toJson);

        post("/:name/start", (req, res) -> {
            String gameName = req.params(":name");
            PlayerIdsDto playerIdsDto = gson.fromJson(req.body(), PlayerIdsDto.class);
            ChessGameDto chessGameDto = chessDao.findGameByName(gameName);
            ChessGameDto startedChessGameDto = webUIChessGameController.startedChessGame(chessGameDto);
            chessDao.updatePlayerIds(playerIdsDto, gameName);
            chessDao.updateGameByName(gameName, startedChessGameDto);
            return startedChessGameDto;
        }, gson::toJson);

        post("/:name/movable", (req, res) -> {
            String gameName = req.params(":name");
            MovableRequestDto movableDto = gson.fromJson(req.body(), MovableRequestDto.class);
            ChessGameDto chessGameDto = chessDao.findGameByName(gameName);
            return webUIChessGameController.movablePositions(chessGameDto, movableDto);
        }, gson::toJson);
        
        post("/:name/move", (req, res) -> {
            String gameName = req.params(":name");
            MoveRequestDto moveDto = gson.fromJson(req.body(), MoveRequestDto.class);
            ChessGameDto chessGameDto = chessDao.findGameByName(gameName);
            ChessGameDto movedChessGameDto = webUIChessGameController.movedChessGame(chessGameDto, moveDto);
            chessDao.updateGameByName(gameName, movedChessGameDto);
            return movedChessGameDto;
        }, gson::toJson);

        // refactor list 7
        post("/:name/score", (req, res) -> {
            String gameName = req.params(":name");
            CommandsDto commandsDto = chessDao.findCommandsByName(gameName);
            ChessGame chessGame = webUIChessGameController.chessGame(commandsDto.getCommands());
            return DtoAssembler.scoreDto(chessGame.score());
        }, gson::toJson);

        // refactor list 8
        get("/search/search-page", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "chess-game-search.html");
        });

        // refactor list 9
        post("/search/search", (req, res) -> {
            UserIdsDto userIdsDto = gson.fromJson(req.body(), UserIdsDto.class);
            List<CommandsDto> commandsByUserIds = chessDao
                .findCommandsByUserIds(userIdsDto.getWhiteUserId());
            List<UserIdsDto> userIdsDtos = chessDao
                .findUserIdsByUserId(userIdsDto.getWhiteUserId());
            List<String> states = new ArrayList<>();
            List<ScoreDto> scoreDtos = new ArrayList<>();

            for (CommandsDto commandsDto : commandsByUserIds) {
                ChessGame chessGame = webUIChessGameController.chessGame(commandsDto.getCommands());
                scoreDtos.add(new ScoreDto(chessGame.score().white(), chessGame.score().black()));
                states.add(chessGame.state());
            }

            return DtoAssembler.searchResultDto(states, userIdsDtos, scoreDtos);
        }, gson::toJson);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
