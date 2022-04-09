package chess;

import chess.domain.piece.property.Team;
import chess.utils.JsonConvertor;
import chess.dao.ChessGame;
import chess.dto.ChessGameRoomInfoDTO;
import chess.service.ChessService;
import chess.utils.Render;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.Map;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public final class WebApplication {

    private static final ChessService CHESS_SERVICE = new ChessService();

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("games", CHESS_SERVICE.getGames());
            return Render.renderHtml(model, "/lobby.html");
        });

        post("/chess/new", (req, res) -> {
            String gameId = CHESS_SERVICE.addChessGame(req.queryParams("gameName"));
            res.redirect("/chess/game/" + gameId);
            return res;
        });

        get("/chess/game/:id", (req, res) -> {
            ChessGameRoomInfoDTO ChessGameRoomInfoDTO = CHESS_SERVICE.findGameById(req.params(":id"));
            return Render.renderGame(ChessGameRoomInfoDTO);
        });

        get("/chess/game/:id/board", (req, res) -> {
            ChessGame chessGame = CHESS_SERVICE.getChessGamePlayed(req.params(":id"));
            Map<String, Object> model = Render.renderBoard(chessGame);
            return JsonConvertor.toJson(model);
        });

        post("/chess/game/:id/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            Team team = Team.valueOf(req.queryParams("team"));
            ChessGame chessGame = CHESS_SERVICE.movePiece(req.params(":id"), source, target, team);
            final Map<String, Object> model = Render.renderBoard(chessGame);

            if (chessGame.isGameSet()) {
                Map<String, Object> result = CHESS_SERVICE.getResult(chessGame);
                model.putAll(result);
            }
            return JsonConvertor.toJson(model);
        });

        exception(Exception.class, (e, request, response) -> {
            response.status(400);
            response.body(e.getMessage());
        });

        exception(SQLException.class, (e, request, response) -> {
            response.status(400);
            response.body("SQL 에러");
        });
    }
}
