package chess.web;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.game.state.position.Position;
import chess.web.dto.BoardDto;
import chess.web.dto.PieceDto;
import chess.web.utils.Request;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");
        ChessGame chessGame = new ChessGame();
        BoardDto boardDto = BoardDto.of(new HashMap<>());

        get("/", (req, res) -> {
            Map<String, PieceDto> pieces = getStringPieceDtoMap(boardDto);
            Map<String, Object> model = new HashMap<>();
            initializeRowPieces(pieces, model);
            return render(model, "/index.html");
        });

        get("/start", (req, res) -> {
            boardDto.setBoard(chessGame.start());
            res.redirect("/");
            return null;
        });

        post("/move", (req, res) -> {
            boardDto.setBoard(Request.move(chessGame, req.queryParams("command")));
            res.redirect("/");
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static void initializeRowPieces(Map<String, PieceDto> pieces, Map<String, Object> model) {
        for (int i = 8; i > 0; i--) { // 8 ~ 1
            List<PieceDto> pieceDtos = getPieceDtos(pieces, i);
            model.put("pieces" + i, pieceDtos);
        }
    }

    private static List<PieceDto> getPieceDtos(Map<String, PieceDto> pieces, int i) {
        List<PieceDto> pieceDtos = new ArrayList<>();
        for (char c = 'a'; c <= 'h'; c++) { // a ~ h
            String position = c + String.valueOf(i);
            if (pieces.containsKey(position)) {
                pieceDtos.add(pieces.get(position));
                continue;
            }
            pieceDtos.add(PieceDto.of(position, ""));
        }
        return pieceDtos;
    }

    private static Map<String, PieceDto> getStringPieceDtoMap(BoardDto boardDto) {
        Map<String, PieceDto> pieces = new HashMap<>();
        for (Position position : boardDto.getBoard().keySet()) {
            String positionStr = position.getFile().name() + position.getRank().getValue();
            pieces.put(positionStr, PieceDto.of(position, boardDto.get(position)));
        }
        return pieces;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
