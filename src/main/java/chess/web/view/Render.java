package chess.web.view;

import chess.domain.board.ChessBoard;
import chess.web.dao.ChessGame;
import chess.web.dto.BoardDTO;
import chess.web.dto.ChessGameDTO;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Render {

    private static final TemplateEngine HANDLEBARS_RENDER = new HandlebarsTemplateEngine();

    public static Map<String, Object> renderBoard(ChessGame chessGame) {
        Map<String, Object> boardDto = new BoardDTO(chessGame.getChessBoard()).getResult();
        Map<String, Object> model = new HashMap<>();

        String boardHtml = renderHtml(boardDto, "/board.html");
        model.put("board", boardHtml);
        model.put("currentTurn", chessGame.getChessBoard().getCurrentTurn());
        return model;
    }

    public static String renderHtml(Map<String, Object> model, String templatePath) {
        return HANDLEBARS_RENDER.render(new ModelAndView(model, templatePath));
    }

    public static String renderGame(ChessGameDTO chessGameDTO) {
        Map<String, Object> model = new HashMap<>();
        model.put("gameId", chessGameDTO.getId());
        model.put("gameName", chessGameDTO.getName());
        return renderHtml(model, "/game.html");
    }
}
