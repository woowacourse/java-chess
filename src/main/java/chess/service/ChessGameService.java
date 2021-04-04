package chess.service;

import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.Point;
import chess.dto.ChessGameDto;
import chess.dto.RequestDto;
import chess.dto.UserDto;
import com.google.gson.Gson;

import java.sql.SQLException;

public class ChessGameService {
    private static final Gson GSON = new Gson();

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private ChessGame chessGame;
    private UserDto userDto;

    public String getPiece(String point) {
        return GSON.toJson(chessGame.getBoard().get(Point.of(point)));
    }

    public int movePiece(String body) { // TODO: DB UPDATE
        RequestDto requestDto = GSON.fromJson(body, RequestDto.class);
        try {
            chessGame.playTurn(Point.of(requestDto.getSourcePoint()), Point.of(requestDto.getTargetPoint()));
            chessGameDao.updateGame(userDto.getUserId(), chessGame);
            if (!chessGame.isProgressing()) {
                return 0;
            }
            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 400;
        }
    }

    public void addUser(UserDto userDto) throws SQLException {
        chessGameDao.addUser(userDto);
    }

    public boolean login(UserDto userDto) throws SQLException {
        if (chessGameDao.findUser(userDto) != null) {
            this.userDto = userDto;
            this.chessGame = getGameByUserId(userDto);
            return true;
        }
        return false;
    }

    private ChessGame getGameByUserId(UserDto userDto) throws SQLException {
        ChessGameDto chessGameDto = chessGameDao.findGameByUserId(userDto);
        if (chessGameDto == null) {
            ChessGame chessGame = new ChessGame();
            chessGameDao.createNewGame(userDto, chessGame.getBoard());
            return chessGame;
        }
        return new ChessGame(chessGameDto.getBoard(), chessGameDto.getCurrentColor());
    }
}
