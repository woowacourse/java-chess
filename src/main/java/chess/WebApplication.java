package chess;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.game.Board;
import chess.domain.game.Color;
import chess.domain.game.Command;
import chess.dto.RequestDto;
import chess.dto.ResponseDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import static spark.Spark.*;

public class WebApplication {

    public static void main(String[] args) {
        final Gson gson = new Gson();
        final WebChessController webChessController = new WebChessController();
        final BoardDao boardDao = new BoardDao();
        final ChessGameDao chessGameDao = new ChessGameDao();

        port(8081);
        staticFiles.location("templates");

        get("/", (req, res) -> {
            return new HandlebarsTemplateEngine().render(new ModelAndView(new HashMap<>(), "index.html"));
        });

        get("/load", (req, res) -> {
            ResponseDto responseDto = null;

            try {
                boolean isGameExists = boardDao.isBoardExists();
                if (isGameExists) {
                    Board chessBoard = Board.of(boardDao.getBoard());
                    Color turn = chessGameDao.getTurn();
                    responseDto = webChessController.load(chessBoard, turn);
                    chessGameDao.updateGame(responseDto);
                }

                if (!isGameExists) {
                    responseDto = webChessController.start();
                    chessGameDao.saveGame(responseDto);
                }

                boardDao.deleteBoard();
                boardDao.saveBoard(responseDto.getBoardDto());
            } catch (SQLException | NullPointerException e) {
                res.status(500);
                e.getStackTrace();
                return gson.toJson(e.getMessage());
            }

            res.status(200);
            return gson.toJson(responseDto);
        });

        get("/move", (req, res) -> {
            try {
                RequestDto requestDto = new RequestDto(Command.MOVE, req);
                ResponseDto responseDto = webChessController.move(requestDto);

                chessGameDao.updateGame(responseDto);
                boardDao.deleteBoard();
                boardDao.saveBoard(responseDto.getBoardDto());
                chessGameDao.updateGame(responseDto);

                return gson.toJson(responseDto);

            } catch (IllegalArgumentException
                    | IllegalStateException
                    | NullPointerException
                    | SQLException e) {
                res.status(500);
                return gson.toJson(e.getMessage());
            }
        });


        get("/end", (req, res) -> {
            try {
                ResponseDto responseDto = webChessController.end();
                boardDao.deleteBoard();
                chessGameDao.deleteGame();

                return gson.toJson(responseDto);

            } catch (IllegalArgumentException | IllegalStateException e) {
                res.status(500);
                return gson.toJson(e.getMessage());
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}