package chess.service;

import chess.dao.ChessGameDAO;
import chess.dao.ChessLogDAO;
import chess.domain.ChessGame;
import chess.domain.ChessResult;
import spark.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {
    private static final ChessGameDAO CHESS_GAME_DAO = ChessGameDAO.getInstance();
    private static final ChessLogDAO CHESS_LOG_DAO = ChessLogDAO.getInstance();

    public static ChessGame findGameByGameId(String gameId) {
        List<List<String>> chessLog = CHESS_LOG_DAO.findGameLogById(gameId);
        ChessGame chessGame = new ChessGame();
        for (List<String> move : chessLog) {
            chessGame.play("move " + move.get(0) + " " + move.get(1));
        }
        return chessGame;
    }

    public static List<Integer> findPreviousGamesById(String name) {
        return CHESS_GAME_DAO.findPreviousGamesById(name);
    }

    public static Map<String, String> playGame(Request req) {
        String gameId = req.splat()[0];
        ChessGame game = req.session().attribute(gameId + "-game");
        String from = req.queryParams("from");
        String to = req.queryParams("to");
        String move = "move " + from + " " + to;

        game.play(move);
        CHESS_LOG_DAO.insertLog(from, to, gameId);
        req.session().attribute(gameId + "-game", game);
        return status(game);
    }

    private static Map<String, String> status(ChessGame game) {
        Map<String, String> status = new HashMap<>(game.status());
        if (game.checkEndGame()) {
            status.putAll(resultStatus(game));
        }
        return status;

    }

    private static Map<String, String> resultStatus(ChessGame game) {
        ChessResult result = game.winner();
        return result.status();
    }
}
