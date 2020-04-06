package chess;

import chess.controller.WebController;
import chess.dao.BoardDAO;
import chess.dao.RecordDAO;
import chess.domains.Record;
import chess.domains.board.Board;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/public");

        Board board = new Board();
        BoardDAO boardDAO = new BoardDAO();
        RecordDAO recordDAO = new RecordDAO();

        get("/", (req, res) -> {
            board.initialize();
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            boardDAO.clearBoard();
            boardDAO.addBoard(board.getBoard());

            Map<String, String> pieceCodes = WebController.convertView(boardDAO.showPieces());

            recordDAO.clearRecord();
            recordDAO.addRecord(new Record("start", ""));

            List<Record> records = recordDAO.readRecords();

            Map<String, Object> model = WebController.makeModel(board, records, pieceCodes);
            return render(model, "index.html");
        });

        get("/resume", (req, res) -> {
            board.initialize();
            List<Record> records = recordDAO.readRecords();
            board.recoverRecords(records);

            Map<String, String> pieceCodes = WebController.convertView(boardDAO.showPieces());
            Map<String, Object> model = WebController.makeModel(board, records, pieceCodes);

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            Record move = new Record("move " + source + " " + target, "");

            try {
                WebController.move(board, source, target);
            } catch (Exception e) {
                move.setErrorMsg(e.getMessage());
            }

            recordDAO.addRecord(move);

            boardDAO.clearBoard();
            boardDAO.addBoard(board.getBoard());

            List<Record> records = recordDAO.readRecords();
            Map<String, String> pieceCodes = WebController.convertView(boardDAO.showPieces());
            Map<String, Object> model = WebController.makeModel(board, records, pieceCodes);

            return render(model, "index.html");
        });
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
