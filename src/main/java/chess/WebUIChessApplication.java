package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.ChessController;
import chess.domain.ChessGameImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessController chessController = new ChessController(ChessGameImpl.initialGame());
        ObjectMapper objectMapper = new ObjectMapper();

        get("/", (req, res) -> {
            res.redirect("index.html");;
            return null;
        });

        get("/pieces", (req, res) -> objectMapper.writeValueAsString(chessController.startGame()));

        get("/roundstatus", (req, res) -> objectMapper.writeValueAsString(chessController.movablePositions()));

        post("/move", (req, res) -> {
            Map<String, String> request = objectMapper.readValue(req.body(), Map.class);
            String currentPosition = request.get("currentPosition");
            String targetPosition = request.get("targetPosition");
            chessController.move(currentPosition, targetPosition);
            return objectMapper.writeValueAsString(chessController.movablePositions());
        });
    }
}
