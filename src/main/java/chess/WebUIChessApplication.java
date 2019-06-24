package chess;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.BoardFactory;
import chess.domain.Game;
import chess.domain.Point;
import chess.domain.pieces.Piece;
import chess.utils.DBUtil;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        port(8080);

        DataSource dataSource = DBUtil.getDataSource();

        PieceDao pieceDao = new PieceDao(dataSource);
        GameDao gameDao = new GameDao(dataSource);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Game game = new Game(BoardFactory.init());
            req.session().attribute("game", game);
            return render(model, "index.html");
        });

        get("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            Point start = new Point(source);
            Point end = new Point(target);

            Game game = req.session().attribute("game");
            game.move(start, end);
            game.isKingAlive();
            game.changeTurn();
            Map<Point, Piece> board = game.getBoard();
            model.put("board", board);
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
