package chess;

import chess.model.BoardDTO;
import chess.model.ChessDAO;
import chess.model.ChessGame;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        get("/newgame.html", (req, res) -> {
            ChessDAO chessDAO = ChessDAO.getInstance();
            Map<String, Object> model = new HashMap<>();
            model.put("board", chessDAO.selectByTurn(1).getPieces());

            ChessGame game = new ChessGame();

            req.session().attribute("game", game);

            return render(model, "newgame.html");
        });

        post("/newgame.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessDAO chessDAO = ChessDAO.getInstance();
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            ChessGame game = req.session().attribute("game");
            game.movePiece(source, target);

            System.err.println(game.convertToList().get(0));
            BoardDTO boardDTO = new BoardDTO(game.convertToList());
//            chessDAO.updateBoard(boardDTO);

            model.put("board", boardDTO.getPieces());
            req.session().attribute("game", game);

            return render(model, "newgame.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

