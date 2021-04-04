package chess;

import chess.domain.command.Command;
import chess.domain.command.CommandFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.player.Round;
import chess.domain.position.Position;
import chess.domain.state.StateFactory;
import chess.dto.ChessRequestDto;
import chess.dto.MoveRequestDto;
import chess.dto.TurnChangeRequestDto;
import chess.dto.TurnRequestDto;
import chess.repository.ChessRepositoryImpl;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static final Gson GSON = new Gson();
    public static final Command START = CommandFactory.initialCommand("start");
    public static final ChessRepositoryImpl CHESS_REPOSITORY = new ChessRepositoryImpl();
    public static final List<String> TURNS = Arrays.asList("white", "black");

    private static Round round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
            StateFactory.initialization(PieceFactory.blackPieces()), START);

    public static void main(String[] args) {
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, String> chessBoardFromDB = new LinkedHashMap<>();
            List<ChessRequestDto> pieces = CHESS_REPOSITORY.showAllPieces();
            for (ChessRequestDto piece : pieces) {
                chessBoardFromDB.put(piece.getPiecePosition(), piece.getPieceName());
            }
            String jsonFormatChessBoard = GSON.toJson(chessBoardFromDB);

            List<TurnRequestDto> turns = CHESS_REPOSITORY.showCurrentTurn();
            String currentTurn = turns.stream()
                    .map(TurnRequestDto::getCurrentTurn)
                    .collect(Collectors.joining());

            double whiteScore = round.getWhitePlayer().calculateScore();
            double blackScore = round.getBlackPlayer().calculateScore();

            Map<String, Object> model = new HashMap<>();
            model.put("jsonFormatChessBoard", jsonFormatChessBoard);
            model.put("currentTurn", currentTurn);
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

            CHESS_REPOSITORY.removePiece(moveRequestDto);
            CHESS_REPOSITORY.movePiece(moveRequestDto);

            List<TurnRequestDto> turns = CHESS_REPOSITORY.showCurrentTurn();
            String currentTurn = turns.stream()
                    .map(TurnRequestDto::getCurrentTurn)
                    .collect(Collectors.joining());
            String nextTurn = TURNS.stream()
                    .filter(t -> !t.equals(currentTurn))
                    .collect(Collectors.joining());
            CHESS_REPOSITORY.changeTurn(new TurnChangeRequestDto(currentTurn, nextTurn));

            return "{\"status\":\"200\", \"message\":\"성공\"}";
        });

        get("/reset", (req, res) -> {
            CHESS_REPOSITORY.removeAllPieces();
            CHESS_REPOSITORY.removeTurn();

            round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
                    StateFactory.initialization(PieceFactory.blackPieces()), START);

            Map<Position, Piece> chessBoard = round.getBoard();
            Map<String, String> filteredChessBoard = new LinkedHashMap<>();
            for (Map.Entry<Position, Piece> chessBoardStatus : chessBoard.entrySet()) {
                if (chessBoardStatus.getValue() != null) {
                    filteredChessBoard.put(chessBoardStatus.getKey().toString(),
                            chessBoardStatus.getValue().getPiece());
                }
            }

            CHESS_REPOSITORY.initializePieceStatus(filteredChessBoard);
            CHESS_REPOSITORY.initializeTurn();

            res.redirect("/");

            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
