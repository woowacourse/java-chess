package chess;

import chess.persistence.dto.ChessBoardDTO;
import chess.persistence.dto.ChessGameDTO;
import chess.persistence.dto.ChessMoveDTO;
import chess.persistence.dto.ErrorDTO;
import chess.service.BoardGeneratorService;
import chess.service.ChessGameStatusService;
import chess.service.GameGeneratorService;
import chess.service.PieceMoveService;
import chess.util.JsonTransformer;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/chessGame", (req, res) -> {
            try {
                ChessGameDTO chessGameDTO = GameGeneratorService.getInstance().request();
                ChessBoardDTO chessBoardDTO = BoardGeneratorService.getInstance().request(chessGameDTO);
                return ChessGameStatusService.getInstance().request(chessGameDTO, chessBoardDTO);
            } catch (IllegalArgumentException e) {
                return new ErrorDTO(e.getMessage());
            }
        }, new JsonTransformer());

        post("/chessGame", "application/json", (req, res) -> {
            Gson gson = new Gson();
            try {
                ChessMoveDTO chessMoveDTO = gson.fromJson(req.body(), ChessMoveDTO.class);
                ChessGameDTO chessGameDTO = GameGeneratorService.getInstance().request();
                ChessBoardDTO chessBoardDTO = BoardGeneratorService.getInstance().request(chessGameDTO);
                ChessBoardDTO newChessBoardDTO = PieceMoveService.getInstance().request(chessMoveDTO, chessGameDTO, chessBoardDTO);
                return ChessGameStatusService.getInstance().request(chessGameDTO, newChessBoardDTO);
            } catch (IllegalArgumentException e) {
                return new ErrorDTO(e.getMessage());
            }
        }, new JsonTransformer());

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
