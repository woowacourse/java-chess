package chess;

import static chess.web.view.RenderView.renderHtml;
import static spark.Spark.exception;
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
import chess.web.dto.ChessGameDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;

public class WebUIChessApplication {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
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
            Map<String, Object> model = new HashMap<>();
            ChessGameDTO chessGameDTO = CHESS_GAME_DAO.findGameById(req.params(":id"));

            model.put("gameId", chessGameDTO.getId());
            model.put("gameName", chessGameDTO.getName());
            return renderHtml(model, "/game.html");
        });

        get("/chess/game/:id/board", (req, res) -> {
            ChessGame chessGame = replayedChessGame(req);
            String boardHtml = renderHtml(new BoardDto(chessGame).getResult(), "/board.html");

            Map<Object, Object> model = new HashMap<>();
            model.put("board", boardHtml);
            model.put("currentTurn", chessGame.currentTurn());
            return GSON.toJson(model);
        });

        post("/chess/game/:id/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            ChessGame chessGame = replayedChessGame(req);
            String boardHtml = renderHtml(movePiece(chessGame, source, target), "/board.html");

            Map<Object, Object> model = new HashMap<>();
            model.put("board", boardHtml);
            model.put("currentTurn", chessGame.currentTurn());

            if (chessGame.isGameSet()) {
                CHESS_GAME_DAO.updateGameEnd(chessGame.getId());
                model.put("isGameSet", Boolean.TRUE);

                model.put("winner", chessGame.winner().toString());
                Score score = chessGame.score();
                model.put("blackScore", score.blackScore());
                model.put("whiteScore", score.whiteScore());
            }

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

    private static ChessGame replayedChessGame(Request req) throws SQLException {
        String gameId = req.params(":id");

        List<Command> commands = MOVE_COMMAND_DAO.findCommandsByGameId(gameId);

        ChessGame chessGame = ChessGame.initChessGame();
        for (Command command : commands) {
            chessGame.execute(command);
        }
        chessGame.setId(gameId);
        return chessGame;
    }

    private static Map<String, Object> movePiece(ChessGame chessGame, String source,
            String target) throws SQLException {
        Move moveCommand = new Move(source, target);
        chessGame.execute(moveCommand);
        saveMoveCommand(chessGame, moveCommand);

        return new BoardDto(chessGame).getResult();
    }

    private static void saveMoveCommand(ChessGame chessGame, Move command) throws SQLException {
        command.setGameId(chessGame.getId());
        MOVE_COMMAND_DAO.addMoveCommand(command);
    }
}
