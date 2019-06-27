package chess.controller;

import chess.model.ChessGame;
import chess.model.ScoreResult;
import chess.model.dto.BoardDTO;
import chess.service.GameService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.WebUIChessApplication.render;

public class GameController {
    private static final int START_TURN = 1;
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public Object start(Request req, Response res) throws SQLException {
        gameService.deleteAll();
        gameService.insertInit();

        int turn = START_TURN;
        ChessGame game = gameService.initGame(turn);

        req.session().attribute("game", game);
        res.redirect("/show?turn=" + turn);
        return null;
    }

    public Object show(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();

        ChessGame game = req.session().attribute("game");
        int turn = Integer.parseInt(req.queryParams("turn"));
        ScoreResult scoreResult = game.calculateScore();
        List<String> board = gameService.findByTurn(turn).getPieces();

        model.put("board", board);
        model.put("score", scoreResult);
        model.put("currentTeam", game.getCurrentTeam());
        return render(model, "game.html");
    }


    public Object play(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        String source = req.queryParams("source");
        String target = req.queryParams("target");
        ChessGame game = req.session().attribute("game");

        gameService.movePiece(game, source, target);
        BoardDTO boardDTO = gameService.produceBoardDTO(game);
        gameService.updateBoard(boardDTO);
        model.put("winner", game.getCurrentTeam());

        if (gameService.checkKingDead(game)) {
            gameService.deleteAll();
            return render(model, "end.html");
        }
        ScoreResult scoreResult = game.calculateScore();

        model.put("currentTeam", game.getCurrentTeam());
        model.put("board", boardDTO.getPieces());
        model.put("score", scoreResult);

        return render(model, "game.html");
    }

    public Object continueGame(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();

        int latestTurn = gameService.getLatestTurn();
        BoardDTO boardDTO = gameService.findByTurn(latestTurn);
        ChessGame game = gameService.continueGame(boardDTO, latestTurn);
        ScoreResult scoreResult = game.calculateScore();

        model.put("score", scoreResult);
        model.put("currentTeam", game.getCurrentTeam());
        model.put("board", boardDTO.getPieces());

        res.redirect("show?turn=" + latestTurn);
        return null;
    }
}
