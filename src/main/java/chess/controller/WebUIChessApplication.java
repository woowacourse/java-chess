package chess.controller;

import chess.domain.chess.ChessBoard;
import chess.domain.chess.Team;
import chess.domain.chess.dao.ChessBoardDAO;
import chess.domain.chess.dto.ChessBoardDTO;
import chess.domain.chess.initializer.ChessBoardInitializer;
import chess.domain.geometric.Position;
import chess.service.ChessBoardService;
import chess.util.DBConnection;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Connection connection = DBConnection.getConnection();
    private static ChessBoardService chessBoardService = new ChessBoardService(connection);

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessBoardDTO chessBoardDTO = new ChessBoardDTO();

            chessBoardService.createGame();
            chessBoardDTO.setUnits(chessBoardService.selectRecentGame().getUnits());

            model.put("team", Team.WHITE.getName());
            model.put("chessBoard", new Gson().toJson(chessBoardDTO));

            return render(model, "game.html");
        });

        get("/resume", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
            ChessBoard chessBoard = chessBoardService.selectRecentGame();

            if (chessBoard.checkKing()) {
                model.put("team", chessBoard.getAliveKingTeam());
                return render(model, "gameover.html");
            }

            chessBoardDTO.setUnits(chessBoard.getUnits());
            model.put("team", chessBoard.getTeam().name());
            model.put("chessBoard", new Gson().toJson(chessBoardDTO));

            return render(model, "game.html");
        });

        post("/resume", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String[] sourcePosition = req.queryParams("source").split(",");
            String[] targetPosition = req.queryParams("target").split(",");

            ChessBoard chessBoard = chessBoardService.selectRecentGame();
            chessBoardService.move(chessBoard, sourcePosition, targetPosition);
            chessBoardService.update(chessBoard, chessBoard.getTeam());
            ChessBoard chessBoardAfterMove = chessBoardService.selectRecentGame();

            if (chessBoard.checkKing()) {
                model.put("team", chessBoard.getAliveKingTeam());
                return render(model, "gameover.html");
            }

            ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
            chessBoardDTO.setUnits(chessBoardAfterMove.getUnits());

            model.put("chessBoard", new Gson().toJson(chessBoardDTO));
            model.put("team", chessBoardAfterMove.getTeam().name());

            return render(model, "game.html");

        });

        post("/score", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("score", chessBoardService.selectRecentGame().sumScore().entrySet());

            return render(model, "score.html");
        });

        exception();
    }

    private static void exception() {
        Spark.exception(Exception.class, WebUIChessApplication::exceptionMsg);
    }

    private static void exceptionMsg(Exception exception, Request request, Response response) {
        response.body(
                "<script>" +
                    "alert('" + exception.getMessage() + "');" +
                    "history.back();" +
                "</script>"
        );
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
