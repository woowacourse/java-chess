package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameDAO;
import chess.domain.game.Side;
import chess.exception.ChessException;
import chess.web.dto.ChessGameDTO;
import chess.web.service.ChessService;
import chess.web.view.RenderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class WebUIChessApplication {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final ChessService CHESS_SERVICE = new ChessService();
    private static final ChessGameDAO CHESS_GAME_DAO = new ChessGameDAO();

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("games", CHESS_GAME_DAO.findActiveGames());
            return RenderView.renderHtml(model, "/lobby.html");
        });

        post("/chess/new", (req, res) -> {
            String gameId = CHESS_SERVICE.addChessGame(req.queryParams("gameName"));
            res.redirect("/chess/game/" + gameId);
            return res;
        });

        get("/chess/game/:id", (req, res) -> {
            ChessGameDTO chessGameDTO = CHESS_GAME_DAO.findGameById(req.params(":id"));
            return RenderView.renderGame(chessGameDTO);
        });

        get("/chess/game/:id/board", (req, res) -> {
            ChessGame chessGame = CHESS_SERVICE.replayedChessGame(req.params(":id"));
            Map<String, Object> model = RenderView.renderBoard(chessGame);
            return GSON.toJson(model);
        });

        post("/chess/game/:id/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            Side playerSide = Side.valueOf(req.queryParams("turn"));

            Map<String, Object> model = CHESS_SERVICE.movePiece(req.params(":id"), source, target, playerSide);
            return GSON.toJson(model);
        });

        exception(ChessException.class, (e, request, response) -> {
            response.status(400);
            response.body(e.getMessage());
        });

        exception(SQLException.class, (e, request, response) -> {
            response.status(400);
            response.body("에러 발생");
        });
    }
}
