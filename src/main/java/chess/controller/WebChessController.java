package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.TeamScore;
import chess.domain.Winner;
import chess.domain.piece.Color;
import chess.dto.ChessBoardDTO;
import chess.dto.MoveStateDTO;
import chess.service.ChessBoardService;
import chess.service.MoveStateService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController implements Controller {


    private ChessBoard chessBoard = new ChessBoard("id", Color.WHITE);
    private ChessBoardService chessBoardService = new ChessBoardService();
    private MoveStateService moveStateService = new MoveStateService();

    @Override
    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/status", (req, res) -> {
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
        });

        get("/refresh", (req, res) -> {
            try {
                ChessBoardDTO chessBoardDTO = new ChessBoardDTO(chessBoard);
                chessBoardService.deleteChessBoard(chessBoardDTO);
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
        });

        post("/move", (req, res) -> {
            try {
                String moveStatement = "move" + " " + req.queryParams("before") + " " + req.queryParams("after");
                chessBoard.getMoveState().move(moveStatement, chessBoard);
                MoveStateDTO moveStateDTO = new MoveStateDTO(chessBoard.getMoveState());
                if (chessBoard.isKingCaptured()) {
                    Color turn = chessBoard.getTurn().getTurn();
                    turn = turn.changeColor(turn);
                    throw new UnsupportedOperationException(turn.getName() + "이(가) 승리했습니다. " + " 다시 시작하기 버튼을 눌러 새로 시작해주세요.");
                }
                moveStateService.addMoveState(moveStateDTO);

                ChessBoardDTO chessBoardDTO = new ChessBoardDTO(chessBoard);
                chessBoardService.updateChessBoard(chessBoardDTO);
                return req.queryParams("before") + " " + req.queryParams("after");
            } catch (Exception e) {
                res.status(400);
                return e.getMessage();
            }
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
