package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;
import chess.domain.game.BlackTurn;
import chess.domain.game.ChessGame;
import chess.domain.game.ScoreCalculator;
import chess.domain.game.State;
import chess.domain.game.WhiteTurn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private ChessGame chessGame;
    private GameDao gameDao = new GameDao();
    private BoardDao boardDao = new BoardDao();

    public void run() {
        port(9090);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "lobby.html");
        });

        get("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame = new ChessGame();
            return render(model, "game.html");
        });

        post("/load", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String id = req.queryParams("id");

            Map<Coordinate, Piece> board = init(boardDao.findByGameId(id));
            State state = createState(board, gameDao.findState(id));

            chessGame = new ChessGame(state);
            model.put("id", id);
            model.put("pieces", chessGame.getPieces());
            return render(model, "game.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.start();
            int id = gameDao.save(chessGame);
            boardDao.save(chessGame.getValue(), id);
            model.put("id", id);
            model.put("pieces", chessGame.getPieces());
            return render(model, "game.html");
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.end();
            gameDao.delete(req.queryParams("id"));

            ScoreCalculator scoreCalculator = new ScoreCalculator(chessGame.getValue());
            Map<Team, Double> status = scoreCalculator.createStatus();

            model.put("blackScore", status.get(Team.BLACK));
            model.put("whiteScore", status.get(Team.WHITE));
            model.put("winTeam", chessGame.getWinTeam(status));
            return render(model, "game.html");
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ScoreCalculator scoreCalculator = new ScoreCalculator(chessGame.getValue());
            Map<Team, Double> status = scoreCalculator.createStatus();
            model.put("blackScore", status.get(Team.BLACK));
            model.put("whiteScore", status.get(Team.WHITE));
            model.put("pieces", chessGame.getPieces());
            return render(model, "game.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String id = req.queryParams("id");
            String from = req.queryParams("from");
            String to = req.queryParams("to");

            chessGame.move(from, to);
            boardDao.updatePosition(id, from, chessGame.getValue().get(Coordinate.of(from)));
            boardDao.updatePosition(id, to, chessGame.getValue().get(Coordinate.of(to)));
            gameDao.updateById(req.queryParams("id"), chessGame.getState().getState());

            model.put("id", req.queryParams("id"));
            model.put("pieces", chessGame.getPieces());
            return render(model, "game.html");
        });
    }

    private State createState(Map<Coordinate, Piece> board, String state) {
        if (Objects.equals(state, "WhiteTurn")) {
            return new WhiteTurn(new Board(board));
        }
        return new BlackTurn(new Board(board));
    }

    public Map<Coordinate, Piece> init(Map<Coordinate, Piece> board) {
        Map<Coordinate, Piece> map = new LinkedHashMap<>();
        for (Row row : Row.values()) {
            initPiece(map, row, board);
        }
        return map;
    }

    private void initPiece(Map<Coordinate, Piece> map, Row row, Map<Coordinate, Piece> board) {
        for (Column column : Column.values()) {
            map.put(Coordinate.of(column, row), board.get(Coordinate.of(column, row)));
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
