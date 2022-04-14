package chess.web.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Player;
import chess.domain.game.state.RunningGame;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.web.dao.ChessBoardDao;
import chess.web.dao.PlayerDao;
import chess.web.service.ChessGameService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ChessGameController {

    public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
    public static final String WINNER_MESSAGE = "WINNER_MESSAGE";

    private final ChessBoardDao chessBoardDao;
    private final PlayerDao playerDao;
    private final ChessGameService service;

    public ChessGameController(ChessBoardDao chessBoardDao, PlayerDao playerDao, ChessGameService service) {
        this.chessBoardDao = chessBoardDao;
        this.playerDao = playerDao;
        this.service = service;
    }

    public ModelAndView root(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "start.html");
    }

    public ModelAndView start(Request req, Response res) {
        service.start();
        res.redirect("/play");
        return null;
    }

    public ModelAndView play(Request req, Response res) {
        Map<Position, Piece> board = chessBoardDao.findAll();
        if (board.isEmpty()) {
            res.redirect("/start");
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            model.put(position.toString(), piece);
        }

        Player player = playerDao.findAll();
        model.put("turn", player.name());
        addSession(req, model, ERROR_MESSAGE);

        return new ModelAndView(model, "index.html");
    }

    public ModelAndView end(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        addSession(req, model, WINNER_MESSAGE);
        return new ModelAndView(model, "end.html");
    }

    public ModelAndView move(Request req, Response res) {
        ChessGame chessGame = ChessGame.of(new RunningGame(service.createChessBoard(), playerDao.findAll()));
        String turn = chessGame.getTurn();
        try {
            service.move(chessGame, req.queryParams("source"), req.queryParams("target"));
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("hasError", true);
            error.put("errorMessage", e.getMessage());
            req.session().attribute("ERROR_MESSAGE", error);
        }

        if (isFinished(chessGame)) {
            finishGame(req, res, turn);
            return null;
        }

        res.redirect("/play");
        return null;
    }

    private boolean isFinished(ChessGame chessGame) {
        return chessGame.isFinished();
    }

    private void finishGame(Request req, Response res, String turn) {
        Map<String, Object> winner = new HashMap<>();
        winner.put("hasWinner", true);
        winner.put("winnerMessage", turn + " 플레이어가 승리했습니다. 새 게임을 시작해주세요!!!");
        req.session().attribute(WINNER_MESSAGE, winner);

        res.redirect("/end");
    }

    private void addSession(Request req, Map<String, Object> model, String sessionName) {
        if (req.session().attribute(sessionName) != null) {
            model.putAll(req.session().attribute(sessionName));
            req.session().removeAttribute(sessionName);
        }
    }
}
