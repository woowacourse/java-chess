package chess;

import chess.domain.command.Command;
import chess.domain.command.CommandFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.player.Round;
import chess.domain.position.Position;
import chess.domain.state.StateFactory;
import chess.dto.MoveRequestDto;
import chess.repository.ChessRepositoryImpl;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static final Gson GSON = new Gson();
    public static final ChessRepositoryImpl CHESS_REPOSITORY = new ChessRepositoryImpl();

    public static Command command = CommandFactory.initialCommand("start");
    public static Round round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
            StateFactory.initialization(PieceFactory.blackPieces()), command);

    public static void main(String[] args) {
        staticFileLocation("/static");

        get("/", (req, res) -> {
            CHESS_REPOSITORY.removeAllPieces();
            CHESS_REPOSITORY.removeTurn();

            Map<Position, Piece> chessBoard = round.getBoard();
            Map<String, String> filteredChessBoard = new HashMap<>();
            for (Map.Entry<Position, Piece> chessBoardStatus : chessBoard.entrySet()) {
                if (chessBoardStatus.getValue() != null) {
                    filteredChessBoard.put(chessBoardStatus.getKey().toString(), chessBoardStatus.getValue().getPiece());
                }
            }
            CHESS_REPOSITORY.initializePieceStatus(filteredChessBoard);
            CHESS_REPOSITORY.initializeTurn();
            
            String jsonFormatChessBoard = GSON.toJson(filteredChessBoard);

            double whiteScore = round.getWhitePlayer().calculateScore();
            double blackScore = round.getBlackPlayer().calculateScore();

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

            res.type("application/json");

            try {
                round.execute(commands);
            } catch (RuntimeException runtimeException) {
                return "{\"status\":\"500\", \"message\":\"" + runtimeException.getMessage() + "\"}";
            }
            return "{\"status\":\"200\", \"message\":\"성공\"}";
        });

        get("/reset", (req, res) -> {
            round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
                    StateFactory.initialization(PieceFactory.blackPieces()), command);

            res.redirect("/");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
