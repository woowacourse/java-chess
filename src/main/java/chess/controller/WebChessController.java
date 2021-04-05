package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.webdao.ChessGameDAO;
import chess.webdto.ChessGameDTO;
import chess.webdto.DBConnectionDTO;
import chess.webdto.MoveRequestDTO;
import chess.webdto.PieceDTOFormat;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static chess.controller.HTTPStatusCode.*;
import static spark.Spark.*;

public class WebChessController {
    private static final String WHITE_TEAM_FORMAT = "white";
    private static final String BLACK_TEAM_FORMAT = "black";

    private ChessGame chessGame;
    private Team whiteTeam;
    private Team blackTeam;
    private final ChessGameDAO chessGameDAO;

    public WebChessController(final ChessGameDAO chessGameDAO) {
        this.chessGameDAO = chessGameDAO;
    }

    public void run() {
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/startNewGame", (req, res) -> {
            Gson gson = new Gson();
            try {
                chessGameDAO.deleteChessGame();
                whiteTeam = Team.whiteTeam();
                blackTeam = Team.blackTeam();
                chessGame = new ChessGame(blackTeam, whiteTeam);
                chessGameDAO.createChessGame(chessGame, currentTurnTeamToString());
                res.status(SUCCESS.statusCode());
                return gson.toJson(generateChessGameDTO());
            } catch (SQLException e) {
                res.status(INTERNAL_SERVER_ERROR.statusCode());
                return gson.toJson(DBConnectionDTO.fail());
            }
        });

        get("/loadPrevGame", (req, res) -> {
            Gson gson = new Gson();
            try {
                chessGame = chessGameDAO.readChessGame();
                whiteTeam = chessGame.getWhiteTeam();
                blackTeam = chessGame.getBlackTeam();
                res.status(SUCCESS.statusCode());
                return gson.toJson(generateChessGameDTO());
            } catch (SQLException e) {
                res.status(INTERNAL_SERVER_ERROR.statusCode());
                return gson.toJson(DBConnectionDTO.fail());
            }
        });

        get("/save", (req, res) -> {
            Gson gson = new Gson();
            try {
                chessGameDAO.deleteChessGame();
                chessGameDAO.createChessGame(chessGame, currentTurnTeamToString());
                res.status(SUCCESS.statusCode());
                return gson.toJson(DBConnectionDTO.success());
            } catch (SQLException e) {
                res.status(INTERNAL_SERVER_ERROR.statusCode());
                return gson.toJson(DBConnectionDTO.fail());
            }
        });

        post("/move", (req, res) -> {
            Gson gson = new Gson();
            final MoveRequestDTO moveRequestDTO = gson.fromJson(req.body(), MoveRequestDTO.class);
            final String start = moveRequestDTO.getStart();
            final String destination = moveRequestDTO.getDestination();
            try {
                chessGame.move(Position.of(start), Position.of(destination));
                res.status(SUCCESS.statusCode());
                return gson.toJson(generateChessGameDTO());
            } catch (IllegalArgumentException e) {
                res.status(BAD_REQUEST.statusCode());
                return gson.toJson(generateChessGameDTO());
            }
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private ChessGameDTO generateChessGameDTO() {
        final Map<String, Map<String, String>> piecePositionToString = generatePiecePositionToString();
        final String currentTurnTeam = currentTurnTeamToString();
        final double whiteTeamScore = chessGame.calculateWhiteTeamScore();
        final double blackTeamScore = chessGame.calculateBlackTeamScore();
        final boolean isPlaying = chessGame.isPlaying();
        return new ChessGameDTO(piecePositionToString, currentTurnTeam, whiteTeamScore, blackTeamScore, isPlaying);
    }

    private Map<String, Map<String, String>> generatePiecePositionToString() {
        final Map<String, Map<String, String>> piecePosition = new HashMap<>();
        piecePosition.put(WHITE_TEAM_FORMAT, generatePiecePositionByTeamToString(chessGame.currentWhitePiecePosition()));
        piecePosition.put(BLACK_TEAM_FORMAT, generatePiecePositionByTeamToString(chessGame.currentBlackPiecePosition()));
        return piecePosition;
    }

    private Map<String, String> generatePiecePositionByTeamToString(final Map<Position, Piece> piecePosition) {
        final Map<String, String> piecePositionConverted = new HashMap<>();
        for (Position position : piecePosition.keySet()) {
            final String positionInitial = position.getPositionInitial();
            final Piece chosenPiece = piecePosition.get(position);
            final String pieceString = PieceDTOFormat.convert(chosenPiece);
            piecePositionConverted.put(positionInitial, pieceString);
        }
        return piecePositionConverted;
    }

    private String currentTurnTeamToString() {
        final Team currentTurnTeam = chessGame.getCurrentTurnTeam();
        if (currentTurnTeam.equals(whiteTeam)) {
            return WHITE_TEAM_FORMAT;
        }
        return BLACK_TEAM_FORMAT;
    }
}
