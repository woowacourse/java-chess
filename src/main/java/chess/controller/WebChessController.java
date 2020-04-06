package chess.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.dto.MovableRequestDto;
import chess.dto.MoveRequestDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    private ChessService service;

    public WebChessController(ChessService service) {
        this.service = service;
    }

    public String renderEntryPoint(Request request, Response response) {
        response.type("text/html");
        return render(new HashMap<>());
    }

    private static String render(Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }

    public Map<Integer, Map<Side, Player>> getPlayerContexts(final Request request, final Response response) throws
        SQLException {
        return service.getPlayerContexts();
    }

    public Map<Position, Piece> getBoard(final Request request, final Response response) throws SQLException {
        return service.findBoardById(getId(request));
    }

    public Map<Position, Piece> resetBoard(final Request request, final Response response) throws SQLException {
        return service.resetGameById(getId(request));
    }

    public Map<Integer, Map<Side, Player>> addGameAndGetPlayers(final Request request, final Response response) throws
        SQLException {
        // TODO: 실제 플레이어 기능 추가
        Player white = new Player("hodol", "password");
        white.setId(1);
        Player black = new Player("pobi", "password");
        black.setId(2);
        return service.addGame(white, black);
    }

    public List<String> findAllAvailablePath(final Request request, final Response response) throws SQLException {
        MovableRequestDto dto = new Gson().fromJson(request.body(), MovableRequestDto.class);
        return service.findAllAvailablePath(getId(request), dto.getFrom());
    }

    public Map<Integer, Map<Side, Double>> getScoreContexts(final Request request, final Response response) throws
        SQLException {
        return service.getScoreContexts();
    }

    public Map<Side, Double> getScore(final Request request, final Response response) throws SQLException {
        return service.getScoresById(getId(request));
    }

    public boolean isWhiteTurn(final Request request, final Response response) throws SQLException {
        return service.isWhiteTurn(getId(request));
    }

    public boolean move(final Request request, final Response response) throws SQLException {
        MoveRequestDto dto = new Gson().fromJson(request.body(), MoveRequestDto.class);
        return service.addMoveByGameId(getId(request), dto);
    }

    public boolean finishGame(final Request request, final Response response) throws SQLException {
        return service.finishGameById(getId(request));
    }

    public boolean isGameOver(final Request request, final Response response) throws SQLException {
        return service.isGameOver(getId(request));
    }

    private static int getId(final Request request) {
        return Integer.parseInt(request.params(":id"));
    }
}
