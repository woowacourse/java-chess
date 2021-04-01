package chess.view.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.ScoreDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class OutputView {

    private OutputView(){

    }

    public static String printGame(BoardDto boardDto, ScoreDto whiteScoreDto, ScoreDto blackScoreDto) {
        Map<String, Object> model = new HashMap<>();
        model.put("board", boardDto);
        model.put("whiteScore", whiteScoreDto);
        model.put("blackScore", blackScoreDto);
        return render(model, "chessBoard.html");
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
