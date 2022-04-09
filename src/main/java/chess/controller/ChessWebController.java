package chess.controller;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import chess.domain.board.Position;
import chess.domain.dao.BoardDaoImpl;
import chess.domain.dao.PieceDaoImpl;
import chess.dto.ResponseDto;
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

    public void run() {
        port(8081);

        ChessGameService chessGameService = new ChessGameService(new PieceDaoImpl(), new BoardDaoImpl());

        get("/", (req, res) -> render(new HashMap<>(), "home.hbs"));

        get("/start", (req, res) -> {
            final ResponseDto responseDto = chessGameService.start();
            return responseDto.toString();
        });

        get("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("boardDto", chessGameService.getBoard());
            return render(model, "index.hbs");
        });

        post("/move", (req, res) -> {
            List<String> command = Arrays.asList(req.body().split(" "));
            final ResponseDto responseDto = chessGameService
                    .move(Position.from(command.get(0)), Position.from(command.get(1)));
            return responseDto.toString();
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("status", StatusDto.of(chessGameService.statusOfWhite(), chessGameService.statusOfBlack()));
            return render(model, "status.hbs");
        });

        get("/end", (req, res) -> {
            final ResponseDto responseDto = chessGameService.end();
            return responseDto.toString();
        });

        get("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("result",
                    ResultDto.of(chessGameService.statusOfWhite(), chessGameService.statusOfBlack(),
                            chessGameService.findWinner()));
            return render(model, "result.hbs");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
