package chess.view.web;

import chess.controller.dto.BoardDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class OutputView {

    private OutputView(){

    }

    public static String printBoard(final String name, final BoardDto boardDto){
        Map<String, Object> model = new HashMap<>();
        model.put(name, boardDto);
        return OutputView.render(model, "chessBoard.html");
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
