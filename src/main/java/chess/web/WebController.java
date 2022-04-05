package chess.web;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.property.Color;
import chess.web.dto.PieceDto;
import chess.web.service.ChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    private static final int MAX_RANK = 8;
    private static final int MIN_RANK = 1;
    private static final char MIN_FILE = 'a';
    private static final char MAX_FILE = 'h';

    private final ChessService chessService = new ChessService();

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<PieceDto> pieces = chessService.initializeData();
            initializeRowPieces(pieces, model);

            if (!pieces.isEmpty()) {
                Map<Color, Double> status = chessService.getStatus();
                model.put("blackScore", status.get(Color.Black));
                model.put("WhiteScore", status.get(Color.White));
            }
            return render(model, "/index.html");
        });

        get("/start", (req, res) -> {
            chessService.start();
            res.redirect("/");
            return null;
        });

        post("/move", (req, res) -> {
            // boardDto.setBoard(Request.move(chessGame, req.queryParams("command")));
            res.redirect("/");
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private void initializeRowPieces(List<PieceDto> pieces, Map<String, Object> model) {
        for (int i = MAX_RANK; i >= MIN_RANK; i--) {
            List<PieceDto> columnPieces = initializeColumnPieces(pieces, i);
            model.put("pieces" + i, columnPieces);
        }
    }

    private List<PieceDto> initializeColumnPieces(List<PieceDto> pieces, int rank) {
        List<PieceDto> columnPieces = new ArrayList<>();

        for (char currentFile = MIN_FILE; currentFile <= MAX_FILE; currentFile++) {
            String position = currentFile + String.valueOf(rank);
            PieceDto pieceDto = pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .orElse(PieceDto.of(position, "", ""));
            columnPieces.add(pieceDto);
        }
        return columnPieces;
    }
}
