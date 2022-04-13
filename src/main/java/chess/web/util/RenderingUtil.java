package chess.web.util;

import chess.dto.PieceDto;
import chess.dto.PiecesDto;
import chess.web.view.BoardView;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RenderingUtil {

    private static final HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();

    public static String render(PiecesDto piecesDto, String templatePath) {
        Map<String, PieceDto> model = mapPiecesDtoToModel(piecesDto);
        return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
    }

    private static Map<String, PieceDto> mapPiecesDtoToModel(PiecesDto piecesDto) {
        return BoardView.of(piecesDto)
            .getBoardView();
    }

}
