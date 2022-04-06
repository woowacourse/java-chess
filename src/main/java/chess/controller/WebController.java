package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.dto.GameDto;
import chess.dto.UpdatePiecePositionDto;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private static final String HTML_PATH = "index.html";
    private static final String GAME_ID = "game-id"; // TODO: 여러 게임 방 기능 구현시 제거

    public void run() {
        ChessGame chessGame = ChessGame.create();
        chessGame.startGame();

        ChessService chessService = ChessService.of(new GameDao(), new BoardDao());

        Map<String, Object> model = new HashMap<>();

        get("/", (req, res) -> {
            GameDto gameDto = chessService.getGame(GAME_ID);
            model.put("boards", gameDto.getBoardDto().getPieceImages());
            model.put("score", gameDto.getScoreResultDto());
            model.put("turn", gameDto.getCurrentTurnDto());

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

            chessService.movePiece(UpdatePiecePositionDto.of(GAME_ID, from, to));

            return request;
        });
    }

    private String render(Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView(model, HTML_PATH);
        return new HandlebarsTemplateEngine().render(modelAndView);
    }
}
