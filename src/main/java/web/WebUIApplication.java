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
import database.ChessGameDao;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Map;
import java.util.Objects;

import static spark.Spark.*;

public class WebUIApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");

        ChessGame chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
        Commands commands = Commands.initCommands(chessGame);
        ChessGameDao chessGameDao = new ChessGameDao();

        get("/start", (req, res) -> {
            chessGame.reset(new Board((InitBoardGenerator.initLines())));
            Command command = commands.matchedCommand("start");
            command.execution("start");
            JSONObject jsonObject = new JSONObject();
            Map<Position, Piece> board = chessGame.board();

            for (Position position : board.keySet()) {
                jsonObject.put(position.toString(), board.get(position).getSymbol());
            }
            jsonObject.put("message", chessGame.state().toString());

            return jsonObject.toJSONString();
        });

        post("/select", "application/json", (req, res) -> {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(req.body());
            Position position = Position.of((String) jsonObj.get("position"));
            JSONObject jsonObject = new JSONObject();

            for (Position possiblePosition : chessGame.movablePath(position)) {
                jsonObject.put(possiblePosition.toString(), "possible");
            }

            return jsonObject.toJSONString();
        });

        post("/move", "application/json", (req, res) -> {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(req.body());
            Position source = Position.of((String) jsonObj.get("source"));
            Position target = Position.of((String) jsonObj.get("target"));
            chessGame.move(source, target);

            JSONObject jsonObject = new JSONObject();
            Map<Position, Piece> board = chessGame.board();
            for (Position position : board.keySet()) {
                jsonObject.put(position.toString(), board.get(position).getSymbol());
            }

            State gameState = chessGame.state();
            jsonObject.put("message", gameState.toString());

            if (gameState instanceof WhiteWin || gameState instanceof BlackWin) {
                Command command = commands.matchedCommand("status");
                Status status = (Status) command;
                jsonObject.put("whiteScore", status.totalWhiteScore());
                jsonObject.put("blackScore", status.totalBlackScore());
            }

            return jsonObject.toJSONString();
        });

        get("/save", (req, res) -> {
            chessGameDao.addChessGame(chessGame);
            return "success";
        });

        get("/load", (req, res) -> {
            ChessGame loadChessGame = chessGameDao.findByGameId("1");
            if (Objects.isNull(loadChessGame)) {
                return "{\"message\":\"no data\"}";
            }
            chessGame.load(loadChessGame);

            JSONObject jsonObject = new JSONObject();
            Map<Position, Piece> board = chessGame.board();
            for (Position position : board.keySet()) {
                jsonObject.put(position.toString(), board.get(position).getSymbol());
            }

            State gameState = chessGame.state();
            jsonObject.put("message", gameState.toString());
            return jsonObject.toJSONString();
        });
    }
}
