package chess.controller;

import chess.domain.board.Board;
import chess.domain.dto.BoardDto;
import chess.domain.piece.service.PieceService;
import chess.domain.piece.state.PiecesState;
import chess.domain.piece.state.Result;
import chess.domain.position.MovingFlow;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {
    private final PieceService pieceService;

    public WebChessController(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    public void route() {
        get("/", (request, response) -> "Hello World");

        get("/start", (request, response) -> {
            PiecesState piecesState = pieceService.initialize();
            Board board = Board.of(piecesState);
            Map<String, Object> model = new HashMap<>();
            BoardDto boardDto = new BoardDto(board);
            model.put("piecesDto", boardDto.getPieces());
            return render(model, "board.html");
        });

        get("/resume", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            PiecesState piecesState = pieceService.getAll();
            Board board = Board.of(piecesState);
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
                PiecesState piecesState = pieceService.movePiece(movingFlow);
                board = Board.of(piecesState);
            } catch (RuntimeException e) {
                return renderError(e);
            }

            if (board.isNotFinished()) {
                return renderBoard(board);
            }

            return renderResult(board);
        });
    }

    private Object renderError(RuntimeException e) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", e.getMessage());
        return render(model, "error.html");
    }

    private Object renderResult(Board board) {
        Result result = board.concludeResult();
        Map<String, Object> model = new HashMap<>();
        model.put("winner", result.getWinner());
        return render(model, "end.html");
    }

    private Object renderBoard(Board board) {
        BoardDto boardDto = new BoardDto(board);
        Map<String, Object> model = new HashMap<>();
        model.put("piecesDto", boardDto.getPieces());
        return render(model, "board.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
