package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.Square;
import chess.domain.TeamScore;
import chess.domain.Winner;
import chess.domain.piece.Color;
import chess.dto.MoveStateDTO;
import chess.service.MoveStateService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController implements Controller {

    private ChessBoard chessBoard = new ChessBoard("id", Color.WHITE);
    private MoveStateService moveStateService = new MoveStateService();
    private Gson gson = new Gson();

    @Override
    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/continue", (req, res) -> continueGame(res));


        get("/status", (req, res) -> calculateGameStatus(res));

        get("/refresh", (req, res) -> restartGame(res));

        movePiece();

    }

    private void movePiece() {
        post("/move", (req, res) -> {
            try {
                chessBoard.getMoveState()
                        .move(Square.of(req.queryParams("before")), Square.of(req.queryParams("after")), chessBoard);
                if (chessBoard.isKingCaptured()) {
                    Color turn = chessBoard.getTurn().getTurn();
                    turn = turn.changeColor(turn);
                    throw new UnsupportedOperationException(turn.getName() + "이(가) 승리했습니다. " + " 다시 시작하기 버튼을 눌러 새로 시작해주세요.");
                }
            } catch (Exception e) {
                res.status(400);
                return e.getMessage();
            }
            MoveStateDTO moveStateDTO = new MoveStateDTO(chessBoard.getMoveState());
            moveStateService.addMoveState(moveStateDTO);
            return req.queryParams("before") + " " + req.queryParams("after");
        });
    }

    private Object restartGame(Response res) {
        try {
            MoveStateDTO moveStateDTO = new MoveStateDTO(chessBoard.getMoveState());
            moveStateService.deleteMoveStates(moveStateDTO);
            res.redirect("/");
            chessBoard = new ChessBoard("id", Color.WHITE);
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
            Map<String, String> moveStates = moveStateService.searchMoveHistory("id");
            StringBuilder moveTrack = new StringBuilder();
            chessBoard = new ChessBoard("id", Color.WHITE);
            for (Map.Entry<String, String> moveState : moveStates.entrySet()) {
                moveTrack.append(moveState.getKey()).append(" ").append(moveState.getValue()).append(" ");
                chessBoard.getMoveState()
                        .move(Square.of(moveState.getKey()), Square.of(moveState.getValue()), chessBoard);
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
