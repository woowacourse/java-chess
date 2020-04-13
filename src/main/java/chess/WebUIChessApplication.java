package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardDAO;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.domain.result.GameResult;
import chess.exception.InvalidPositionException;
import chess.exception.PieceImpossibleMoveException;
import chess.exception.TakeTurnException;
import chess.service.BoardService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Board board = BoardFactory.createBoard();
    private static BoardDAO boardDAO = new BoardDAO();

    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            return render(BoardService.parseEmptyBoard(), "index.html");
        });

        post("/start", (req, res) -> {
            board.initialize();
            return render(BoardService.parseInitializedBoard(board, boardDAO), "index.html");
        });

        post("/end", (req, res) -> {
            board = BoardFactory.createBoard();
            return render(BoardService.createBoardModel(board), "index.html");
        });

        post("/load", (req, res) -> {
            board = BoardService.receiveLoadedBoard(boardDAO);
            return render(BoardService.parseLoadedBoard(boardDAO), "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = BoardService.parseLoadedBoard(boardDAO);
            try {
                board.move(req.queryParams("fromPiece"), req.queryParams("toPiece"));
                if (board.isFinished()) {
                    board.changeTurn();
                    res.redirect("/finish");
                }
                model = BoardService.receiveMovedBoard(board, boardDAO);
            }catch (InvalidPositionException | PieceImpossibleMoveException e) {
                res.redirect("/exception");
            } catch (TakeTurnException e) {
                model.put("turn", board.getCurrentTurn() + "의 차례입니다.");
            }
            return render(BoardService.receiveMovedBoard(board, boardDAO), "index.html");
        });

        get("/finish", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("turn", board.getCurrentTurn() + "가(이)");
            return render(model, "finish.html");
        });

        get("/exception", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "exception.html");
        });

        post("/status", (req, res) -> {
            GameResult gameResult = new GameResult();
            double blackScore = gameResult.calculateScore(board, Team.BLACK);
            double whiteScore = gameResult.calculateScore(board, Team.WHITE);
            if (blackScore == 0 && whiteScore == 0) {
                return render(BoardService.parseEmptyBoard(), "index.html");
            } else {
                Map<String, Object> model = BoardService.parseLoadedBoard(boardDAO);
                model.put("black", blackScore);
                model.put("white", whiteScore);
                model.put("turn", board.getCurrentTurn());
                return render(model, "index.html");
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
