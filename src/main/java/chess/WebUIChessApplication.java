package chess;

import chess.controller.ChessController;
import chess.domain.dto.BoardDto;
import chess.domain.dto.MovablePositionDto;
import chess.domain.dto.MoveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFileLocation("public");
        ChessController chessController = new ChessController();
        ObjectMapper objectMapper = new ObjectMapper();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        /*get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGame chessGame = new ChessGame();
            chessGame.settingBoard();
            model.put("board", chessGame.getBoard());
            return render(model, "index.html");
        });*/

        get("/create", (req, res) -> {
            BoardDto boardDto = chessController.start();
            return objectMapper.writeValueAsString(boardDto);
        });

        post("/move", (req, res) -> {
            try {
                MoveRequestDto moveRequestDto = objectMapper.readValue(req.body(), MoveRequestDto.class);
                BoardDto boardDto =
                        chessController.move(moveRequestDto.getTarget(), moveRequestDto.getDestination());
                return objectMapper.writeValueAsString(boardDto);
            }
            catch (Exception e) {
                return objectMapper.writeValueAsString(chessController.boardDto());
            }
        });

        post("/movable", (req, res) -> {
            MovablePositionDto movablePositionDto = objectMapper.readValue(req.body(), MovablePositionDto.class);
            return objectMapper.writeValueAsString(chessController.movablePosition(movablePositionDto.getTarget()));
        });

        post("/score", (req, res) -> {
            return objectMapper.writeValueAsString(chessController.boardStatusDto());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
