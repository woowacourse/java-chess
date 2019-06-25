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
    private static final String SESSION_ID = "-game";
    private static final String FROM = "from";
    private static final String TO = "to";
    private static final ChessGameDAO CHESS_GAME_DAO = ChessGameDAO.getInstance();
    private static final ChessLogDAO CHESS_LOG_DAO = ChessLogDAO.getInstance();

    public static Map<String, String> findGameByGameId(Request request) {
        String gameId = gameId(request);
        ChessGame chessGame = new ChessGame();
        lastStatus(CHESS_LOG_DAO.findGameLogById(gameId), chessGame);
        request.session().attribute(gameId + SESSION_ID, chessGame);
        return status(chessGame);
    }

    private static String gameId(Request request) {
        return request.splat()[0];
    }

    private static void lastStatus(List<List<String>> chessLogs, ChessGame chessGame) {
        for (List<String> chessLog : chessLogs) {
            chessGame.play(chessLog.get(0), chessLog.get(1));
        }
    }

    public static List<Integer> findPreviousGamesById(String name) {
        return CHESS_GAME_DAO.findPreviousGamesById(name);
    }

    public static Map<String, String> playGame(Request req) {
        String gameId = gameId(req);
        ChessGame game = req.session().attribute(gameId + SESSION_ID);
        String from = req.queryParams(FROM);
        String to = req.queryParams(TO);

        game.play(from, to);
        CHESS_LOG_DAO.insertLog(from, to, gameId);
        req.session().attribute(gameId + SESSION_ID, game);
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
