package chess.controller.web;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.Board;
import chess.Team;
import chess.Turn;
import chess.controller.console.dto.PieceDto;
import chess.piece.Pieces;
import chess.service.ChessService;
import chess.service.dto.BoardDto;
import chess.service.dto.MoveDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessWebController {

    private final ChessService chessService;

    public ChessWebController(ChessService chessService) {
        this.chessService = chessService;
    }

    private static String render (Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {

        Gson gson = new Gson();

        get("/", (req, res) -> render(new HashMap<>(), "index.html"));

        get("/start", (req, res) -> {
            Board board = chessService.loadGame();
            Turn turn = board.getTurn();
            Team currentTeam = turn.getTeam();
            return gson.toJson(BoardDto.of(currentTeam.name(), board.getPieces().getPieces()));
        });

//        get("/end", (req, res) -> {
//            return gson.toJson(chessService.endGame());
//        });
//
//        get("/status", (req, res) -> {
//            return gson.toJson(chessService.createStatus());
//        });

        post("/move", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            Board board = chessService.move(moveDto);
            Turn turn = board.getTurn();
            Team currentTeam = turn.getTeam();
            return gson.toJson(BoardDto.of(currentTeam.name(), board.getPieces().getPieces()));
        });
    }
}
