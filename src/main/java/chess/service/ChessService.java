package chess.service;

import static chess.controller.WebChessController.*;
import static chess.domain.Status.*;
import static chess.domain.piece.Color.*;

import java.sql.SQLException;
import java.util.HashMap;

import chess.dao.ChessDao;
import chess.dao.SQLConnection;
import chess.domain.ChessGame;
import chess.domain.Status;
import chess.dto.RequestDto;
import chess.dto.UserDto;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.dto.BoardDto;

public class ChessService {
    private final ChessDao chessDAO;

    public ChessService() {
        chessDAO = new ChessDao(new SQLConnection());
    }

    public ChessGame restartChess(UserDto userDto) throws SQLException {
        String userId = chessDAO.findUserIdByUser(userDto);
        chessDAO.deleteBoard(userId);
        return new ChessGame();
    }

    public ChessGame matchBoard(UserDto userDto) throws SQLException {
        String userId = chessDAO.findUserIdByUser(userDto);
        BoardDto boardDto = chessDAO.findBoard(userId);
        return makeChessGame(boardDto);
    }

    public String matchBoardImageSource(ChessGame chessGame, String requestBody) {
        return chessGame.getBoard().get(Point.of(requestBody)).getImage();
    }

    public String matchPieceName(ChessGame chessGame, String requestBody) {
        return chessGame.getBoard().get(Point.of(requestBody)).getName();
    }

    private ChessGame makeChessGame(BoardDto boardDto) {
        if (boardDto == null) {
            return new ChessGame();
        }
        return new ChessGame(boardDto.getBoard());
    }

    public void addBoard(ChessGame chessGame, UserDto userDto, String requestBody) throws SQLException {
        chessDAO.addBoard(chessDAO.findUserIdByUser(userDto), requestBody, chessGame.nextTurn());
    }

    public String makeNextColor(ChessGame chessGame) {
        if (chessGame.nextTurn().isSameAs(BLACK)) {
            return WHITE.name();
        }
        return BLACK.name();
    }

    public String makeCurrentColor(ChessGame chessGame, String requestBody) {
        if (chessGame.getBoard().get(Point.of(requestBody)).isSameTeam(BLACK)) {
            return BLACK.name();
        }
        return WHITE.name();
    }

    public int move(ChessGame chessGame, RequestDto requestDto) {
        String source = requestDto.getSource();
        String target = requestDto.getTarget();
        try {
            chessGame.playTurn(Point.of(source), Point.of(target));
            if(chessGame.isEnd()) {
                return RESET_CONTENT.getCode();
            }
            return OK.getCode();
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            return NO_CONTENT.getCode();
        }
    }

    public double score(ChessGame chessGame, String requestBody) {
        return chessGame.calculateScore(Color.valueOf(requestBody)).getScore();
    }

    public void addUser(String requestName, String requestPassword) throws SQLException {
        UserDto userDto = new UserDto(requestName, requestPassword);
        chessDAO.addUser(userDto);
    }

    public UserDto requestLoginUser(String requestName, String requestPassword) {
        return new UserDto(requestName, requestPassword);
    }

    public UserDto findUser(UserDto userDto) throws SQLException {
        return chessDAO.findByUserNameAndPwd(userDto.getName(), userDto.getPwd());
    }
}
