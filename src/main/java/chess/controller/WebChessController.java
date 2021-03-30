package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.board.Coordinate;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;
import chess.dto.BoardDTO;
import chess.dto.RequestDTO;
import chess.dto.ResultDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {
    private static TeamType CURRENT_TEAM_TYPE = TeamType.WHITE;

    private ChessBoard chessBoard;

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
            if (chessBoard.isKingCheckmate()) {
                this.chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
                CURRENT_TEAM_TYPE = TeamType.WHITE;
            }
            BoardDTO boardDTO = BoardDTO.from(chessBoard, CURRENT_TEAM_TYPE);
            return new Gson().toJsonTree(boardDTO);
        });
    }

    public void postMovement() {
        post("/chessboard/move", (request, response) -> {
            response.type("application/json");
            RequestDTO requestDTO = new Gson().fromJson(request.body(), RequestDTO.class);
            Coordinate current = Coordinate.from(requestDTO.getCurrent());
            Coordinate destination = Coordinate.from(requestDTO.getDestination());
            TeamType teamType = TeamType.valueOf(requestDTO.getTeamType());
            chessBoard.move(current, destination, teamType);
            CURRENT_TEAM_TYPE = teamType.findOppositeTeam();
            BoardDTO boardDTO = BoardDTO.from(chessBoard, CURRENT_TEAM_TYPE);
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
