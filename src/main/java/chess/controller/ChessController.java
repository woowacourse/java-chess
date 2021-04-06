package chess.controller;

import chess.domain.ChessGame;
import chess.domain.DTO.BoardDTO;
import chess.domain.DTO.MoveInfoDTO;
import chess.domain.DTO.ScoreDTO;
import chess.domain.board.BoardFactory;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessController {
    public static final BoardFactory boardFactory = new BoardFactory();
    public final ChessService chessService;

    public ChessController() {
        chessService = new ChessService();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        Gson gson = new Gson();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "chess.html");
        });

        get("/loadSavedBoard", (req, res) -> {
            BoardDTO savedBoardInfo = chessService.getSavedBoardInfo(chessGame);
            return gson.toJson(savedBoardInfo);
        });

        get("/resetBoard", (req, res) -> {
            BoardDTO boardDTO = chessService.initiateBoard(chessGame);
            return gson.toJson(boardDTO);
        });

        get("/scoreStatus", (req, res) -> {
            ScoreDTO scoreDTO = ScoreDTO.of(chessGame.scoreStatus());
            return gson.toJson(scoreDTO);
        });

        post("/move", (req, res) -> {
            MoveInfoDTO moveInfoDTO = gson.fromJson(req.body(), MoveInfoDTO.class);
            BoardDTO boardDTO = chessService.move(chessGame, moveInfoDTO);
            return gson.toJson(boardDTO);
        });
    }
}