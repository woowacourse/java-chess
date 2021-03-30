package chess.service;

import chess.domain.ChessGame;
import chess.domain.Point;
import com.google.gson.Gson;

public class ChessGameService {
    ChessGame chessGame = new ChessGame();

    public String getPiece(String point) {
        Gson gson = new Gson();
        return gson.toJson(chessGame.getBoard().get(Point.of(point)));
    }
}
