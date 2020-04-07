package chess;

import chess.controller.dao.ChessBoardDao;
import chess.controller.dao.GameDao;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.domain.game.*;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Gson gson = new Gson();
    private static final ChessBoardDao chessBoardDao = new ChessBoardDao();
    private static final GameDao gameDao = new GameDao();

    public static void main(String[] args) {
        port(8081);
        staticFiles.location("public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });

        get("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessBoardDao.deleteChessBoard(gameDao.findMaxRoomNumber());
            gameDao.updateEndState(gameDao.findMaxRoomNumber());
            return render(model, "main.html");
        });

        get("/loadGame", (req, res) -> {
            try {
                int roomNumber = gameDao.findMaxRoomNumber();
                ResponseDto responseDto = null;
                if (gameDao.findState(roomNumber) == GameStatus.FINISH) {
                    ChessBoard chessBoard = new ChessBoard(PieceFactory.create());
                    ChessGame chessGame = new ChessGame(chessBoard, Player.WHITE, GameStatus.NOT_STARTED);
                    responseDto = chessGame.start(new RequestDto(Command.START));
                    gameDao.saveInitGame(responseDto);
                    roomNumber = gameDao.findMaxRoomNumber();
                    chessBoardDao.saveChessBoard(responseDto.getChessBoardDto(), roomNumber);
                    res.status(200);
                } else if (gameDao.findState(roomNumber) == GameStatus.RUNNING) {
                    ChessBoard chessBoard = new ChessBoard(chessBoardDao.findPlayingChessBoard(roomNumber));
                    ChessGame chessGame = new ChessGame(chessBoard, gameDao.findTurn(roomNumber), GameStatus.RUNNING);

                    responseDto = chessGame.load(chessBoard);
                }

                return gson.toJson(responseDto);
            } catch (IllegalArgumentException | IllegalStateException e) {
                res.status(400);
                return gson.toJson(e.getMessage());
            }
        });

        get("/move", (req, res) -> {
            try {
                int roomNumber = gameDao.findMaxRoomNumber();
                ChessBoard chessBoard = new ChessBoard(chessBoardDao.findPlayingChessBoard(roomNumber));
                ChessGame chessGame = new ChessGame(chessBoard, gameDao.findTurn(roomNumber), GameStatus.RUNNING);

                RequestDto requestDto = new RequestDto(Command.MOVE, req);
                ResponseDto responseDto = chessGame.move(requestDto);

                responseDto.setRoomNumber(roomNumber);

                gameDao.updateGame(responseDto);
                chessBoardDao.deleteChessBoard(roomNumber);
                chessBoardDao.saveChessBoard(responseDto.getChessBoardDto(), roomNumber);

                if (chessBoard.isGameOver()) {
                    responseDto.dieKing();
                    chessBoardDao.deleteChessBoard(roomNumber);
                    gameDao.updateEndState(roomNumber);
                }

                res.status(200);
                return gson.toJson(responseDto);
            } catch (IllegalArgumentException | IllegalStateException e) {
                res.status(400);
                return gson.toJson(e.getMessage());
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
