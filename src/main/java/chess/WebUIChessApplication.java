package chess;

import chess.domain.web.FakeLogDao;
import chess.domain.web.Log;
import chess.domain.web.WebChessGame;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.JsonTransformer.json;
import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        WebChessGame chessGame = new WebChessGame();
        FakeLogDao fakeLogDao = new FakeLogDao();

        staticFiles.location("/public");
        get("/", (req, res) -> {
            chessGame.reset();
            Map<String, Object> model = new HashMap<>();
            model.put("status", true);
            return render(model, "index.html");
        });

        get("/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.reset();
            fakeLogDao.clear();

            model.put("status", true);
            return render(model, "chess.html");
        });

        get("/loading", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("status", true);

            chessGame.reset();

            Map<Integer, Log> fakeLog = fakeLogDao.selectAll();
            // TODO: 2020/04/03 DB가 비어있을 경우 처리

            for(Log log : fakeLog.values()) {
                chessGame.move(PositionFactory.of(log.getStart()), PositionFactory.of(log.getEnd()));
            }
            return render(model, "chess.html");
        });

        get("/board", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", chessGame.createBoard().getBoard());
            model.put("turn", chessGame.getTurn());
            model.put("score", chessGame.calculateScore());
            model.put("status", true);
            return model;
        }, json());
        post("/board", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            if (req.queryParams("start").equals(req.queryParams("end"))) {
                model.put("status", true);
                return render(model, "chess.html");
            }

            try {
                chessGame.move(PositionFactory.of(req.queryParams("start")), PositionFactory.of(req.queryParams("end")));
                fakeLogDao.insert(req.queryParams("start"), req.queryParams("end"));
                model.put("status", true);
                if (chessGame.isKingDead()) {
                    model.put("winner", chessGame.getAliveKingColor());
                    fakeLogDao.clear();
                    chessGame.reset();
                    return render(model, "result.html");
                }
                return render(model, "chess.html");
            } catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException e) {
                model.put("status", false);
                return render(model, "chess.html");
            }
        });
        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                model.put("status", true);
                // TODO: 2020/04/02 필요없는 코드 수행 방지!! 엔드일때도 이걸 다 수행해야 됨 지금 로직상
                List<String> movablePositionNames = chessGame
                        .findMovablePositions(PositionFactory.of(req.queryParams("start")))
                        .getPositions()
                        .stream()
                        .map(Position::toString)
                        .collect(Collectors.toList());
                model.put("position", req.queryParams("start"));
                model.put("movable", movablePositionNames);
                return model;
            } catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException e) {
                model.put("status", false);
                model.put("exception", e.getMessage());
                return model;
            }
        }, json());
        post("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                model.put("status", true);
                model.put("position", req.queryParams("end"));
                return model;
            } catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException e) {
                model.put("status", false);
                model.put("exception", e.getMessage());
                return model;
            }
        }, json());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

