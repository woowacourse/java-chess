package chess.web.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.Player;
import chess.domain.game.state.RunningGame;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.domain.piece.property.Color;
import chess.web.dao.ChessBoardDao;
import chess.web.dao.PlayerDao;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ChessController {

    public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
    public static final String WINNER_MESSAGE = "WINNER_MESSAGE";
    private final ChessBoardDao chessBoardDao;
    private final PlayerDao playerDao;

    public ChessController(ChessBoardDao chessBoardDao, PlayerDao playerDao) {
        this.chessBoardDao = chessBoardDao;
        this.playerDao = playerDao;
    }

    public ModelAndView root(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "start.html");
    }

    public ModelAndView start(Request req, Response res) {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        removeAll();
        saveAll(chessGame);

        res.redirect("/play");
        return null;
    }

    public ModelAndView play(Request req, Response res) {
        Map<String, String> board = chessBoardDao.findAll();
        if (board.isEmpty()) {
            res.redirect("/start");
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        for (String position : board.keySet()) {
            String piece = board.get(position);
            model.put(position, PieceFactory.of(position, piece));
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
        Map<String, String> board = chessBoardDao.findAll();
        Map<Position, Piece> chessBoard = new HashMap<>();

        for (String position : board.keySet()) {
            Position currentPosition = Position.of(File.of(position.substring(0, 1).toUpperCase()),
                    Rank.of(position.substring(1, 2)));

            Piece currentPiece = PieceFactory.of(position, board.get(position));
            chessBoard.put(currentPosition, currentPiece);
        }
        ChessGame chessGame = ChessGame.of(new RunningGame(ChessBoard.of(chessBoard), playerDao.findAll()));

        final chess.web.controller.Request request = chess.web.controller.Request.of(req.body());
        String command = request.command();

        //command a2a4
        String turn = chessGame.getTurn();
        String source = command.substring(0, 2);
        String target = command.substring(2, 4);
        try {
            chessGame.move(Position.of(source), Position.of(target));
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("hasError", true);
            error.put("errorMessage", e.getMessage());
            req.session().attribute("ERROR_MESSAGE", error);
        }

        if (chessGame.isFinished()) {
            Map<String, Object> winner = new HashMap<>();
            winner.put("hasWinner", true);
            winner.put("winnerMessage", turn + " 플레이어가 승리했습니다. 새 게임을 시작해주세요!!!");
            req.session().attribute(WINNER_MESSAGE, winner);

            res.redirect("/end");
            return null;
        }

        removeAll();
        saveAll(chessGame);

        res.redirect("/play");
        return null;
    }

    private void addSession(Request req, Map<String, Object> model, String sessionName) {
        if (req.session().attribute(sessionName) != null) {
            model.putAll(req.session().attribute(sessionName));
            req.session().removeAttribute(sessionName);
        }
    }

    private void removeAll() {
        chessBoardDao.deleteAll();
        playerDao.deleteAll();

    }

    private void saveAll(ChessGame chessGame) {
        Map<Position, Piece> chessBoard = chessGame.getBoard();
        for (Position position : chessBoard.keySet()) {
            chessBoardDao.save(position, chessBoard.get(position));
        }
        playerDao.save(Color.of(chessGame.getTurn()));
    }
}
