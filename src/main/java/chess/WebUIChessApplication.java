package chess;

import chess.domain.*;
import chess.domain.ChessInitialPosition;
import chess.domain.piece.Piece;
import chess.exception.GameOverException;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.*;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "/index.html");
        });

        get("/new", (req, res) -> {
            ChessGame chessGame = loadInitialChessGame();
            int roomNumber = ChessGameService.saveInitialChessGame(chessGame);
            return render(makeBaseChessGameModel(chessGame, roomNumber), "/chess.html");
        });

        get("/select", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("roomNumbers", ChessGameService.getRoomNumbers());
            return render(model, "/select_game.html");
        });

        post("/load", (req, res) -> {
            int roomNumber = Integer.parseInt(req.queryParams("room-number"));
            ChessGame chessGame = loadChessGame(roomNumber);
            return render(makeBaseChessGameModel(chessGame, roomNumber), "/chess.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int roomNumber = Integer.parseInt(req.queryParams("room-number"));
            ChessGame chessGame = loadChessGame(roomNumber);

            int startX = Integer.parseInt(req.queryParams("start-x"));
            int startY = Integer.parseInt(req.queryParams("start-y"));
            int endX = Integer.parseInt(req.queryParams("end-x"));
            int endY = Integer.parseInt(req.queryParams("end-y"));
            try {
                chessGame.move(Position.getPosition(startX, startY), Position.getPosition(endX, endY));
            } catch (GameOverException e) {
                ChessGameService.gameOver(roomNumber);
                model.put("message", e.getMessage());
                return render(model, "/gameover.html");
            }

            ChessGameService.saveChessGame(roomNumber, chessGame);

            model.putAll(makeBaseChessGameModel(chessGame, roomNumber));
            return render(model, "/chess.html");
        });

        post("/status", (req, res) -> {
            int roomNumber = Integer.parseInt(req.queryParams("room-number"));
            ChessGame chessGame = loadChessGame(roomNumber);

            Map<String, Object> model = makeBaseChessGameModel(chessGame, roomNumber);
            model.putAll(makeStatusModel(chessGame));
            return render(model, "/chess.html");
        });

        exception(Exception.class, (exception, request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", exception.getMessage());
            response.body(render(model, "error.html"));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static ChessGame loadInitialChessGame() {
        ChessBoard initialChessBoard = ChessInitialPosition.generateChessBoard();
        return new ChessGame(initialChessBoard);
    }

    private static ChessGame loadChessGame(int roomNumber) throws SQLException {
        Player turn = ChessGameService.loadTurn(roomNumber);
        List<Piece> pieces = ChessGameService.loadChessPieces(roomNumber);
        ChessBoard chessBoard = new ChessBoard(pieces);
        return new ChessGame(turn, chessBoard);
    }

    private static Map<String, Object> makeBaseChessGameModel(ChessGame chessGame, int roomNumber) {
        Map<String, Object> model = new HashMap<>();
        model.put("turn", chessGame.getCurrentPlayer().name());
        model.put("roomNumber", roomNumber);
        model.put("board", chessGame.getPieceImages());
        return model;
    }

    private static Map<String, Object> makeStatusModel(ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();
        model.put("winner", "승자 : " + chessGame.findWinner().name());
        model.put("whiteScore", "백 점수 : " + chessGame.getPlayerScore(Player.WHITE).getScore());
        model.put("blackScore", "흑 점수 : " + chessGame.getPlayerScore(Player.BLACK).getScore());
        return model;
    }
}
