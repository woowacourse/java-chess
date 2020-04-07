package chess.controller;

import static chess.util.JsonParser.*;
import static spark.Spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.dao.PlayerDao;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.dto.BoardDto;
import chess.dto.MovableRequestDto;
import chess.dto.MoveRequestDto;
import chess.service.ChessService;
import chess.util.RoutesConfig;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    private ChessService service;

    public WebChessController(ChessService service) {
        this.service = service;
        route();
    }

    public void route() {
        addTemporaryPlayers();
        RoutesConfig.configure();
        before(RoutesConfig::setJsonContentType);

        get("/", this::renderEntryPoint);
        get("/boards", this::getPlayerContexts, json());
        post("/boards", this::addGameAndGetPlayers, json());

        get("/boards/:id", this::getBoard, json());
        post("/boards/:id", this::resetBoard, json());
        delete("/boards/:id", this::finishGame, json());

        path("/boards/:id", () -> {
            get("/status", this::isGameOver, json());
            get("/turn", this::isWhiteTurn, json());
            get("/score", this::getScore, json());

            post("/move", this::move, json());
            post("/movable", this::findAllAvailablePath, json());
        });

        get("/scores", this::getScoreContexts, json());
    }

    private static void addTemporaryPlayers() {
        // 플레이어 회원가입 / 로그인 구현 이전 foreign key 오류를 내지 않기 위해 임시로 DB에 플레이어 추가
        try {
            new PlayerDao().addInitialPlayers();
        } catch (SQLException ignored) {
        }
    }

    private String renderEntryPoint(Request request, Response response) {
        response.type("text/html");
        return render(new HashMap<>());
    }

    private static String render(Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }

    private Map<Integer, Map<Side, Player>> getPlayerContexts(final Request request, final Response response) throws
        SQLException {
        return service.getPlayerContexts();
    }

    private Map<Position, Piece> getBoard(final Request request, final Response response) throws SQLException {
        BoardDto dto = new BoardDto(service.findBoardById(parseId(request)));
        return dto.getBoard();
    }

    private Map<Position, Piece> resetBoard(final Request request, final Response response) throws SQLException {
        BoardDto dto = new BoardDto(service.resetGameById(parseId(request)));
        return dto.getBoard();
    }

    private Map<Integer, Map<Side, Player>> addGameAndGetPlayers(final Request request, final Response response) throws
        SQLException {
        // TODO: 실제 플레이어 기능 추가
        Player white = new Player(1, "hodol", "password");
        Player black = new Player(2, "pobi", "password");
        return service.addGame(white, black);
    }

    public List<String> findAllAvailablePath(final Request request, final Response response) throws SQLException {
        MovableRequestDto dto = new Gson().fromJson(request.body(), MovableRequestDto.class);
        return service.findAllAvailablePath(parseId(request), dto.getFrom());
    }

    public Map<Integer, Map<Side, Double>> getScoreContexts(final Request request, final Response response) throws
        SQLException {
        return service.getScoreContexts();
    }

    public Map<Side, Double> getScore(final Request request, final Response response) throws SQLException {
        return service.getScoresById(parseId(request));
    }

    public boolean isWhiteTurn(final Request request, final Response response) throws SQLException {
        return service.isWhiteTurn(parseId(request));
    }

    public boolean move(final Request request, final Response response) throws SQLException {
        MoveRequestDto dto = new Gson().fromJson(request.body(), MoveRequestDto.class);
        return service.addMoveByGameId(parseId(request), dto.getFrom(), dto.getTo());
    }

    public boolean finishGame(final Request request, final Response response) throws SQLException {
        return service.finishGameById(parseId(request));
    }

    public boolean isGameOver(final Request request, final Response response) throws SQLException {
        return service.isGameOver(parseId(request));
    }

    private static int parseId(final Request request) {
        return Integer.parseInt(request.params(":id"));
    }
}
