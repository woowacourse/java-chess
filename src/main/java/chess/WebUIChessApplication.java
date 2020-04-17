package chess;

import chess.dao.ChessBoardDao;
import chess.dao.ChessGameDao;
import chess.domain.game.ChessBoard;
import chess.controller.ChessController;
import chess.domain.game.Command;
import chess.domain.game.Player;
import chess.dto.RequestDto;
import chess.dto.ResponseDto;
import com.google.gson.Gson;

import java.sql.SQLException;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final Gson GSON = new Gson();
    private static final ChessController CHESS_CONTROLLER = new ChessController();
    private static final ChessBoardDao CHESS_BOARD_DAO = new ChessBoardDao();
    private static final ChessGameDao CHESS_GAME_DAO = new ChessGameDao();

    public static void main(String[] args) throws SQLException {
        port(8081);
        staticFiles.location("templates");
        CHESS_BOARD_DAO.deleteChessBoard();

        get("/loadGame", (req, res) -> {
            ResponseDto responseDto = null;

            try {
                boolean isGameExists = CHESS_BOARD_DAO.isChessBoardExists();
                if (isGameExists) {
                    ChessBoard chessBoard = new ChessBoard(CHESS_BOARD_DAO.getChessBoard());
                    Player turn = CHESS_GAME_DAO.getTurn();
                    responseDto = CHESS_CONTROLLER.load(chessBoard, turn);
                    CHESS_GAME_DAO.updateGame(responseDto);
                }

                if (!isGameExists) {
                    responseDto = CHESS_CONTROLLER.start();
                    CHESS_GAME_DAO.saveGame(responseDto);
                }

                CHESS_BOARD_DAO.deleteChessBoard();
                CHESS_BOARD_DAO.saveChessBoard(responseDto.getChessBoardDto());
            } catch (SQLException| NullPointerException e) {
                res.status(500);
                e.getStackTrace();
                return GSON.toJson(e.getMessage());
            }

            res.status(200);
            return GSON.toJson(responseDto);
        });


        get("/move", (req, res) -> {
            try {
                RequestDto requestDto = new RequestDto(Command.MOVE, req);
                ResponseDto responseDto = CHESS_CONTROLLER.move(requestDto);

                CHESS_GAME_DAO.updateGame(responseDto);
                CHESS_BOARD_DAO.deleteChessBoard();
                CHESS_BOARD_DAO.saveChessBoard(responseDto.getChessBoardDto());
                CHESS_GAME_DAO.updateGame(responseDto);

                return GSON.toJson(responseDto);
            } catch (IllegalArgumentException
                    | IllegalStateException
                    | NullPointerException
                    | SQLException e) {
                res.status(500);
                return GSON.toJson(e.getMessage());
            }
        });

        get("/end", (req, res) -> {
            try {
                ResponseDto responseDto = CHESS_CONTROLLER.end();
                CHESS_BOARD_DAO.deleteChessBoard();
                CHESS_GAME_DAO.deleteChessGame();
                return GSON.toJson(responseDto);
            } catch (IllegalArgumentException | IllegalStateException e) {
                res.status(500);
                return GSON.toJson(e.getMessage());
            }
        });
    }
}
