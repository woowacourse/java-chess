package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.Board;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private static final String TERMINATE_MESSAGE = "게임이 종료되었습니다.";
    private static final String STARTED_PATH = "chess.html";
    private static final String INDEX_PATH = "index.html";
    private static final String WINNING_MESSAGE = "%s가 이겼습니다!!";

    private final ChessService chessService;

    public ChessWebController() {
        this.chessService = new ChessService(new ChessGameDao(), new BoardDao());
    }

    public void run() {
        renderHome();
        renderEnter();
        renderReady();
        renderStart();
        renderMove();
        renderStatus();
        renderEnd();
    }

    private void renderHome() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, INDEX_PATH);
        });
    }

    private void renderEnter() {
        post("/enter", (req, res) -> {
            String roomId = req.queryParams("room");
            int gameId = chessService.saveGame(roomId);
            res.redirect("/game/" + gameId);
            return null;
        });
    }

    private void renderReady() {
        get("/game/:id", (req, res) -> {
            int gameId = Integer.parseInt(req.params(":id"));
            Board board = chessService.ready(gameId);
            Map<String, Object> model = board.toMap();
            model.put("id", gameId);
            return render(model, STARTED_PATH);
        });
    }

    private void renderStart() {
        get("/game/:id/start", (req, res) -> {
            try {
                int gameId = Integer.parseInt(req.params(":id"));
                Board board = chessService.start(gameId);
                Map<String, Object> model = board.toMap();
                model.put("id", gameId);
                return render(model, STARTED_PATH);
            } catch (IllegalStateException exception) {
                return renderErrorMessage(exception.getMessage());
            }
        });
    }

    private void renderMove() {
        post("/game/:id/move", (req, res) -> {
            try {
                int gameId = Integer.parseInt(req.params(":id"));
                Board board = chessService.move(gameId, req.queryParams("from"), req.queryParams("to"));
                Map<String, Object> model = board.toMap();
                model.putAll(renderWinner());
                model.put("id", gameId);
                return render(model, STARTED_PATH);
            } catch (IllegalStateException | IllegalArgumentException exception) {
                return renderErrorMessage(exception.getMessage());
            }
        });
    }

    private Map<String, Object> renderWinner() {
        Map<String, Object> winningMessage = new HashMap<>();
        if (chessService.isComplete()) {
            String winner = String.format(WINNING_MESSAGE, chessService.complete().name());
            winningMessage.put("complete", winner);
        }
        return winningMessage;
    }

    private void renderStatus() {
        get("/game/:id/status", (req, res) -> {
            int gameId = Integer.parseInt(req.params(":id"));
            Map<String, Object> model = new HashMap<>();
            try {
                model.putAll(chessService.showBoard());
                model.putAll(chessService.showStatus());
                model.put("id", gameId);
                return render(model, STARTED_PATH);
            } catch (IllegalStateException exception) {
                return renderErrorMessage(exception.getMessage());
            }
        });
    }

    private void renderEnd() {
        get("/game/:id/terminate", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                int gameId = Integer.parseInt(req.params(":id"));
                model.put("terminate", TERMINATE_MESSAGE);
                model.put("id", gameId);
                model.putAll(chessService.showBoard());
                return render(model, STARTED_PATH);
            } catch (IllegalStateException exception) {
                return renderErrorMessage(exception.getMessage());
            }
        });
    }

    private String renderErrorMessage(String errorMessage) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", errorMessage);
        model.putAll(chessService.showBoard());
        return render(model, STARTED_PATH);
    }

    private String render(Map<String, Object> model, String tempalatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, tempalatePath));
    }

}
