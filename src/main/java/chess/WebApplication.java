package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import chess.domain.piece.ChessmenInitializer;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.dto.BoardDto;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {


    public static void main(String[] args) {
        port(8082);
        staticFiles.location("/static");


        get("/", (req, res) -> {
            Map<String, Object> model = generateBoard();

            return render(model, "index.html");});
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static Map<String, Object> generateBoard() {
        Map<String, Object> model = new HashMap<>();

        ChessmenInitializer chessmenInitializer = new ChessmenInitializer();

        Pieces chessmen = chessmenInitializer.init();

        for (Piece piece: chessmen.getPieces()) {
            BoardDto boardDto = new BoardDto(piece.getPosition(), piece.getName(), piece.getColor());
            model.put(boardDto.getPosition(), boardDto);
        }

        return model;
    }


}
