package chess.service;

import chess.User;
import chess.dao.ChessGameDao;
import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Point;
import chess.dto.ChessGameDto;
import chess.dto.RequestDto;
import com.google.gson.Gson;

import java.sql.SQLException;

public class ChessGameService {
    Gson gson = new Gson();
    ChessGameDao chessGameDao = new ChessGameDao();
    private ChessGame chessGame;
    private User user;

    public String getPiece(String point) {
        return gson.toJson(chessGame.getBoard().get(Point.of(point)));
    }

    public int movePiece(String body) { // TODO: DB UPDATE
        RequestDto requestDto = gson.fromJson(body, RequestDto.class);
        try {
            chessGame.playTurn(Point.of(requestDto.getSourcePoint()), Point.of(requestDto.getTargetPoint()));
            chessGameDao.updateGame(user.getUserId(), chessGame);
            if (!chessGame.isProgressing()) {
                return 0;
            }
            return 200;
        } catch (Exception e) {
            System.out.println("@@@@@" + e.getMessage());
            return 400;
        }
    }

    public void addUser(String userId) throws SQLException {
        chessGameDao.addUser(userId);
    }

    public boolean login(String userId) throws SQLException {
        if (chessGameDao.findUserById(userId) != null) {
            this.chessGame = getGameByUserId(userId);
            this.user = chessGameDao.findUserById(userId);
            return true;
        }
        return false;
    }

    private ChessGame getGameByUserId(String userId) throws SQLException {
        ChessGameDto chessGameDto = chessGameDao.findGameByUserId(userId);
        if (chessGameDto == null) {
            ChessGame chessGame = new ChessGame();
            chessGameDao.createNewGame(userId, chessGame.getBoard());
            return chessGame;
        }
        return new ChessGame(chessGameDto.getBoard(), chessGameDto.getCurrentColor());
    }
}
