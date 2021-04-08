package chess;

import chess.controller.WebChessAction;
import chess.controller.dto.PieceDto;
import chess.dao.BoardDao;
import chess.dao.MoveLogDao;
import chess.dao.PieceDao;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessGameService chessGameService = new ChessGameService(new BoardDao(), new PieceDao(), new MoveLogDao());
        WebChessAction webChessAction = new WebChessAction(chessGameService);

        get("/", webChessAction::index);

        get("/game", webChessAction::start);

        post("/move", webChessAction::move);

        get("/end", webChessAction::end);

        get("/continue", webChessAction::continueGame);

        post("/status", webChessAction::status);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
