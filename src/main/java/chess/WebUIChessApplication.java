package chess;

import chess.domain.ChessBoard;
import chess.domain.dto.ChessStatusDto;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static ChessBoard chessBoard = ChessBoard.generate();
    public static void main(String[] args) {

        staticFileLocation("static");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/board", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            ChessStatusDto chessStatusDto = new ChessStatusDto(chessBoard.getChessBoard(),
                    Color.WHITE,
                    chessBoard.sumScoreByColor(Color.BLACK),
                    chessBoard.sumScoreByColor(Color.WHITE));
            String jsonString = mapper.writeValueAsString(chessStatusDto);
            return jsonString;
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
