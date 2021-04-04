package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.MoveOnCommand;
import chess.domain.command.StartOnCommand;
import chess.dto.ChessGameDto;
import chess.dto.requestDto.MoveRequestDto;
import chess.dto.responseDto.ResponseDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {
    private final ChessService chessService;
    private ChessGame chessGame;

    public WebChessController() {
        this.chessService = new ChessService();
        this.chessGame = new ChessGame();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        staticFiles.location("/static");
        Command startOnCommand = new StartOnCommand();
        String[] temp = new String[0];
        startOnCommand.execute(chessGame, temp);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "board.html");
        });

        get("/data", (req, res) -> {
            ChessGameDto chessGameDto = new ChessGameDto(chessGame);
            String chessGameDtoJson = gson.toJson(chessGameDto);
            return gson.toJson(new ResponseDto(true, chessGameDtoJson));
        });

        post("/move", (req, res) -> {
            try {
                MoveRequestDto moveRequestDto = gson.fromJson(req.body(), MoveRequestDto.class);
                Command moveOnCommand = new MoveOnCommand();
                String[] sourceTarget = new String[]{"move", moveRequestDto.getSource(), moveRequestDto.getTarget()};
                moveOnCommand.execute(chessGame, sourceTarget);
                ChessGameDto chessGameDto = new ChessGameDto(chessGame);
                String chessGameDtoJson = gson.toJson(chessGameDto);
                return gson.toJson(new ResponseDto(true, chessGameDtoJson));
            } catch (Exception e) {
                return gson.toJson(new ResponseDto(false, e.getMessage()));
            }
        });

        post("/new-game", (req, res) -> {
            chessService.generateChessGame();
            return null;
        });

        get("/chess-game-list", (req, res) -> {
            List<Integer> chessGameList = chessService.getAllChessGameId();
            String chessGameListJson = gson.toJson(chessGameList);
            return gson.toJson(new ResponseDto(true, chessGameListJson));
        });
    }
}
