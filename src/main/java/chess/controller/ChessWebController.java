package chess.controller;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.dto.ChessBoardDto;
import chess.dto.StatusDto;
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

        get("/", (req, res) -> {
            chessGame = new ChessGame();
            return render(new HashMap<>(), "home.hbs");
        });

        get("/start", (req, res) -> {
            chessGame.start();
            res.redirect("/chess");
            return null;
        });

        get("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("boardDto", ChessBoardDto.from(chessGame.getBoard().getPiecesByPosition()));
            return render(model, "index.hbs");
        });

        post("/move", (req, res) -> {
            System.out.println(req.body());
            List<String> command = Arrays.asList(req.body().split(" "));
            chessGame.move(Position.from(command.get(0)), Position.from(command.get(1)));
            res.redirect("/chess");
            return res.toString();
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("status", StatusDto.of(chessGame.statusOfWhite(), chessGame.statusOfBlack()));
            return render(model, "status.hbs");
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
