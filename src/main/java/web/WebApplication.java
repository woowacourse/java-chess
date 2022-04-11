package web;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.dao.ChessGameDao;
import web.dao.ChessGameDaoImpl;
import web.dto.ChessGameResponse;
import web.dto.MoveRequest;
import web.service.ChessService;

public class WebApplication {

    private static final JsonTransformer JSON_TRANSFORMER = new JsonTransformer();
    private static final ChessGameDao CHESS_GAME_DAO = new ChessGameDaoImpl();
    private static final ChessService CHESS_SERVICE = new ChessService();

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("games", CHESS_GAME_DAO.findRunningGames());
            return render(model, "/lobby.html");
        });

        post("/game", (req, res) -> {
            Long gameId = CHESS_SERVICE.createChessGame(req.queryParams("gameName"));
            res.redirect("/game/" + gameId);
            return res;
        });

        get("/game/:id", (req, res) -> {
            ChessGameResponse chessGameResponse = CHESS_GAME_DAO.findByGameId(req.params(":id"));
            Map<String, Object> model = new HashMap<>();
            model.put("gameId", chessGameResponse.getId());
            model.put("gameName", chessGameResponse.getName());
            return render(model, "/board.html");
        });

        get("/game/:id/board", (req, res) -> {
            ChessGame chessGame = CHESS_SERVICE.loadChessGame(req.params(":id"));
            return loadChessBoard(chessGame);
        }, JSON_TRANSFORMER);

        put("game/:id/move", (req, res) -> {
            MoveRequest moveReqeust = JSON_TRANSFORMER.getGson()
                .fromJson(req.body(), MoveRequest.class);
            return CHESS_SERVICE.move(moveReqeust);
        }, JSON_TRANSFORMER);

        get("game/:id/score", (req, res) -> CHESS_SERVICE.score(req.params(":id")),
            JSON_TRANSFORMER);

        get("game/:id/turn", (req, res) -> CHESS_SERVICE.turn(req.params(":id")), JSON_TRANSFORMER);
    }

    private static Map<String, String> loadChessBoard(ChessGame chessGame) {
        ChessBoard chessBoard = chessGame.chessBoard();
        Map<Position, Piece> board = chessBoard.getBoard();

        Map<String, String> model = new HashMap<>();
        for (Position position : board.keySet()) {
            String positionName = position.toString();
            String pieceName = Symbol.makePieceName(position, board);
            model.put(positionName, pieceName);
        }
        return model;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
