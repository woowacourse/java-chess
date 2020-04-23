package chess.controller;

import chess.domain.*;
import chess.domain.piece.Color;
import chess.dto.MoveStateDTO;
import chess.service.MoveStateService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController implements Controller {

    private ChessBoard chessBoard;
    private MoveState moveState;
    private MoveStateService moveStateService = new MoveStateService();
    private Gson gson = new Gson();

    @Override
    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "login.html");
        });

        get("/login", (req, res) -> {
            try {
                String id = req.queryParams("id");
                chessBoard = new ChessBoard(Color.WHITE);
                moveState = new MoveState(id);
                return "정상적으로 로그인되었습니다.";
            } catch (Exception e) {
                return e.getMessage();
            }
        });

        get("/game", (req, res) -> {
            System.out.println(moveState.getPlayer().getPlayerId() + ">>>>>>>>>>>>>>>>>>>>>>>");
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game/id", (req, res) -> moveState.getPlayer().getPlayerId());

        post("/game/continue", (req, res) -> continueGame(res));

        get("/game/status", (req, res) -> calculateGameStatus(res));

        get("/game/refresh", (req, res) -> restartGame(res));

        post("/game/move", this::movePiece);

    }


    private Object movePiece(Request req, Response res) throws SQLException {
        try {
            moveState
                    .move(Square.of(req.queryParams("before")), Square.of(req.queryParams("after")), chessBoard);
            if (chessBoard.isKingCaptured()) {
                Color turn = chessBoard.getTurn().getTurn();
                turn = turn.changeColor(turn);
                return turn.getName();
            }
        } catch (Exception e) {
            res.status(400);
            return e.getMessage();
        }
        MoveStateDTO moveStateDTO = new MoveStateDTO(moveState);
        moveStateService.addMoveState(moveStateDTO);
        return req.queryParams("before") + " " + req.queryParams("after");
    }


    private Object restartGame(Response res) {
        try {
            MoveStateDTO moveStateDTO = new MoveStateDTO(moveState);
            moveStateService.deleteMoveStates(moveStateDTO);
            res.redirect("/game");
            String id = moveState.getPlayer().getPlayerId();
            chessBoard = new ChessBoard(Color.WHITE);
            moveState = new MoveState(id);
            externalStaticFileLocation("/templates");
            return null;
        } catch (Exception e) {
            res.status(400);
            return e.getMessage();
        }
    }

    private Object calculateGameStatus(Response res) {
        try {
            Map<Color, Double> scores = TeamScore.calculateTeamScore(chessBoard.getChessBoard());
            double black = scores.get(Color.BLACK);
            double white = scores.get(Color.WHITE);
            StringBuilder win = new StringBuilder();
            for (Color winner : Winner.getWinners(chessBoard.getChessBoard())) {
                win.append(winner.getName());
                win.append(" ");
            }
            return black + " " + white + " " + win;

        } catch (Exception e) {
            res.status(400);
            return e.getMessage();
        }
    }

    private Object continueGame(Response res) {
        try {
            String playerId = moveState.getPlayer().getPlayerId();
            Map<String, String> states = moveStateService.searchMoveHistory(playerId);
            StringBuilder moveTrack = new StringBuilder();
            chessBoard = new ChessBoard(Color.WHITE);
            moveState = new MoveState(playerId);
            for (Map.Entry<String, String> state : states.entrySet()) {
                moveTrack.append(state.getKey()).append(" ").append(state.getValue()).append(" ");
                moveState.move(Square.of(state.getKey()), Square.of(state.getValue()), chessBoard);
            }
            return gson.toJson(moveTrack.toString());

        } catch (Exception e) {
            res.status(400);
            return e.getMessage();
        }
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
