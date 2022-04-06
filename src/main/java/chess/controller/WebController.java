package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.chessboard.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.command.GameCommand;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.domain.position.Position;
import chess.service.BoardService;
import chess.service.GameService;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private ChessGame chessGame;

    private final BoardService boardService = new BoardService(new BoardDao());
    private final GameService gameService = new GameService(new GameDao());

    public void run() {
        chessGame = initChessGame();

        renderMain();
        renderStart();
        renderMove();
        renderStatus();
        renderEnd();
    }

    private ChessGame initChessGame() {
        Map<Position, Piece> board = boardService.loadBoard();
        if (board.isEmpty()) {
            return new ChessGame(new ChessBoard(new NormalPiecesGenerator()));
        }
        return new ChessGame(gameService.loadState(), new ChessBoard(board));
    }

    private void renderMain() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.putAll(boardService.findBoard());

            if (chessGame.isEndGameByPiece()) {
                model.put("winner", chessGame.getWinner() + " 승리!!");
                gameService.delete();
                chessGame = initChessGame();
                return render(model, "index.html");
            }
            return render(model, "index.html");
        });
    }

    private void renderStart() {
        get("/start", (req, res) -> {
            try {
                chessGame.playGameByCommand(new GameCommand("start"));
            } catch (Exception e) {
                return renderErrorMessage(e.getMessage());
            }

            Map<String, Object> model = new HashMap<>();

            gameService.saveGame(chessGame);
            boardService.saveBoard(chessGame, gameService.findGameId());

            model.putAll(boardService.findBoard());

            return render(model, "index.html");
        });
    }

    private void renderMove() {
        post("/move", (req, res) -> {
            final String from = req.queryParams("from").toLowerCase(Locale.ROOT);
            final String to = req.queryParams("to").toLowerCase(Locale.ROOT);

            try {
                chessGame.playGameByCommand(new GameCommand("move", from, to));
            } catch (Exception e) {
                return renderErrorMessage(e.getMessage());
            }
            gameService.update(chessGame);
            boardService.update(chessGame, from);
            boardService.update(chessGame, to);

            res.redirect("/");
            return null;
        });
    }

    private void renderStatus() {
        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            try {
                chessGame.playGameByCommand(new GameCommand("status"));
            } catch (Exception e) {
                return renderErrorMessage(e.getMessage());
            }

            model.putAll(boardService.findBoard());

            double whiteScore = chessGame.calculateScore(Color.WHITE);
            double blackScore = chessGame.calculateScore(Color.BLACK);
            model.put("white", "WHITE : " + whiteScore);
            model.put("black", "BLACK : " + blackScore);

            return render(model, "index.html");
        });
    }

    private void renderEnd() {
        get("/end", (req, res) -> {
            try {
                chessGame.playGameByCommand(new GameCommand("end"));
            } catch (Exception e) {
                return renderErrorMessage(e.getMessage());
            }
            gameService.delete();
            chessGame = initChessGame();

            res.redirect("/");
            return null;
        });
    }

    private String renderErrorMessage(String errorMessage) {
        Map<String, Object> model = new HashMap<>();
        model.putAll(boardService.findBoard());
        model.put("error", errorMessage);
        return render(model, "index.html");
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
