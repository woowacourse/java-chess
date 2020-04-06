package chess;

import chess.controller.WebController;
import chess.dao.BoardDAO;
import chess.dao.RecordDAO;
import chess.domains.board.Board;
import chess.domains.piece.PieceColor;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
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
            WebController.startGame(board, boardDAO, recordDAO);

            Map<String, Object> model = new HashMap<>();
            model.put("records", recordDAO.readRecords());
            model.put("pieces", WebController.convertView(boardDAO.showPieces()));
            model.put("turn", WebController.printTurn(WebController.turn(board)));
            model.put("white_score", WebController.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", WebController.calculateScore(board, PieceColor.BLACK));

            return render(model, "index.html");
        });

        get("/resume", (req, res) -> {
            WebController.resumeGame(board, recordDAO);

            Map<String, Object> model = new HashMap<>();
            model.put("records", recordDAO.readRecords());
            model.put("pieces", WebController.convertView(boardDAO.showPieces()));
            model.put("turn", WebController.printTurn(WebController.turn(board)));
            model.put("white_score", WebController.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", WebController.calculateScore(board, PieceColor.BLACK));

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            WebController.movePiece(board, source, target);

            WebController.endGame(board);

            Map<String, Object> model = new HashMap<>();
            model.put("records", recordDAO.readRecords());
            model.put("end", board.isGameOver());
            model.put("pieces", WebController.convertView(boardDAO.showPieces()));
            model.put("turn", WebController.printTurn(WebController.turn(board)));
            model.put("white_score", WebController.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", WebController.calculateScore(board, PieceColor.BLACK));

            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
