package chess;

import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("notification", "테스트 안내 입니다.");
            model.put("whiteScore", 28);
            model.put("blackScore", 28);
            Map<Position, Piece> pieces = PieceFactory.getInstance().getPieces();
            List<String> positions = pieces.keySet().stream()
                    .map(Position::toString)
                    .collect(Collectors.toList());
            for(String string : positions) {
                model.put(string,pieces.get(new Position(string)));
            }
            return render(model, "index.html");
        });

        post("/move", (req, res) -> null );
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
