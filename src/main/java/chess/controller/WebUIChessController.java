package chess.controller;

import chess.domain.board.ChessBoardFactory;
import chess.domain.command.Command;
import chess.domain.command.CommandFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.PiecesFactory;
import chess.domain.player.Player;
import chess.domain.player.Round;
import chess.domain.position.Position;
import chess.domain.state.State;
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

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessController {
    public static final Gson GSON = new Gson();
    public static final Command START = CommandFactory.initialCommand("start");
    public static final ChessRepositoryImpl CHESS_REPOSITORY = new ChessRepositoryImpl();
    public static final List<String> TURNS = Arrays.asList("white", "black");

    private static Round round = new Round(StateFactory.initialization(PiecesFactory.whitePieces()),
            StateFactory.initialization(PiecesFactory.blackPieces()), START);

    public void run() {
        get("/", (req, res) -> {
            Map<String, String> chessBoardFromDB = new LinkedHashMap<>();
            List<ChessRequestDto> pieces = CHESS_REPOSITORY.showAllPieces();
            for (ChessRequestDto piece : pieces) {
                chessBoardFromDB.put(piece.getPiecePosition(), piece.getPieceName());
            }

            CHESS_REPOSITORY.removeAllPieces();

            Map<Position, Piece> chessBoard = round.getBoard(ChessBoardFactory.loadBoard(chessBoardFromDB));
            Map<String, String> stringChessBoard = new LinkedHashMap<>();
            for (Map.Entry<Position, Piece> chessBoardEntry : chessBoard.entrySet()) {
                stringChessBoard.put(chessBoardEntry.getKey().toString(), chessBoardEntry.getValue().getPiece());
            }

            List<Piece> whitePieces = new ArrayList<>();
            List<Piece> blackPieces = new ArrayList<>();
            for (Map.Entry<Position, Piece> chessBoardEntry : chessBoard.entrySet()) {
                if (chessBoardEntry.getValue().isBlack()) {
                    blackPieces.add(chessBoardEntry.getValue());
                    continue;
                }
                whitePieces.add(chessBoardEntry.getValue());
            }

            CHESS_REPOSITORY.initializePieceStatus(stringChessBoard);

            String jsonFormatChessBoard = GSON.toJson(stringChessBoard);

            round = new Round(StateFactory.initialization(new Pieces(whitePieces)),
                    StateFactory.initialization(new Pieces(blackPieces)), START);

            List<TurnRequestDto> turns = CHESS_REPOSITORY.showCurrentTurn();
            String currentTurn = turns.stream()
                    .map(TurnRequestDto::getCurrentTurn)
                    .collect(Collectors.joining());

            if ("white".equals(currentTurn)) {
                Player white = round.getWhitePlayer();
                Player black = round.getBlackPlayer();
                State nextWhiteTurn = white.getState().toRunningTurn();
                State nextBlackTurn = black.getState().toFinishedTurn();
                white.changeState(nextWhiteTurn);
                black.changeState(nextBlackTurn);
            }
            if ("black".equals(currentTurn)) {
                Player white = round.getWhitePlayer();
                Player black = round.getBlackPlayer();
                State nextWhiteTurn = white.getState().toFinishedTurn();
                State nextBlackTurn = black.getState().toRunningTurn();
                white.changeState(nextWhiteTurn);
                black.changeState(nextBlackTurn);
            }

            Player whitePlayer = round.getWhitePlayer();
            Player blackPlayer = round.getBlackPlayer();

            double whiteScore = whitePlayer.calculateScore();
            double blackScore = blackPlayer.calculateScore();

            if (!(whitePlayer.getPieces().isKing() && blackPlayer.getPieces().isKing())) {
                round.changeToEnd();
            }

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

            round = new Round(StateFactory.initialization(PiecesFactory.whitePieces()),
                    StateFactory.initialization(PiecesFactory.blackPieces()), START);

            Map<Position, Piece> chessBoard = round.getBoard();
            Map<String, String> filteredChessBoard = new LinkedHashMap<>();
            for (Map.Entry<Position, Piece> chessBoardEntry : chessBoard.entrySet()) {
                if (chessBoardEntry.getValue() != null) {
                    filteredChessBoard.put(chessBoardEntry.getKey().toString(),
                            chessBoardEntry.getValue().getPiece());
                }
            }

            CHESS_REPOSITORY.initializePieceStatus(filteredChessBoard);
            CHESS_REPOSITORY.initializeTurn();

            res.redirect("/");

            return null;
        });
    }

    private static String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
