package chess.controller;

import chess.dao.ChessLogDao;
import chess.dto.BoardDto;
import chess.dto.MovablePositionDto;
import chess.dto.MoveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {
    public void run() {
        Spark.staticFileLocation("public");
        ChessController chessController = new ChessController();
        ObjectMapper objectMapper = new ObjectMapper();
        ChessLogDao chessLogDao = new ChessLogDao();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("roomId", req.queryParams("room"));
            return render(model, "index.html");
        });

        get("/create/:room", (req, res) -> {
            String roomNumber = req.params(":room");
            List<String> commands = chessLogDao.applyCommand(roomNumber);
            BoardDto boardDto = chessController.start(commands);
            return objectMapper.writeValueAsString(boardDto);
        });

        post("/move", (req, res) -> {
            try {
                MoveRequestDto moveRequestDto = objectMapper.readValue(req.body(), MoveRequestDto.class);
                BoardDto boardDto =
                        chessController.move(moveRequestDto.getTarget(), moveRequestDto.getDestination());
                chessLogDao.addLog(
                        moveRequestDto.getRoomId(), moveRequestDto.getTarget(), moveRequestDto.getDestination());
                return objectMapper.writeValueAsString(boardDto);
            } catch (Exception e) {
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

        get("/clear/:room", (req, res) -> {
            String roomNumber = req.params(":room");
            chessLogDao.deleteLog(roomNumber);
            res.redirect("/");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
