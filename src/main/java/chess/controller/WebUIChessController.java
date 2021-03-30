package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.gamestate.Ready;
import chess.domain.piece.Piece;
import chess.domain.user.User;
import chess.domain.user.UserDAO;
import chess.view.OutputView;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebUIChessController {
    private final UserDAO userDAO = new UserDAO();
    private final Gson gson = new Gson();
    private ChessGame chessGame;

    private static Map<String, Object> getChessBoardModelToRender(ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();
        Map<Position, Piece> chessBoard = chessGame.getChessBoardAsMap();
        Color turn = chessGame.getTurn();

        for (Map.Entry<Position, Piece> entry : chessBoard.entrySet()) {
            model.put(entry.getKey().getPosition(), entry.getValue());
        }
        model.put("turn", turn);

        if (chessGame.isOngoing()) {
            model.put("result", null);
            return model;
        }
        Result result = chessGame.result();
        model.put("result", result);
        return model;
    }

    public Map<String, Object> initializeChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessGame = new ChessGame(chessBoard, Color.WHITE, new Ready());
        chessGame.start(Collections.singletonList("start"));
        return getChessBoardModelToRender(chessGame);
    }

    public Map<String, Object> movePiece(List<String> input) {
        try {
            chessGame.play(input);
            return getChessBoardModelToRender(chessGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void gameResult() {
        try {
            Result result = chessGame.calculateResult();
            OutputView.printResult(result);
            initializeChessBoard();
        } catch (UnsupportedOperationException ignored) {
        }
    }

    public String findUser(String request) {
        try {
            String userId = gson.fromJson(request, User.class).getUserId();
            User user = userDAO.findByUserId(userId);
            return gson.toJson(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean addUser(String request) {
        try {
            User user = gson.fromJson(request, User.class);
            userDAO.addUser(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
