package chess;

import chess.domain.board.Board;

import chess.domain.dao.PieceDAO;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.PiecesDto;
import chess.domain.game.ChessGame;
import chess.domain.piece.MovePositionInfo;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    private static ChessGame chessGame;

    public static void main(String[] args) {
        chessGame = new ChessGame(new Board(PieceFactory.createPieces()));
        Gson gson = new Gson();

        staticFiles.location("/static");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            if (!chessGame.isReady()) {
                return gson.toJson(new ChessBoardDto("false", new PiecesDto(chessGame.getBoard().getPieces()), "이미 게임이 실행되었습니다.", ""));
            }
            chessGame.start();
            return gson.toJson(new ChessBoardDto("true", new PiecesDto(chessGame.getBoard().getPieces()), "", chessGame.getStatus()));
        });

        post("/move", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            MovePositionInfo mpi = mapper.readValue(req.body(), MovePositionInfo.class);

            try {
                chessGame.move(new Position(mpi.getSource().split("")),
                        new Position(mpi.getTarget().split("")));
            } catch (Exception e) {
                return gson.toJson(new ChessBoardDto("false", new PiecesDto(chessGame.getBoard().getPieces()), "이동할 수 없습니다.", chessGame.getStatus()));
            }
            if (chessGame.isFinished()) {
                return gson.toJson(new ChessBoardDto("end", new PiecesDto(chessGame.getBoard().getPieces()), "왕을 잡아 게임이 종료됩니다.", chessGame.getStatus()));
            }

            return gson.toJson(new ChessBoardDto("true", new PiecesDto(chessGame.getBoard().getPieces()), "", chessGame.getStatus()));
        });

        post("/end", (req, res) -> {
            if (chessGame.isFinished()) {
                return gson.toJson(new ChessBoardDto("false", new PiecesDto(chessGame.getBoard().getPieces()), "이미 게임이 종료되었습니다.", chessGame.getStatus()));
            }
            chessGame.end();
            String resultTeam = "무승부 입니다.";
            if (chessGame.getBlackScore() > chessGame.getWhiteScore()) {
                resultTeam = "블랙팀 승";
            }
            if (chessGame.getWhiteScore() > chessGame.getBlackScore()) {
                resultTeam = "화이트팀 승";
            }

            String result = "블랙팀 : " + chessGame.getBlackScore() + " 화이트팀 : " + chessGame.getWhiteScore() + "\n";
            String json = gson.toJson(new ChessBoardDto("true", new PiecesDto(chessGame.getBoard().getPieces()), result + resultTeam, chessGame.getStatus()));
            chessGame.ready();
            return json;

        });

        post("/status", (req, res) -> {
            if (chessGame == null || chessGame.isReady() || chessGame.isFinished()) {
                return gson.toJson(new ChessBoardDto("false", null, "게임을 실행시켜주세요.", chessGame.getStatus()));
            }
            String result = "블랙팀 : " + chessGame.getBlackScore() + " 화이트팀 : " + chessGame.getWhiteScore();
            return gson.toJson(new ChessBoardDto("true", new PiecesDto(chessGame.getBoard().getPieces()), result, chessGame.getStatus()));
        });

        post("/save", (req, res) -> {
            PieceDAO pieceDAO = new PieceDAO();
            if (pieceDAO.saveAll(chessGame.getBoard().getPieces()) && pieceDAO.saveTurn(chessGame.getStatus())) {
                return gson.toJson(new ChessBoardDto("true", new PiecesDto(chessGame.getBoard().getPieces()), "게임이 저장되었습니다.", chessGame.getStatus()));
            }
            return gson.toJson(new ChessBoardDto("false", new PiecesDto(chessGame.getBoard().getPieces()), "저장에 실패하였습니다. ", chessGame.getStatus()));

        });

        post("/load", (req, res) -> {
            PieceDAO pieceDAO = new PieceDAO();
            if (pieceDAO.loadPieces() == null) {
                return gson.toJson(new ChessBoardDto("false", new PiecesDto(chessGame.getBoard().getPieces()), "저장된 게임이 없습니다. ", chessGame.getStatus()));
            }
            chessGame = new ChessGame(pieceDAO.loadPieces());
            chessGame.changeState(pieceDAO.loadTurn(chessGame));

            return gson.toJson(new ChessBoardDto("true", new PiecesDto(chessGame.getBoard().getPieces()), "저장된 게임을 불러왔습니다.", chessGame.getStatus()));
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
