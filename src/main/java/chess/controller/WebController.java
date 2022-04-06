package chess.controller;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.Command;
import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.dao.DBConnectorGenerator;
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
    private static final String ERROR_MESSAGE_IMPOSSIBLE_COMMAND = "[ERROR] 지금은 앙댕! 혼난다??\n";
    private static final String ERROR_GAME_NOT_START_YET = "[ERROR]게임부터 시작하지지?!";
    private static final String ERROR_GAME_IS_OVER = "[ERROR] 지금은 못 움직여!";
    private static final String ERROR_GAME_IS_NOT_END = "[ERROR]아직 게임 안끝났어!";

    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;
    public static final int BOARD_ID = 1;

    private ChessGame game;
    private ChessGameDao chessGameDao = new ChessGameDao();

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
            save();
        }

        if (command == Command.MOVE) {
            move(commands, model);
            update();
        }

        if (command == Command.END) {
            model.put("exit", true);
            remove();
            game = null;
        }

        if (command == Command.STATUS) {
            status(model);
            remove();
        }

        if (command == Command.CONTINUE) {
            continueGame(model);
            save();
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

    private void continueGame(Map<String, Object> model) {
        if (game != null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }
        game = chessGameDao.findById(BOARD_ID, DBConnectorGenerator.getConnection());

        if (game == null) {
            throw new IllegalArgumentException("[ERROR] 저장된 게임이 없습니다");
        }
        packBoardInfo(model);
    }

    private void checkGameStarted() {
        if (game == null) {
            throw new IllegalArgumentException(ERROR_GAME_NOT_START_YET);
        }
    }

    private void save() {
        chessGameDao.remove(BOARD_ID, DBConnectorGenerator.getConnection());
        chessGameDao.save(game, BOARD_ID, DBConnectorGenerator.getConnection());
        new BoardDao().save(game.getBoard(), BOARD_ID, DBConnectorGenerator.getConnection());
    }

    private void update() {
        chessGameDao.update(game, BOARD_ID, DBConnectorGenerator.getConnection());
        BoardDao boardDao = new BoardDao();
        boardDao.remove(BOARD_ID, DBConnectorGenerator.getConnection());
        boardDao.save(game.getBoard(), BOARD_ID, DBConnectorGenerator.getConnection());
    }

    private void remove() {
        chessGameDao.remove(BOARD_ID, DBConnectorGenerator.getConnection());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
