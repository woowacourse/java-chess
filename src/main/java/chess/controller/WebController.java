package chess.controller;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.Team;
import chess.domain.board.Position;
import chess.domain.result.StatusResult;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WebController {

    private final ChessService chessService = new ChessService(new ChessGameDao(), new BoardDao());

    public String boardPage(Request request, Response response) {
        Map<String, Object> model = setModel();
        return render(model, "index.html");
    }

    private Map<String, Object> setModel() {
        Map<String, Object> model = new HashMap<>();
        addBoardInformation(model);
        addTurnInformation(model);
        addPlayingInformation(model);
        addScoreInformation(model);
        return model;
    }

    private void addScoreInformation(Map<String, Object> model) {
        if (chessService.isPlayingGame()) {
            StatusResult statusResult = chessService.processStatus();
            model.put("blackscore", statusResult.getBlackScore());
            model.put("whitescore", statusResult.getWhiteScore());
        }
    }

    private void addBoardInformation(Map<String, Object> model) {
        model.put("pieces", chessService.getBoardInformation());
    }

    private void addTurnInformation(Map<String, Object> model) {
        if (!chessService.getCurrentTeam().isNeutrality(Team.NEUTRALITY)) {
            model.put("team", chessService.getCurrentTeam());
        }
    }

    private void addPlayingInformation(Map<String, Object> model) {
        if (chessService.isReadyGame()) {
            model.put("start", false);
            return;
        }
        model.put("start", true);
    }

    public String move(Request request, Response response) {
        processMove(request);
        response.redirect("/");
        return null;
    }

    private void processMove(Request request) {
        String[] parameters = request.body().split("&");
        Map<String, String> map = parameterToMap(parameters);
        String rawSource = map.get("source").trim();
        String rawTarget = map.get("target").trim();
        chessService.processMove(Position.of(rawSource), Position.of(rawTarget));
    }

    public String start(Request request, Response response) {
        chessService.init();
        response.redirect("/");
        return null;
    }

    public <T extends Exception> void boardPageWithException(T exception, Request request, Response response) {
        Map<String, Object> model = setModel();
        model.put("error", exception.getMessage());
        response.body(render(model, "index.html"));
    }

    private Map<String, String> parameterToMap(String[] parameters) {
        return Arrays.stream(parameters)
                .map(s -> s.split("="))
                .collect(Collectors.toMap(s -> s[0], s -> URLDecoder.decode(s[1], StandardCharsets.UTF_8)));
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
