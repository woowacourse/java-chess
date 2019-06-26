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
import java.util.List;
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

        get("/start_game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
            ChessBoard chessBoard = new ChessBoard(new ChessBoardInitializer());
            chessBoardDTO.setUnits(chessBoard.getUnits());

            chessBoardDAO.add(chessBoard, Team.WHITE);

            String chessJson = new Gson().toJson(chessBoardDTO);

            model.put("team", chessBoard.getTeam().name());
            model.put("chessBoard", chessJson);

            return render(model, "game.html");
        });

        get("/game", (req, res) -> {
            int roomId = Integer.parseInt(req.queryParams("roomId"));

            Map<String, Object> model = new HashMap<>();
            ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
            ChessBoard chessBoard = chessBoardDAO.select(roomId);

            if (checkKing(model, chessBoard)) return render(model, "gameover.html");

            chessBoardDTO.setUnits(chessBoard.getUnits());

            String chessJson = new Gson().toJson(chessBoardDTO);

            model.put("roomId", roomId);
            model.put("team", chessBoard.getTeam().name());
            model.put("chessBoard", chessJson);
            return render(model, "game.html");
        });

        post("/game", (req, res) -> {
            String roomIdstring = req.queryParams("roomId");
            int roomId = Integer.parseInt(req.queryParams("roomId"));

            Map<String, Object> model = new HashMap<>();

            String[] source = req.queryParams("source").split(",");
            String[] target = req.queryParams("target").split(",");

            Position sourcePosition = Position.create(Integer.parseInt(source[0]), Integer.parseInt(source[1]));
            Position targetPosition = Position.create(Integer.parseInt(target[0]), Integer.parseInt(target[1]));

            ChessBoard chessBoard = chessBoardDAO.select(roomId);

            chessBoard.move(sourcePosition, targetPosition);
            chessBoard.changeTeam();

            chessBoardDAO.update(chessBoard, chessBoard.getTeam(), roomId);

            ChessBoard chessBoardAfterUpdate = chessBoardDAO.select(roomId);

            if (checkKing(model, chessBoard)) return render(model, "gameover.html");

            ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
            chessBoardDTO.setUnits(chessBoardAfterUpdate.getUnits());

            String chessJson = new Gson().toJson(chessBoardDTO);


            model.put("roomId", roomId);
            model.put("chessBoard", chessJson);
            model.put("team", chessBoardAfterUpdate.getTeam().name());
            return render(model, "game.html");

        });

        get("/game_room", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Integer> roomIds = chessBoardDAO.getIdList();
            String roomIdsJson = new Gson().toJson(roomIds);

            model.put("roomIds", roomIds);
            return render(model, "game_room.html");
        });

        exception();
    }

    private static boolean checkKing(Map<String, Object> model, ChessBoard chessBoard) {
        if (chessBoard.numberOfKing() != 2) {
            model.put("team", chessBoard.getAliveKingTeam());
            return true;
        }
        return false;
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
