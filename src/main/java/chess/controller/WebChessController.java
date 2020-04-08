package chess.controller;

import chess.dao.ChessBoardDAO;
import chess.domain.ChessBoard;
import chess.domain.TeamScore;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
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
    public static ChessBoard chessBoard = new ChessBoard();
    public static boolean blackTurn = false;
    public static String notification;
    public static TeamScore teamScore;
    public static ChessBoardDAO chessBoardDAO = ChessBoardDAO.getInstance();

    public static void run() {

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/onGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return processGame(model);
        });

        get("/initialize", (req, res) -> {
            chessBoard = new ChessBoard();
            res.redirect("/onGame");
            return null;
        });

        get("/retrieve", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                chessBoard = chessBoardDAO.retrieve();
            } catch (Exception e) {
                notification = "저장된 게임이 없습니다";
            }
            return processGame(model);
        });

        post("/move", (req, res) -> {
            List<Square> sourceDestination = Arrays.asList(Square.of(req.queryParams("source")), Square.of(req.queryParams("destination")));
            if (chessBoard.canMove(sourceDestination, blackTurn)) {
                chessBoard.movePiece(sourceDestination);
                blackTurn = !blackTurn;
                notification = "";
            } else {
                notification = "움직일 수 없는 위치입니다";
            }
            if (chessBoard.isKingCaptured()) {
                res.redirect("/endGame");
            }
            res.redirect("/onGame");
            return null;
        });

        post("/save", (req, res) -> {
            chessBoardDAO.save(chessBoard);
            res.redirect("/");
            return null;
        });

        get("/endGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<Square, Piece> board = chessBoard.getChessBoard();
            Map<String, Piece> boardView = new HashMap<>();
            for (Square square : board.keySet()) {
                boardView.put(square.toString(), board.get(square));
            }
            String winners = teamScore.getWinners().stream().map(t -> t.toString()).collect(Collectors.joining(","));
            model.put("chessBoard", boardView);
            model.put("notification", notification);
            model.put("winners", winners);
            return render(model, "endGame.html");
        });
    }

    private static Object processGame(Map<String, Object> model) {
        Map<Square, Piece> board = chessBoard.getChessBoard();
        Map<String, Piece> boardView = new HashMap<>();
        teamScore = new TeamScore();
        teamScore.updateTeamScore(chessBoard);
        Map<String, Double> teamScoreView = new HashMap<>();
        for (Square square : board.keySet()) {
            boardView.put(square.toString(), board.get(square));
        }
        for (Color color : teamScore.getTeamScore().keySet()) {
            teamScoreView.put(color.toString(), teamScore.getTeamScore().get(color));
        }
        model.put("chessBoard", boardView);
        model.put("notification", notification);
        model.put("teamScore", teamScoreView);
        return render(model, "onGame.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
