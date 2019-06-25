package chess.controller;

import chess.domain.chess.ChessBoard;
import chess.domain.chess.Team;
import chess.domain.chess.dao.ChessBoardDAO;
import chess.domain.chess.dto.ChessBoardDTO;
import chess.domain.chess.initializer.ChessBoardInitializer;
import chess.domain.geometric.Position;
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
    private static Connection dbConnection = DBConnection.getConnection();
    private static ChessBoardDAO chessBoardDAO = new ChessBoardDAO(dbConnection);

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
            ChessBoard chessBoard = new ChessBoard(new ChessBoardInitializer());
            chessBoardDTO.setUnits(chessBoard.getUnits());

            chessBoardDAO.add(chessBoard, Team.WHITE);

            String chessJson = new Gson().toJson(chessBoardDTO);

            model.put("chessBoard", chessJson);

            return render(model, "game.html");
        });

        get("/resume", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessBoardDTO chessBoardDTO = new ChessBoardDTO();

            ChessBoard chessBoard = chessBoardDAO.selectRecentRow();
            chessBoardDTO.setUnits(chessBoard.getUnits());

            String chessJson = new Gson().toJson(chessBoardDTO);
            model.put("team", chessBoard.getTeam().name());

            model.put("chessBoard", chessJson);
            return render(model, "game.html");
        });

        post("/resume", (req, res) -> {
            String[] source = req.queryParams("source").split(",");
            String[] target = req.queryParams("target").split(",");

            Position sourcePosition = Position.create(Integer.parseInt(source[0]), Integer.parseInt(source[1]));
            Position targetPosition = Position.create(Integer.parseInt(target[0]), Integer.parseInt(target[1]));

            ChessBoard chessBoard = chessBoardDAO.selectRecentRow();
            chessBoard.move(sourcePosition, targetPosition);

            chessBoardDAO.update(chessBoard, chessBoard.getTeam().opposite());

            Map<String, Object> model = new HashMap<>();

            ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
            chessBoardDTO.setUnits(chessBoard.getUnits());

            String chessJson = new Gson().toJson(chessBoardDTO);

            model.put("chessBoard", chessJson);
            model.put("team", chessBoard.getTeam().name());
            return render(model, "game.html");

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
