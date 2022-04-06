package chess.controller;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.domain.dao.BoardDaoImpl;
import chess.domain.dao.PieceDaoImpl;
import chess.dto.ResultDto;
import chess.dto.StatusDto;
import chess.service.ChessGameService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private ChessGame chessGame;

    public void run() {
        port(8081);

        ChessGameService chessGameService = new ChessGameService(new PieceDaoImpl(), new BoardDaoImpl());

        get("/", (req, res) -> render(new HashMap<>(), "home.hbs"));

        get("/start", (req, res) -> {
            chessGameService.start();
            res.redirect("/chess");
            return null;
        });

        get("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("boardDto", chessGameService.getBoard());
            return render(model, "index.hbs");
        });

        post("/move", (req, res) -> {
            List<String> command = Arrays.asList(req.body().split(" "));
            chessGameService.move(Position.from(command.get(0)), Position.from(command.get(1)));
            if (!chessGameService.isRunning()) {
                res.status(301);
                return res.toString();
            }
            res.status(302);
            return res.toString();
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("status", StatusDto.of(chessGameService.statusOfWhite(), chessGameService.statusOfBlack()));
            return render(model, "status.hbs");
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGameService.end();
            model.put("result",
                    ResultDto.of(chessGameService.statusOfWhite(), chessGameService.statusOfBlack(), chessGameService.findWinner()));
            return render(model, "result.hbs");
        });

        get("/save", (req, res) -> {
            chessGameService.save(chessGameService.getTurn());
            res.redirect("/chess");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
