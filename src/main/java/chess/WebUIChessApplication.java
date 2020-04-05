package chess;

import chess.controller.WebController;
import chess.dao.BoardDAO;
import chess.domains.Record;
import chess.domains.board.Board;
import chess.service.WebService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/public");

        Board board = new Board();
        List<Record> records = new ArrayList<>();
        BoardDAO boardDAO = new BoardDAO();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            board.initialize();
            boardDAO.deleteBoard();
            boardDAO.addBoard(board.getBoard());

            Map<String, String> pieceCodes = WebController.convertView(boardDAO.showPieces());
            records.clear();
            records.add(new Record("start", ""));

            Map<String, Object> model = WebController.makeModel(board, records, pieceCodes);
            return render(model, "index.html");
        });

        get("/resume", (req, res) -> {
            Map<String, String> pieceCodes = WebController.convertView(boardDAO.showPieces());
            Map<String, Object> model = WebController.makeModel(board, records, pieceCodes);

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            Record move = new Record("move " + source + ", " + target, "");
            try {
                WebService.move(board, source, target);
            } catch (Exception e) {
                move.setErrorMsg(e.getMessage());
            }
            records.add(move);

            boardDAO.deleteBoard();
            boardDAO.addBoard(board.getBoard());

            Map<String, String> pieceCodes = WebController.convertView(boardDAO.showPieces());
            Map<String, Object> model = WebController.makeModel(board, records, pieceCodes);

            return render(model, "index.html");
        });
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
