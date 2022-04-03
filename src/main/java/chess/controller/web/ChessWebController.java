package chess.controller.web;

import chess.Board;
import chess.Team;
import chess.Turn;
import chess.piece.Piece;
import chess.service.ChessService;
import chess.service.ScoreDto;
import chess.service.dto.BoardDto;
import chess.service.dto.MoveDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {

    private final ChessService chessService;

    public ChessWebController(ChessService chessService) {
        this.chessService = chessService;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {

        Gson gson = new Gson();

        get("/", (req, res) -> render(new HashMap<>(), "index.html"));

        get("/api/load", (req, res) -> {
            Board board = chessService.loadGame();
            BoardDto boardDto = convertToDto(board);
            return gson.toJson(boardDto);
        });

        get("/api/end", (req, res) -> {
            Board board = chessService.initBoard();
            BoardDto boardDto = convertToDto(board);
            return gson.toJson(boardDto);
        });

        get("/api/status", (req, res) -> {
            ScoreDto status = chessService.getStatus();
            return gson.toJson(status);
        });

        post("/api/move", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            Board board = chessService.move(moveDto);
            BoardDto boardDto = convertToDto(board);
            return gson.toJson(boardDto);
        });
    }

    private BoardDto convertToDto(Board board) {
        Map<String, String> collect = board.getPieces().getPieces().stream()
                .collect(Collectors.toMap(
                        piece -> piece.getPosition().name(),
                        Piece::getName
                ));

        return new BoardDto(getCurrentTurn(board), collect, board.isDeadKing());
    }

    private String getCurrentTurn(Board board) {
        Turn turn = board.getTurn();
        Team currentTeam = turn.getTeam();
        return currentTeam.value();
    }
}
