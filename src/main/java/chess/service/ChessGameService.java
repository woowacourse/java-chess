package chess.service;

import chess.domain.ChessGame;
import chess.domain.Point;
import chess.dto.RequestDto;
import com.google.gson.Gson;

public class ChessGameService {
    ChessGame chessGame = new ChessGame();
    Gson gson = new Gson();

    public String getPiece(String point) {
        return gson.toJson(chessGame.getBoard().get(Point.of(point)));
    }

    public int movePiece(String body) {
        RequestDto requestDto = gson.fromJson(body, RequestDto.class);
        try {
            chessGame.playTurn(Point.of(requestDto.getSourcePoint()), Point.of(requestDto.getTargetPoint()));
            if (!chessGame.isProgressing()) {
                return 0;
            }
            return 200;
        } catch (Exception e) {
            System.out.println("@@@@@" + e.getMessage());
            return 400;
        }
    }
}
