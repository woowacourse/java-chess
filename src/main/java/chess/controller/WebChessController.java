package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.TeamScore;
import chess.domain.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.service.ChessBoardService;
import chess.service.TurnService;
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

    public static void run(ChessBoardService chessBoardService, TurnService turnService) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/onGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessBoard chessBoard = chessBoardService.load();
            Turn turn = turnService.load();
            return processGame(model, chessBoard, turn);
        });

        get("/initialize", (req, res) -> {
            chessBoardService.initialize();
            turnService.initialize();
            res.redirect("/onGame");
            return null;
        });

        get("/retrieve", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessBoard chessBoard = new ChessBoard();
            Turn turn = Turn.WHITE;
            try {
                chessBoard = chessBoardService.load();
                turn = turnService.load();
            } catch (Exception e) {
                notification = "저장된 게임이 없습니다";
            }
            return processGame(model, chessBoard, turn);
        });

        post("/move", (req, res) -> {
            ChessBoard chessBoard = chessBoardService.load();
            Turn turn = turnService.load();

            List<Square> sourceDestination =
                    Arrays.asList(Square.of(req.queryParams("source")), Square.of(req.queryParams("destination")));
            if (chessBoard.canMove(sourceDestination, turn)) {
                chessBoard.movePiece(sourceDestination);
                turn = turn.getOppositeTurn();
                notification = "";
            } else {
                notification = "움직일 수 없는 위치입니다";
            }
            chessBoardService.saveBoard(chessBoard);
            turnService.save(turn);
            if (chessBoard.isKingCaptured()) {
                res.redirect("/endGame");
            }
            res.redirect("/onGame");
            return null;
        });

        post("/save", (req, res) -> {
            ChessBoard chessBoard = chessBoardService.load();
            chessBoardService.saveBoard(chessBoard);

            Turn turn = turnService.load();
            turnService.save(turn);

            res.redirect("/");
            return null;
        });

        get("/endGame", (req, res) -> {
            ChessBoard chessBoard = chessBoardService.load();
            TeamScore teamScore = new TeamScore();
            teamScore.updateTeamScore(chessBoard);
            Map<String, Object> model = new HashMap<>();
            Map<Square, Piece> board = chessBoard.getChessBoard();
            Map<String, Piece> boardView = new HashMap<>();
            for (Square square : board.keySet()) {
                boardView.put(square.toString(), board.get(square));
            }
            String winners = teamScore.getWinners().stream()
                    .map(Enum::toString)
                    .collect(Collectors.joining(","));
            model.put("chessBoard", boardView);
            model.put("notification", notification);
            model.put("winners", winners);
            return render(model, "endGame.html");
        });
    }

    private static Object processGame(Map<String, Object> model, ChessBoard chessBoard, Turn turn) {
        Map<Square, Piece> board = chessBoard.getChessBoard();
        Map<String, Piece> boardView = new HashMap<>();
        TeamScore teamScore = new TeamScore();
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
