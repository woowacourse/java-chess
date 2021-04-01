package chess;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.webdao.ChessGameDAO;
import chess.webdto.ChessGameDTO;
import chess.webdto.MoveRequestDTO;
import chess.webdto.PieceDTOFormat;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static ChessGame chessGame;
    private static Team whiteTeam;
    private static Team blackTeam;
    private static final ChessGameDAO CHESS_GAME_DAO = new ChessGameDAO();

    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/startNewGame", (req, res) -> {
            Gson gson = new Gson();
            CHESS_GAME_DAO.deleteChessGameDB();
            whiteTeam = Team.whiteTeam();
            blackTeam = Team.blackTeam();
            chessGame = new ChessGame(blackTeam, whiteTeam);
            CHESS_GAME_DAO.createChessGame(chessGame, convertCurrentTurnTeamToString());
            return gson.toJson(generateChessGameDTO());
        });

        get("/loadPrevGame", (req, res) -> {
            Gson gson = new Gson();
            chessGame = CHESS_GAME_DAO.readChessGame();
            whiteTeam = chessGame.getWhiteTeam();
            blackTeam = chessGame.getBlackTeam();
            return gson.toJson(generateChessGameDTO());
        });

        post("/move", (req, res) -> {
            Gson gson = new Gson();
            final MoveRequestDTO moveRequestDTO = gson.fromJson(req.body(), MoveRequestDTO.class);
            final String start = moveRequestDTO.getStart();
            final String destination = moveRequestDTO.getDestination();
            try {
                chessGame.move(Position.of(start), Position.of(destination));
                CHESS_GAME_DAO.deleteChessGameDB();
                CHESS_GAME_DAO.createChessGame(chessGame, convertCurrentTurnTeamToString());
                res.status(200);
                return gson.toJson(generateChessGameDTO());
            } catch (Exception e) {
                res.status(404);
                return gson.toJson(generateChessGameDTO());
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static ChessGameDTO generateChessGameDTO() {
        final Map<String, Map<String, String>> piecePositionByTeam = piecePositionByTeam(chessGame);
        final String currentTurnTeam = convertCurrentTurnTeamToString();
        final double whiteTeamScore = chessGame.calculateWhiteTeamScore();
        final double blackTeamScore = chessGame.calculateBlackTeamScore();
        final boolean isPlaying = chessGame.isPlaying();
        return new ChessGameDTO(piecePositionByTeam, currentTurnTeam, whiteTeamScore, blackTeamScore, isPlaying);
    }

    private static Map<String, Map<String, String>> piecePositionByTeam(final ChessGame chessGame) {
        final Map<String, Map<String, String>> piecePosition = new HashMap<>();
        piecePosition.put("white", piecePositionOfSingleTeam(chessGame.currentWhitePiecePosition()));
        piecePosition.put("black", piecePositionOfSingleTeam(chessGame.currentBlackPiecePosition()));
        return piecePosition;
    }

    private static Map<String, String> piecePositionOfSingleTeam(final Map<Position, Piece> piecePosition) {
        final Map<String, String> piecePositionConverted = new HashMap<>();
        for (Position position : piecePosition.keySet()) {
            final String positionInitial = position.getPositionInitial();
            final Piece chosenPiece = piecePosition.get(position);
            final String pieceString = PieceDTOFormat.convert(chosenPiece);
            piecePositionConverted.put(positionInitial, pieceString);
        }
        return piecePositionConverted;
    }

    private static String convertCurrentTurnTeamToString() {
        final Team currentTurnTeam = chessGame.getCurrentTurnTeam();
        if (currentTurnTeam.equals(whiteTeam)) {
            return "white";
        }
        return "black";
    }
}
