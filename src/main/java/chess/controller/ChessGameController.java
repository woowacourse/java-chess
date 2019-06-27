package chess.controller;

import chess.WebUIChessApplication;
import chess.domain.Game;
import chess.domain.PieceFactory;
import chess.domain.Point;
import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.dto.PieceDto;
import chess.service.ChessGameService;
import org.javatuples.Pair;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameController {
    private ChessGameService chessGameService = ChessGameService.getInstance();

    public String showIndex(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        List<Integer> gameIds = chessGameService.findAllId();
        model.put("gameIds", gameIds);
        return WebUIChessApplication.render(model, "main.html");
    }

    public String game(Request req, Response res) {
        Pair<Game, Integer> game = chessGameService.createNewGame();

        Map<String, Object> model = putBoardStatus(game.getValue0());
        req.session().attribute("gameId", game.getValue1());
        return WebUIChessApplication.render(model, "home.html");
    }

    public String move(Request req, Response res) {
        String source = req.queryParams("source");
        String target = req.queryParams("target");
        Point start = new Point(source);
        Point end = new Point(target);

        int gameId = req.session().attribute("gameId");
        Game game = chessGameService.move(gameId, start, end);

        Map<String, Object> model = new HashMap<>();

        model.put("gameEnd", !game.isKingAlive());
        model.putAll(putBoardStatus(game));

        return WebUIChessApplication.render(model, "home.html");
    }

    public String continueGame(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int gameId = Integer.parseInt(req.params(":gameId"));
        req.session().attribute("gameId", gameId);
        Game game = getGame(gameId);

        model.put("gameEnd", !game.isKingAlive());
        model.putAll(putBoardStatus(game));
        return WebUIChessApplication.render(model, "home.html");
    }

    private Map<String, Object> putBoardStatus(Game game) {
        Map<String, Object> model = new HashMap<>();
        model.put("board", chessGameService.convertBoard(game.getBoard()));
        model.put("turn", game.getTurn() == Team.WHITE ? "백" : "흑");
        model.put("whiteScore", game.calculateScore(Team.WHITE));
        model.put("blackScore", game.calculateScore(Team.BLACK));
        return model;
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
