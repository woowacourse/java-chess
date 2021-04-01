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

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/chess/lobby", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGameDAO chessGameDAO = new ChessGameDAO();

            model.put("games", chessGameDAO.findActiveGames());

            return renderHtml(model, "/lobby.html");
        });

        post("/chess/new", (req, res) -> {
            ChessGame chessGame = ChessGame.initChessGame();
            chessGame.setName(req.queryParams("gameName"));

            ChessGameDAO chessGameDAO = new ChessGameDAO();
            String gameId = chessGameDAO.addGame(chessGame);

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

        MoveCommandDAO moveCommandDAO = new MoveCommandDAO();
        List<Command> commandsByGameId = moveCommandDAO.findCommandsByGameId(gameId);

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

            BoardDto boardDto = new BoardDto(chessGame);

            if (chessGame.isGameSet()) {
                Map<String, Object> model = boardDto.getResult();
                model.put("winner", chessGame.winner().toString());
                Score score = chessGame.score();
                model.put("blackScore", score.blackScore());
                model.put("whiteScore", score.whiteScore());
                return model;
            }

            Move move = (Move) command;
            move.setGameId(chessGame.getId());
            MoveCommandDAO moveCommandDAO = new MoveCommandDAO();
            moveCommandDAO.addMoveCommand(move);

            return boardDto.getResult();
        } catch (ChessException e) {
            Map<String, Object> model = new BoardDto(chessGame).getResult();
            model.put("error", String.format("<script>alert(\"%s\")</script>", e));
            return model;
        }
    }
}
