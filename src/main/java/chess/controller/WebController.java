package chess.controller;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.Command;
import chess.domain.ChessGame;
import chess.domain.Status;
import chess.domain.piece.Piece;
import chess.domain.position.Square;
import chess.dto.ScoreDTO;
import chess.dto.SquareAndPiece;
import chess.view.InputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private static final String ERROR_GAME_NOT_START_YET = "[ERROR]게임부터 시작하지지?!";
    private static final String ERROR_GAME_IS_OVER = "[ERROR] 지금은 못 움직여!";
    private static final String ERROR_GAME_IS_NOT_END = "[ERROR]아직 게임 안끝났어!";
    private static final String RESULT_FORMAT = "%s : %.1f점%n";

    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private ChessGame game;

    public void run() {
        port(8080);
        staticFileLocation("/templates");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/command", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            inGame(req.queryParams("command"), model);
            return render(model, "startedChess.html");
        });
    }

    private void inGame(String input, Map<String, Object> model) {
        try {
            packBoardInfo(model);
            executeCommand(InputView.requireCommand(input), model);
        } catch (IllegalArgumentException e) {
            model.put("error", e);
        }
    }

    private void packBoardInfo(Map<String, Object> model) {
        if (game == null) {
            return;
        }
        Map<Square, Piece> pieceMap = game.getBoard();
        List<SquareAndPiece> infos = new ArrayList<>();
        for (Square square : pieceMap.keySet()) {
            infos.add(SquareAndPiece.of(square, pieceMap.get(square)));
        }
        model.put("infos", infos);
    }

    private void executeCommand(Map.Entry<Command, List<Square>> commands, Map<String, Object> model) {
        Command command = commands.getKey();
        if (command == Command.START) {
            start(model);
        }

        if (command == Command.MOVE) {
            move(commands, model);
        }

        if (command == Command.END) {
            model.put("exit", true);
            game = null;
        }

        if (command == Command.STATUS) {
            status(model);
        }
    }

    private void start(Map<String, Object> model) {
        if (game != null) {
            throw new IllegalArgumentException(ERROR_GAME_IS_NOT_END);
        }
        game = new ChessGame();
        packBoardInfo(model);
    }

    private void move(Map.Entry<Command, List<Square>> commands, Map<String, Object> model) {
        checkGameStarted();
        if (game.isKingDie()) {
            throw new IllegalArgumentException(ERROR_GAME_IS_OVER);
        }

        List<Square> squares = commands.getValue();
        game.move(squares.get(SOURCE_INDEX), squares.get(TARGET_INDEX));
        packBoardInfo(model);
        checkKingDieAfterMove(model);
    }

    private void checkKingDieAfterMove(Map<String, Object> model) {
        if (game.isKingDie()) {
            model.put("kingDie", true);
        }
    }

    private void status(Map<String, Object> model) {
        checkGameStarted();
        if (!game.isKingDie()) {
            throw new IllegalArgumentException(ERROR_GAME_IS_NOT_END);
        }
        Status status = game.saveStatus();
        model.put("score", ScoreDTO.of(status));
        model.put("exit", true);
        game = null;
    }

    private void checkGameStarted() {
        if (game == null) {
            throw new IllegalArgumentException(ERROR_GAME_NOT_START_YET);
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
