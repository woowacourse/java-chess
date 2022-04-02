package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private static final String HTML_PATH = "index.html";

    public void run() {
        ChessGame chessGame = new ChessGame();
        chessGame.startGame();

        Map<String, Object> model = new HashMap<>();

        get("/", (req, res) -> {
            BoardDto boardDto = BoardDto.from(chessGame.getBoard());
            List<List<String>> value = boardDto.getPieceImages();
            model.put("boards", value);

            return render(model);
        });

        post("/move", (req, res) -> {
            String request = req.body();
            // TODO: 리팩토링
            // TODO: 입력값을 파싱하는게 컨트롤러의 책임일까?

            String fromText = request.split("from=")[1].split("&")[0];
            String toText = request.split("to=")[1];

            Position from = Position.from(fromText);
            Position to = Position.from(toText);

            chessGame.movePiece(from, to);
            return request;
        });
    }

    private String render(Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView(model, HTML_PATH);
        return new HandlebarsTemplateEngine().render(modelAndView);
    }
}
