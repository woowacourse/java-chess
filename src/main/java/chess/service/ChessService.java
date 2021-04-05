package chess.service;

import static chess.controller.WebChessController.*;
import static chess.domain.piece.Color.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import chess.dao.ChessDao;
import chess.dao.SQLConnection;
import chess.domain.ChessGame;
import chess.dto.RequestDto;
import chess.dto.UserDto;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.dto.BoardDto;

public class ChessService {
    private static final ChessDao chessDAO = new ChessDao(new SQLConnection());
    private static final Gson GSON = new Gson();

    private static ChessGame chessGame;
    private static UserDto userDto;

    public static String makeChessBoard() {
        Map<String, Object> model = new HashMap<>();
        model.put("user", userDto);
        return render(model, "chess.html");
    }

    public static String restartChess() throws SQLException {
        chessGame = new ChessGame();
        chessDAO.deleteBoard(chessDAO.findUserIdByUser(userDto));
        return render(new HashMap<>(), "/chess");
    }

    public static String matchBoardImageSource(String requestBody) throws SQLException {
        String userId = chessDAO.findUserIdByUser(userDto);
        BoardDto boardDto = chessDAO.findBoard(userId);
        chessGame = makeChessGame(boardDto);
        return GSON.toJson(chessGame.getBoard().get(Point.of(requestBody)).getImage());
    }

    public static String matchPieceName(String requestBody) {
        return GSON.toJson(chessGame.getBoard().get(Point.of(requestBody)).getName());
    }

    private static ChessGame makeChessGame(BoardDto boardDto) {
        if (boardDto == null) {
            return new ChessGame();
        }
        return new ChessGame(boardDto.getBoard());
    }

    public static int moveRequest(String requestBody) {
        RequestDto requestDto = GSON.fromJson(requestBody, RequestDto.class);
        return move(requestDto);
    }

    public static String addBoard(String requestBody) throws SQLException {
        chessDAO.addBoard(chessDAO.findUserIdByUser(userDto), requestBody, chessGame.nextTurn());
        return render(new HashMap<>(), "start.html");
    }

    public static String makeNextColor() {
        if (chessGame.nextTurn().isSameAs(BLACK)) {
            return WHITE.name();
        }
        return BLACK.name();
    }

    public static String makeCurrentColor(String requestBody) {
        if (chessGame.getBoard().get(Point.of(requestBody)).isSameTeam(BLACK)) {
            return BLACK.name();
        }
        return WHITE.name();
    }

    public static int move(RequestDto requestDto) {
        String source = requestDto.getSource();
        String target = requestDto.getTarget();
        try {
            chessGame.playTurn(Point.of(source), Point.of(target));
            if (chessGame.isEnd()) {
                return 333;
            }
            return 200;
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            return 401;
        }
    }

    public static double score(String requestBody) {
        return chessGame.calculateScore(Color.valueOf(requestBody)).getScore();
    }

    public static String signUp(String requestName, String requestPassword) throws SQLException {
        UserDto userDto = new UserDto(requestName, requestPassword);
        chessDAO.addUser(userDto);
        return render(new HashMap<>(), "start.html");
    }

    public static String login(String requestName, String requestPassword) throws SQLException {
        userDto = new UserDto(requestName, requestPassword);
        Map<String, Object> model = new HashMap<>();
        if (chessDAO.findByUserNameAndPwd(userDto.getName(), userDto.getPwd()) == null) {
            return render(model, "start.html");
        }
        model.put("user", userDto);
        return render(model, "chess.html");
    }
}
