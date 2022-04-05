package chess.controller;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.controller.dto.ErrorDto;
import chess.controller.dto.GameStatusDto;
import chess.controller.dto.MoveDto;
import chess.controller.dto.ScoreDto;
import chess.domain.ChessGame;
import chess.domain.GameStatus;
import chess.domain.board.BoardGenerationStrategy;
import chess.domain.board.Result;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private final ChessGame chessGame;
    private final Gson gson = new Gson();

    public ChessWebController(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void start(BoardGenerationStrategy strategy) {

        port(8888);
        staticFileLocation("/static");
        get("/", (req, res) -> {
            Map<String, Piece> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            chessGame.startGame(strategy);
            return gson.toJson(GameStatusDto.of(chessGame));
        });

        post("/move", (req, res) -> {
            try {
                checkReady();
                MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
                chessGame.move(new Position(moveDto.getFrom()), new Position(moveDto.getTo()));
                return gson.toJson(GameStatusDto.of(chessGame));
            } catch (Exception e) {
                return gson.toJson(new ErrorDto(e.getMessage()));
            }
        });

        get("/status", (req, res) -> {
            try {
                checkReady();
                Result result = chessGame.createResult();
                return gson.toJson(ScoreDto.of(result));
            } catch (Exception e) {
                return gson.toJson(new ErrorDto(e.getMessage()));
            }
        });

        get("/end", (req, res) -> {
            try {
                checkReady();
                Result result = chessGame.stepGame();
                return gson.toJson(ScoreDto.of(result));
            } catch (Exception e) {
                return gson.toJson(new ErrorDto(e.getMessage()));
            }
        });
    }

    private static String render(Map<String, Piece> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void checkReady() {
        GameStatus gameStatus = chessGame.checkGameStatus();
        if (gameStatus.isReady()) {
            throw new IllegalArgumentException("체스 게임을 시작해야 합니다.");
        }
    }
}
