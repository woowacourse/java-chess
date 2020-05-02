package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.TeamScore;
import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.service.ChessBoardService;
import chess.service.ChessGameService;
import chess.service.TurnService;
import chess.service.dto.MovingRequest;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {
    public static String notification;

    private ChessBoardService chessBoardService;
    private TurnService turnService;
    private ChessGameService chessGameService;

    public WebChessController(ChessBoardService chessBoardService, TurnService turnService, ChessGameService chessGameService) {
        this.chessBoardService = chessBoardService;
        this.turnService = turnService;
        this.chessGameService = chessGameService;
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/onGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return processGame(model);
        });

        get("/initialize", (req, res) -> {
            chessGameService.initialize();
            res.redirect("/onGame");
            return null;
        });

        get("/retrieve", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Turn turn = Turn.WHITE;
            try {
                turn = turnService.loadTurn();
            } catch (Exception e) {
                notification = "저장된 게임이 없습니다. 새로운 게임을 시작합니다.";
            }
            return processGame(model);
        });

        post("/move", (req, res) -> {
            ChessBoard chessBoard = chessBoardService.loadBoard();
            MovingRequest movingRequest = new MovingRequest(req.queryParams("source"), req.queryParams("destination"));

            notification = chessBoardService.movePiece(movingRequest);
            if (chessBoard.isKingCaptured()) {
                res.redirect("/endGame");
            }
            res.redirect("/onGame");
            return null;
        });

        post("/save", (req, res) -> {
            ChessBoard chessBoard = chessBoardService.loadBoard();
            chessBoardService.saveBoard(chessBoard);

            Turn turn = turnService.loadTurn();
            turnService.save(turn);

            res.redirect("/");
            return null;
        });

        get("/endGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("chessBoard", chessBoardService.getBoardView());
            model.put("winners", chessBoardService.getWinners());
            return render(model, "endGame.html");
        });
    }

    private Object processGame(Map<String, Object> model) {
        model.put("chessBoard", chessBoardService.getBoardView());
        model.put("notification", notification);
        model.put("teamScore", chessBoardService.getTeamScoreView());
        return render(model, "onGame.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
