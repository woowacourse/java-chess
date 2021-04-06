package chess;

import static chess.web.view.RenderView.renderHtml;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.command.Command;
import chess.domain.command.Move;
import chess.domain.command.MoveCommandDAO;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameDAO;
import chess.domain.game.Score;
import chess.exception.ChessException;
import chess.web.dto.BoardDto;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;

public class WebUIChessApplication {

    private static final ChessGameDAO CHESS_GAME_DAO = new ChessGameDAO();
    private static final MoveCommandDAO MOVE_COMMAND_DAO = new MoveCommandDAO();

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("games", CHESS_GAME_DAO.findActiveGames());

            return renderHtml(model, "/lobby.html");
        });

        post("/chess/new", (req, res) -> {
            ChessGame chessGame = ChessGame.initChessGame();
            chessGame.setName(req.queryParams("gameName"));

            String gameId = CHESS_GAME_DAO.addGame(chessGame);

            res.redirect("/chess/game/" + gameId);
            return res;
        });

        get("/chess/game/:id", (req, res) -> {
            ChessGame chessGame = getChessGame(req);

            Map<String, Object> model = new BoardDto(chessGame).getResult();
            return renderHtml(model, "/index.html");
        });

        get("/chess/game/:id/move", (req, res) -> {
            String target = req.queryParams("target");
            String source = req.queryParams("source");

            Map<String, Object> model = movePiece(getChessGame(req), target, source);

            return renderHtml(model, "/index.html");
        });
    }

    private static ChessGame getChessGame(Request req) throws SQLException {
        String gameId = req.params(":id");

        List<Command> commandsByGameId = MOVE_COMMAND_DAO.findCommandsByGameId(gameId);

        ChessGame chessGame = ChessGame.initChessGame();
        for (Command command : commandsByGameId) {
            chessGame.execute(command);
        }
        chessGame.setId(gameId);
        return chessGame;
    }

    private static Map<String, Object> movePiece(ChessGame chessGame, String target,
            String source) throws SQLException {
        try {
            Command command = Command.from("move " + target + " " + source);
            chessGame.execute(command);
            saveMoveCommand(chessGame, (Move) command);

            return chessModelFromGame(chessGame);
        } catch (ChessException e) {
            return chessModelWithException(chessGame, e);
        }
    }

    private static void saveMoveCommand(ChessGame chessGame, Move command) throws SQLException {
        command.setGameId(chessGame.getId());
        MOVE_COMMAND_DAO.addMoveCommand(command);
    }

    private static Map<String, Object> chessModelFromGame(ChessGame chessGame) {
        Map<String, Object> model = new BoardDto(chessGame).getResult();

        if (chessGame.isGameSet()) {
            model.put("winner", chessGame.winner().toString());
            Score score = chessGame.score();
            model.put("blackScore", score.blackScore());
            model.put("whiteScore", score.whiteScore());
        }

        return model;
    }

    private static Map<String, Object> chessModelWithException(ChessGame chessGame,
            ChessException e) {
        Map<String, Object> model = new BoardDto(chessGame).getResult();
        model.put("error", String.format("<script>alert(\"%s\")</script>", e));
        return model;
    }
}
