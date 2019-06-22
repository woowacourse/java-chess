package chess;

import java.util.HashMap;
import java.util.Map;

import chess.dao.BoardDAO;
import chess.dao.ResultCounterDAO;
import chess.dao.TurnDAO;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.ResultCounter;
import chess.domain.Team;
import chess.domain.Turn;
import chess.dto.BoardDTO;
import chess.dto.ResultCounterDTO;
import chess.dto.TurnDTO;
import chess.utils.DataParser;
import chess.utils.QueryParser;
import chess.view.JsonTransformer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
//    private static ChessBoard chessBoard;

    private static BoardDTO boardDTO;
    private static TurnDTO turnDTO;
    private static ResultCounterDTO resultCounterDTO;

    public static void main(String[] args) {
        staticFiles.location("/assets");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        // 게임을 처음 시작할 때 진입점
        get("/game_play", (req, res) -> {
            BoardDAO.deleteAll();
            TurnDAO.deleteAll();
            ResultCounterDAO.deleteAll();

            boardDTO = new BoardDTO();
            turnDTO = new TurnDTO();
            resultCounterDTO = new ResultCounterDTO();

            ChessBoard chessBoard = new ChessBoard();
            Map<String, Object> model = new HashMap<>();

            boardDTO.setBoard(chessBoard.getBoard());
            turnDTO.setTurn(chessBoard.getTurn());
            resultCounterDTO.setResultCounter(chessBoard.getResultCounter());

            BoardDAO.add(boardDTO);
            TurnDAO.add(turnDTO);
            ResultCounterDAO.add(resultCounterDTO);

            model.put("turn", chessBoard.getTurn());
            return render(model, "game_play.html");
        });

        // 계속할 때 진입점
        get("/game_continue", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("turn", TurnDAO.selectLastTurn().getTurn());
            return render(model, "game_play.html");
        });

        post("/game_play", (req, res) -> {
            Position source = QueryParser.position(req.queryParams("source"));
            Position target = QueryParser.position(req.queryParams("target"));

            boardDTO = BoardDAO.selectAll();
            turnDTO = TurnDAO.selectLastTurn();
            resultCounterDTO = ResultCounterDAO.selectAll();

            ChessBoard chessBoard = new ChessBoard(boardDTO.getBoard(), turnDTO.getTurn(), resultCounterDTO.getResultCounter());

            boolean isKingDead = chessBoard.move(source, target);

            boardDTO.setBoard(chessBoard.getBoard());
            turnDTO.setTurn(chessBoard.getTurn());
            resultCounterDTO.setResultCounter(chessBoard.getResultCounter());

            BoardDAO.afterMove(boardDTO);
            TurnDAO.afterMove(turnDTO);
            ResultCounterDAO.afterMove(resultCounterDTO);

            if (isKingDead) {
                Map<String, Object> model = new HashMap<>();

                Double whiteScore = chessBoard.totalScore(Team.WHITE);
                Double blackScore = chessBoard.totalScore(Team.BLACK);

                Turn winner = chessBoard.getWinner();

                model.put("message", "왕이 잡혔습니다.");
                model.put("winner", winner);
                model.put("whiteScore", whiteScore);
                model.put("blackScore", blackScore);
                return render(model, "result.html");
            }
            Map<String, Object> model = new HashMap<>();
            model.put("turn", chessBoard.getTurn());
            return render(model, "game_play.html");
        });

        post("/status", (req, res) -> {
            boardDTO = BoardDAO.selectAll();
            turnDTO = TurnDAO.selectLastTurn();
            resultCounterDTO = ResultCounterDAO.selectAll();

            ChessBoard chessBoard = new ChessBoard(boardDTO.getBoard(), turnDTO.getTurn(), resultCounterDTO.getResultCounter());

            return DataParser.board(chessBoard.getBoard());
        }, new JsonTransformer());

        get("/result", (req, res) -> {
            boardDTO = BoardDAO.selectAll();
            turnDTO = TurnDAO.selectLastTurn();
            resultCounterDTO = ResultCounterDAO.selectAll();

            ChessBoard chessBoard = new ChessBoard(boardDTO.getBoard(), turnDTO.getTurn(), resultCounterDTO.getResultCounter());

            Double whiteScore = chessBoard.totalScore(Team.WHITE);
            Double blackScore = chessBoard.totalScore(Team.BLACK);

            String winner = getWinner(whiteScore, blackScore);

            HashMap<String, Object> model = new HashMap<>();
            model.put("whiteScore", whiteScore);
            model.put("blackScore", blackScore);
            model.put("winner", winner);
            return render(model, "result.html");
        });


        get("/end", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            BoardDAO.deleteAll();
            TurnDAO.deleteAll();
            ResultCounterDAO.deleteAll();
            return render(model, "end.html");
        });

        exception(RuntimeException.class, (e, req, res) -> {

            res.status(404);
            res.body(
                 "<h1> 에러 발생 </h1>" +
                 "<div>" + e.getMessage() + "</div>" +
                 "<button onclick=\"window.history.back()\">되돌아가기</button>"
            );
        });
    }

    private static String getWinner(Double whiteScore, Double blackScore) {
        String winner = "";
        if (whiteScore > blackScore) {
            winner = "WHITE";
        }
        if (whiteScore > blackScore) {
            winner = "BLACK";
        }
        if (whiteScore == blackScore) {
            winner = "NONE";
        }
        return winner;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
