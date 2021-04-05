package chess;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.ChessController;
import chess.dao.ChessDAOImpl;
import chess.service.ChessServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessController chessController = new ChessController(new ChessServiceImpl(new ChessDAOImpl()));
        ObjectMapper objectMapper = new ObjectMapper();

        get("/", (req, res) -> {
            res.redirect("index.html");
            return null;
        });

        get("/:gameId/pieces", (req, res) -> {
            String gameId = req.params(":gameId");
            return objectMapper
                .writeValueAsString(chessController.startGame(Long.parseLong(gameId)));
        });

        get("/:gameId/roundstatus", (req, res) -> {
            String gameId = req.params(":gameId");
            return objectMapper
                .writeValueAsString(chessController.roundStatus(Long.parseLong(gameId)));
        });

        post("/:gameId/move", (req, res) -> {
            Map<String, String> request = objectMapper.readValue(req.body(), Map.class);
            String gameId = req.params(":gameId");
            String currentPosition = request.get("currentPosition");
            String targetPosition = request.get("targetPosition");
            chessController.move(Long.parseLong(gameId), currentPosition, targetPosition);
            return objectMapper
                .writeValueAsString(chessController.roundStatus(Long.parseLong(gameId)));
        });

        get("/:gameId/restart", (req, res) -> {
            String gameId = req.params(":gameId");
            chessController.restart(Long.parseLong(gameId));
            return true;
        });

        delete("/exit/:gameId", (req, res) -> {
            String gameId = req.params(":gameId");
            chessController.exitGame(Long.parseLong(gameId));
            return true;
        });

        post("/save/:gameId", (req, res) -> {
            String gameId = req.params(":gameId");
            chessController.saveGame(Long.parseLong(gameId));
            return true;
        });
    }
}
