package web;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.board.position.Position;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.command.Status;
import chess.domain.game.ChessGame;
import chess.domain.game.state.BlackWin;
import chess.domain.game.state.State;
import chess.domain.game.state.WhiteWin;
import chess.domain.piece.Piece;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.service.ChessGameService;
import web.service.RoomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");

        ChessGame chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
        Commands commands = Commands.initCommands(chessGame);
        ChessGameService chessGameService = new ChessGameService();
        RoomService roomService = new RoomService();
        JSONParser jsonParser = new JSONParser();

        post("/start", "application/json", (req, res) -> {
            JSONObject jsonObj = (JSONObject) jsonParser.parse(req.body());
            int roomId = Integer.parseInt((String) jsonObj.get("roomId"));
            ChessGame loadChessGame = chessGameService.chessGameByRoomId(roomId);
            chessGame.load(loadChessGame);

            JSONObject jsonObject = new JSONObject();
            Map<Position, Piece> board = chessGame.board();
            for (Position position : board.keySet()) {
                jsonObject.put(position.toString(), board.get(position).getSymbol());
            }
            jsonObject.put("message", chessGame.state().toString());

            return jsonObject.toJSONString();
        });

        post("/select", "application/json", (req, res) -> {
            JSONObject jsonObj = (JSONObject) jsonParser.parse(req.body());
            Position position = Position.of((String) jsonObj.get("position"));
            JSONObject jsonObject = new JSONObject();

            for (Position possiblePosition : chessGame.movablePath(position)) {
                jsonObject.put(possiblePosition.toString(), "possible");
            }

            return jsonObject.toJSONString();
        });

        post("/move", "application/json", (req, res) -> {
            JSONObject jsonObj = (JSONObject) jsonParser.parse(req.body());
            int roomId = Integer.parseInt((String) jsonObj.get("roomId"));
            String webCommand = (String) jsonObj.get("command");
            Command command = commands.matchedCommand(webCommand);
            command.execution(webCommand);
            chessGameService.addCommand(webCommand, roomId);

            JSONObject jsonObject = new JSONObject();
            Map<Position, Piece> board = chessGame.board();
            for (Position position : board.keySet()) {
                jsonObject.put(position.toString(), board.get(position).getSymbol());
            }

            State gameState = chessGame.state();
            jsonObject.put("message", gameState.toString());

            if (gameState instanceof WhiteWin || gameState instanceof BlackWin) {
                command = commands.matchedCommand("status");
                Status status = (Status) command;
                jsonObject.put("whiteScore", status.totalWhiteScore());
                jsonObject.put("blackScore", status.totalBlackScore());
            }

            return jsonObject.toJSONString();
        });

        get("/rooms", (req, res) -> {
            List<Integer> roomIds = roomService.roomIdS();
            if (roomIds.isEmpty()) {
                throw new IllegalStateException();
            }

            JSONObject jsonObject = new JSONObject();
            for (Integer roomId : roomIds) {
                jsonObject.put("room" + roomId, roomId);
            }

            return jsonObject.toJSONString();
        });

        get("/chessRoom", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("roomId", req.queryParams("roomId"));
            return render(model, "chess.html");
        });

        get("/newRoomId", (req, res) -> {
            int roomId = roomService.newRoomId();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("roomId", roomId);
            return jsonObject.toJSONString();
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
