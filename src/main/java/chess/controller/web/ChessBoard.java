package chess.controller.web;

import chess.controller.web.dto.*;
import chess.domain.game.Game;
import chess.domain.location.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final Map<PositionDto, PieceDto> board;
    private final List<ScoreDto> score;
    private final ColorDto color;
    private final boolean isFinished;

    public ChessBoard(Game game) {
        this.board = new BoardDto(game.allBoard()).getMaps();
        this.score = new ScoreDtos(game.score()).getScoreDtos();
        this.color = new ColorDto(game.currentPlayer());
        this.isFinished = game.isEnd();
    }

    public String html(String errorMessage) {
        return renderChessBoard(errorMessage);
    }

    public String html() {
        return html("");
    }

    private String renderChessBoard(String errorMessage) {
        Map<String, Object> model = makeBoardModel();
        model.put("error", new ErrorDto(errorMessage));

        return render(model, "chessboard.html");
    }

    private Map<String, Object> makeBoardModel() {
        Map<String, Object> model = new HashMap<>();

        for (PositionDto positionDTO : board.keySet()) {
            model.put(positionDTO.getKey(), board.get(positionDTO));
        }
        model.put("scores", score);
        model.put("turn", color);

        if (isFinished) {
            model.put("winner", color);
        }

        return model;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
