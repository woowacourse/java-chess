package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.position.Position;
import chess.dto.request.UpdatePiecePositionDto;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private static final String HTML_PATH = "index.html";
    private static final String GAME_ID = "game-id"; // TODO: 여러 게임 방 기능 구현시 제거

    private final ChessService chessService;

    public WebController() {
        this.chessService = ChessService.of(new GameDao(), new BoardDao());
    }

    public void run() {
        get("/", (req, res) -> showBoard());
        post("/move", (req, res) -> movePiece(req));
    }

    private String showBoard() {
        Map<String, Object> model = new HashMap<>();
        model.put("board", chessService.getBoard(GAME_ID).getPieceImages());
        model.put("score", chessService.getScore(GAME_ID));
        model.put("turn", chessService.getCurrentTurn(GAME_ID));
        model.put("winColor", chessService.getWinColor(GAME_ID));

        return render(model);
    }

    private String movePiece(Request req) {
        try {
            String request = req.body();
            // TODO: 리팩토링
            // TODO: 입력값을 파싱하는게 컨트롤러의 책임일까?

            String fromText = request.split("from=")[1].split("&")[0];
            String toText = request.split("to=")[1];

            Position from = Position.from(fromText);
            Position to = Position.from(toText);

            chessService.movePiece(UpdatePiecePositionDto.of(GAME_ID, from, to));
        } catch (IllegalStateException e) {
            return e.getMessage();
        }

        return "success";
    }

    private String render(Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView(model, HTML_PATH);
        return new HandlebarsTemplateEngine().render(modelAndView);
    }
}
