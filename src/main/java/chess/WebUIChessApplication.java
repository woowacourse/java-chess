package chess;

import chess.domain.ChessBoard;
import chess.domain.Team;
import chess.domain.exceptions.ChessPlayException;
import chess.domain.piece.Piece;
import chess.domain.utils.InputParser;
import chess.dto.ResultDto;
import chess.service.ChessBoardService;
import chess.view.JsonTransformer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static ChessBoard chessBoard;

    public static void main(String[] args) {
        staticFiles.location("/assets");
        ChessBoardService chessBoardService = new ChessBoardService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game_play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessBoard = chessBoardService.gameStart();
            return render(model, "game_play.html");
        });

        post("/game_play", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            Map<String, Object> model = new HashMap<>();

            Piece targetPiece = chessBoard.move(InputParser.position(source), InputParser.position(target));

            if (targetPiece != null && targetPiece.isKing()) {
                Team winner = chessBoard.getWinner();
                res.redirect("/result?winner=" + winner.name());
            }

            ResultDto resultDto = null;
            if (targetPiece != null) {
                resultDto = new ResultDto();
                resultDto.setName(targetPiece.getName());
                resultDto.setTeam(targetPiece.getEnemy().name());
            }
            Optional<ResultDto> optionalResultDto = Optional.ofNullable(resultDto);

            chessBoardService.move(chessBoard.boardToDto(), chessBoard.turnToDto(), optionalResultDto);
            return render(model, "game_play.html");
        });

        post("/status", (req, res) -> chessBoard.boardToDto(), new JsonTransformer());

        get("/result", (req, res) -> {
            String winner = req.queryParams("winner");
            Double whiteScore = chessBoard.totalScore(Team.WHITE);
            Double blackScore = chessBoard.totalScore(Team.BLACK);
            HashMap<String, Object> model = new HashMap<>();

            if (winner != null) {
                model.put("message", "왕이 잡혔습니다.");
                model.put("winner", winner);
            }
            model.put("whiteScore", whiteScore);
            model.put("blackScore", blackScore);

            chessBoardService.gameEnd();
            return render(model, "result.html");
        });

        exception(ChessPlayException.class, (e, req, res) -> res.redirect("/game_play"));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
