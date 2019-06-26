package chess;

import chess.dto.ChessBoardDto;
import chess.dto.ChessPositionDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            req.session().attribute("roundId", 0);
            return render(model, "index.html");
        });

        get("/chessBoard", ((request, response) -> {
            Gson gson = new Gson();
            int roundId = request.session().attribute("roundId");

            return gson.toJson(ChessService.getInstance().getChessBoardDTO(roundId));
        }));

        post("/chessBoard", ((request, response) -> {
            Gson gson = new Gson();
            ChessPositionDto chessPositionDto = gson.fromJson(request.body(), ChessPositionDto.class);

            System.out.println(chessPositionDto);
            int roundId = request.session().attribute("roundId");

            try {
                ChessBoardDto chessBoardDTO = ChessService.getInstance().getChessBoard(chessPositionDto, roundId);
                System.out.println(chessBoardDTO);
                return gson.toJson(chessBoardDTO);
            } catch (IllegalArgumentException e) {
                return "error";
            }
        }));

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
