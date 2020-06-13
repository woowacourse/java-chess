package chess.controller;

import chess.dao.PieceDao;
import chess.dao.PieceDaoFactory;
import chess.domain.board.Board;
import chess.domain.board.service.BoardService;
import chess.domain.dto.BoardDto;
import chess.domain.piece.service.PieceService;
import chess.domain.piece.state.Result;
import chess.domain.position.MovingFlow;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {
    private static final BoardService boardService = new BoardService();

    public void route() {
        get("/", (request, response) -> "Hello World");

        get("/start", (request, response) -> {
            PieceDaoFactory pieceDaoFactory = new PieceDaoFactory();
            PieceDao pieceDao = pieceDaoFactory.createPieceDao();
            Board board = boardService.initialize(new PieceService(pieceDao));
            Map<String, Object> model = new HashMap<>();
            BoardDto boardDto = new BoardDto(board);
            model.put("piecesDto", boardDto.getPieces());
            return render(model, "board.html");
        });

        get("/resume", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            PieceDaoFactory pieceDaoFactory = new PieceDaoFactory();
            PieceDao pieceDao = pieceDaoFactory.createPieceDao();
            Board board = boardService.resume(new PieceService(pieceDao));
            BoardDto boardDto = new BoardDto(board);
            model.put("piecesDto", boardDto.getPieces());
            return render(model, "board.html");
        });

        post("/move", (request, response) -> {
            String from = request.queryParams("from");
            String to = request.queryParams("to");
            MovingFlow movingFlow = MovingFlow.of(from, to);
            Board board;
            try {
                board = boardService.movePiece(movingFlow);
            } catch (RuntimeException e) {
                Map<String, Object> model = new HashMap<>();
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            if (board.isNotFinished()) {
                BoardDto boardDto = new BoardDto(board);
                Map<String, Object> model = new HashMap<>();
                model.put("piecesDto", boardDto.getPieces());
                return render(model, "board.html");
            }

            Result result = board.concludeResult();
            Map<String, Object> model = new HashMap<>();
            model.put("winner", result.getWinner());
            return render(model, "end.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
