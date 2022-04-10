package chess.web.util;

import chess.domain.piece.Piece;
import chess.dto.PiecesDto;
import chess.web.view.BoardView;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RenderingUtil {

    private static final HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();

    public static String render(PiecesDto piecesDto, String templatePath) {
        Map<String, Piece> model = mapPiecesDtoToModel(piecesDto);
        return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
    }

    private static Map<String, Piece> mapPiecesDtoToModel(PiecesDto piecesDto) {
        return BoardView.of(piecesDto)
            .getBoardView();
    }

}
