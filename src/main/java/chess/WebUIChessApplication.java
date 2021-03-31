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

        post("/move", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(req.body(), new TypeReference<Map<String, String>>() {});
            String sourceValue = map.get("source");
            String targetValue = map.get("target");
            Position source = Position.of(sourceValue.charAt(0), sourceValue.charAt(1));
            Position target = Position.of(targetValue.charAt(0), targetValue.charAt(1));
            chessBoard.movePiece(source, target);
            ChessStatusDto chessStatusDto = new ChessStatusDto(chessBoard.getChessBoard(),
                    Color.from(map.get("turn")).reverse(),
                    chessBoard.sumScoreByColor(Color.BLACK),
                    chessBoard.sumScoreByColor(Color.WHITE));
            return mapper.writeValueAsString(chessStatusDto);
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
