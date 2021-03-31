package chess;

import chess.domain.command.Command;
import chess.domain.command.CommandFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.player.Round;
import chess.domain.position.Position;
import chess.domain.state.StateFactory;
import chess.dto.MoveRequestDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static final Gson GSON = new Gson();

    public static Command command = CommandFactory.initialCommand("start");
    public static final Round ROUND = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
            StateFactory.initialization(PieceFactory.blackPieces()), command);

    public static void main(String[] args) {
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<Position, Piece> chessBoard = ROUND.getBoard();

            Map<String, String> filteredChessBoard = new HashMap<>();
            for (Map.Entry<Position, Piece> chessBoardStatus : chessBoard.entrySet()) {
                if (chessBoardStatus.getValue() != null) {
                    filteredChessBoard.put(chessBoardStatus.getKey().toString(), chessBoardStatus.getValue().getPiece());
                }
            }
            
            String jsonFormatChessBoard = GSON.toJson(filteredChessBoard);

            double whiteScore = ROUND.getWhitePlayer().calculateScore();
            double blackScore = ROUND.getBlackPlayer().calculateScore();

            Map<String, Object> model = new HashMap<>();
            model.put("jsonFormatChessBoard", jsonFormatChessBoard);
            model.put("whiteScore", whiteScore);
            model.put("blackScore", blackScore);

            return render(model, "chess.html");
        });

        post("/move", (req, res) -> {
            MoveRequestDto moveRequestDto = GSON.fromJson(req.body(), MoveRequestDto.class);
            Queue<String> commands =
                    new ArrayDeque<>(Arrays.asList("move", moveRequestDto.getSource(), moveRequestDto.getTarget()));

            ROUND.execute(commands);

            res.type("application/json");
            return "{\"message\":\"200\"}";
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
