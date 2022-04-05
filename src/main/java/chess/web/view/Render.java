package chess.web.view;

import chess.domain.board.ChessBoard;
import chess.web.dto.BoardDto;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Render {

    private static final TemplateEngine HANDLEBARS_RENDER = new HandlebarsTemplateEngine();

    public static Map<String, Object> renderBoard(ChessBoard chessBoard){
        Map<String, Object> boardDto = new BoardDto(chessBoard).getResult();
        Map<String, Object> model = new HashMap<>();

        String boardHtml = renderHtml(boardDto, "/board.html");
        model.put("board", boardHtml);
        model.put("currentTurn", chessBoard.getCurrentTurn());
        return model;
    }

    public static String renderHtml(Map<String, Object> model, String templatePath) {
        return HANDLEBARS_RENDER.render(new ModelAndView(model, templatePath));
    }
}
