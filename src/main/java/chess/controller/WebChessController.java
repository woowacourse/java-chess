package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;
import chess.dto.BoardDTO;
import chess.dto.RequestDTO;
import chess.dto.ResultDTO;
import chess.repository.ChessRepository;
import chess.repository.ConnectionManager;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {
    private static TeamType CURRENT_TEAM_TYPE = TeamType.WHITE;

    private ChessBoard chessBoard;
    private ChessService chessService = new ChessService(new ChessRepository(ConnectionManager.makeConnection()));

    public WebChessController(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void getMainPage() {
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    public void getChessBoard() {
        get("/chessboard", (request, response) -> {
            response.type("application/json");
            BoardDTO boardDTO = chessService.get();
            return new Gson().toJsonTree(boardDTO);
        });
    }

    public void postMovement() {
        post("/chessboard/move", (request, response) -> {
            response.type("application/json");
            RequestDTO requestDTO = new Gson().fromJson(request.body(), RequestDTO.class);
            BoardDTO boardDTO = chessService.move(requestDTO);
            return new Gson().toJsonTree(boardDTO);
        });
    }

    public void getResult() {
        get("/chessboard/result", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "result.html");
        });
    }

    public void showResult() {
        get("/chessboard/result/show", (request, response) -> {
            response.type("application/json");
            Result result = chessBoard.calculateScores();
            TeamType winnerTeamType = chessBoard.findWinnerTeam();
            ResultDTO resultDTO = ResultDTO.from(result, winnerTeamType);
            return new Gson().toJsonTree(resultDTO);
        });
    }

    public void runExceptionHandler() {
        exception(RuntimeException.class, (exception, request, response) -> {
            response.status(500);
            response.type("application/json");
            response.body(exception.getMessage());
        });
    }
}
