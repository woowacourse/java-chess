package chess.controller;

import chess.dao.ChessGameDAO;
import chess.domain.board.Position;
import chess.domain.game.Result;
import chess.domain.game.WebChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.dto.PositionDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.Map;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

public class WebController {

    private static final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private static final Gson gson = new Gson();

    public int newGame(Request req, Response res) throws SQLException {
        WebChessGame chessGame = new WebChessGame();
        chessGameDAO.addGame(chessGame);
        return chessGameDAO.recentGame();
    }

    public JsonArray loadGame(Request req, Response res) throws SQLException {
        int gameId = Integer.parseInt(req.params("id"));
        WebChessGame chessGame = chessGameDAO.loadGame(gameId);
        JsonArray chessBoardArray = new JsonArray();
        for (Map.Entry<Position, Piece> board : chessGame.getChessBoardMap().entrySet()) {
            chessBoardArray.add(boardToJSON(board));
        }
        return chessBoardArray;
    }

    public JsonObject move(Request req, Response res) throws SQLException {
        int gameId = Integer.parseInt(req.params("id"));
        PositionDTO positionDTO = gson.fromJson(res.body(), PositionDTO.class);
        WebChessGame chessGame = chessGameDAO.loadGame(gameId);
        if (chessGame.moved(positionDTO.getSource(), positionDTO.getTarget())) {
            JsonObject response = new JsonObject();
            JsonObject movedSource = boardToJSON(positionDTO.getSource(),
                chessGame.getPiece(positionDTO.getSource()));
            JsonObject movedTarget = boardToJSON(positionDTO.getTarget(),
                chessGame.getPiece(positionDTO.getTarget()));
            response.add("source", movedSource);
            response.add("target", movedTarget);

            response.addProperty("isOver", chessGame.isOver());

            chessGameDAO.saveGame(gameId, chessGame);
            return response;
        }
        return new JsonObject();
    }

    public String turn(Request req, Response res) throws SQLException {
        Color turn = chessGameDAO.turn(Integer.parseInt(req.params("id")));
        return gson.toJson(turn);
    }

    public JsonObject result(Request req, Response res) throws SQLException {
        int gameId = Integer.parseInt(req.params("id"));
        WebChessGame chessGame = chessGameDAO.loadGame(gameId);
        Result result = chessGame.getResult();
        JsonObject response = new JsonObject();
        response.add("black", resultToJson(result, Color.BLACK));
        response.add("white", resultToJson(result, Color.WHITE));
        return response;
    }

    public int finish(Request req, Response res) {
        chessGameDAO.finish(Integer.parseInt(req.params("id")));
        return HttpStatus.ACCEPTED_202;
    }

    public boolean finished(Request req, Response res) throws SQLException {
        return chessGameDAO.finished(Integer.parseInt(req.params("id")));
    }

    private static JsonObject boardToJSON(Position movedPosition, Piece movedPiece) {
        JsonObject square = new JsonObject();
        square.addProperty("id", movedPosition.getStringPosition());
        square.addProperty("position", movedPosition.getStringPosition());

        JsonObject piece = new JsonObject();
        piece.addProperty("type", movedPiece.getName());
        piece.addProperty("color", movedPiece.getColorAsString());

        square.add("piece", piece);
        return square;
    }

    private static JsonObject boardToJSON(Map.Entry<Position, Piece> board) {
        return boardToJSON(board.getKey(), board.getValue());
    }

    private static JsonObject resultToJson(Result result, Color color) {
        JsonObject colorResult = new JsonObject();
        colorResult.addProperty("score", result.getScore(color));
        colorResult.addProperty("outcome", result.winOrLose(color));
        return colorResult;
    }
}
