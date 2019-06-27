package chess.controller;

import chess.domain.Game;
import chess.domain.PieceFactory;
import chess.domain.Point;
import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.dto.PieceDto;
import chess.service.ChessGameService;
import org.javatuples.Pair;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameController {
    private ChessGameService chessGameService = ChessGameService.getInstance();

    public Object main(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        List<Integer> gameIds = chessGameService.findAllId();
        model.put("gameIds", gameIds);
        return render(model, "main.html");
    }

    public Object start(Request req, Response res) {
        Pair<Game, Integer> game = chessGameService.createNewGame();

        Map<String, Object> model = putBoardStatus(game.getValue0());
        req.session().attribute("gameId", game.getValue1());
        return render(model, "home.html");
    }

    private Map<String, Object> putBoardStatus(Game game) {
        Map<String, Object> model = new HashMap<>();
        model.put("board", chessGameService.convertBoard(game.getBoard()));
        model.put("turn", game.getTurn() == Team.WHITE ? "백" : "흑");
        model.put("whiteScore", game.calculateScore(Team.WHITE));
        model.put("blackScore", game.calculateScore(Team.BLACK));
        return model;
    }

    public Object continueGame(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int gameId = Integer.parseInt(req.params(":gameId"));
        req.session().attribute("gameId", gameId);
        Game game = getGame(gameId);

        if (!game.isKingAlive()) {
            game.changeTurn();
            model.put("gameEnd", true);
            model.put("winner", game.getTurn());
        }
        model.putAll(putBoardStatus(game));
        return render(model, "home.html");
    }

    public Object move(Request req, Response res) {
        String source = req.queryParams("source");
        String target = req.queryParams("target");
        Point start = new Point(source);
        Point end = new Point(target);

        int gameId = req.session().attribute("gameId");
        Game game = chessGameService.move(gameId, start, end);

        Map<String, Object> model = new HashMap<>();
        if (!game.isKingAlive()) {
            model.put("gameEnd", true);
            model.put("winner", game.getTurn());
        }
        model.putAll(putBoardStatus(game));

        return render(model, "home.html");
    }

    public Object printMessage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", req.queryParams("message"));
        return render(model, "error.html");
    }

    public void catchException(Exception e, Request req, Response res) {
        try {
            res.redirect("/error?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            res.redirect("/error?message=" + "URL Encoding Error");
        }
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private Game getGame(int gameId) {
        List<PieceDto> pieceDtos = chessGameService.findPieceById(gameId);
        Map<Point, Piece> board = new HashMap<>();
        pieceDtos.forEach(dto -> board.put(new Point(dto.getX(), dto.getY()),
                PieceFactory.of(dto.getName(), dto.isTeam() ? Team.WHITE : Team.BLACK)));
        Team turn = chessGameService.findTurnByGameId(gameId) ? Team.WHITE : Team.BLACK;
        return new Game(board, turn);
    }
}
