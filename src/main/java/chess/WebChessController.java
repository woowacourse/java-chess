package chess;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.game.Board;
import chess.domain.game.Color;
import chess.domain.game.Command;
import chess.dto.ChessGameDto;
import chess.dto.RequestDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebChessController {
    final Gson gson = new Gson();
    final ChessGame chessGame = new ChessGame();
    final BoardDao boardDao = new BoardDao();
    final ChessGameDao chessGameDao = new ChessGameDao();

    public void run() {
        get("/", (req, res) -> new HandlebarsTemplateEngine().render(new ModelAndView(new HashMap<>(), "index.html")));

        get("/load", (req, res) -> {
            ChessGameDto responseDto = null;

            try {
                boolean isGameExists = boardDao.isBoardExists();
                if (isGameExists) {
                    Board chessBoard = Board.of(boardDao.getBoard());
                    Color turn = chessGameDao.getTurn();
                    responseDto = chessGame.load(chessBoard, turn);
                    chessGameDao.updateGame(responseDto);
                }

                if (!isGameExists) {
                    responseDto = chessGame.start();
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
                ChessGameDto chessGameDto = chessGame.move(requestDto);

                chessGameDao.updateGame(chessGameDto);
                boardDao.deleteBoard();
                boardDao.saveBoard(chessGameDto.getBoardDto());
                chessGameDao.updateGame(chessGameDto);

                return gson.toJson(chessGameDto);

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
                ChessGameDto responseDto = chessGame.end();
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
