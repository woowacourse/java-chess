package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.GameDto;
import chess.controller.dto.ScoreDto;
import chess.dao.GameDao;
import chess.dao.RoomDao;
import chess.domain.piece.Owner;
import chess.service.ChessGame;
import chess.service.RequestHandler;
import chess.view.web.OutputView;

import java.sql.Connection;
import java.util.Collections;
import java.util.Map;

import static spark.Spark.*;

public class WebController {

    private ChessGame chessGame;
    private final GameDao gameDao;
    private final RoomDao roomDao;

    public WebController(Connection connection) {
        this.gameDao = new GameDao(connection);
        this.roomDao = new RoomDao(connection);
    }

    public void mapping() {
        create();
        load();
        show();
        move();
    }

    private void load() {
        get("/load/:roomName", (req, res) -> {
            final String roomName = req.params(":roomName");
            System.out.println("room name : "+ roomName);

            final GameDto gameDto = gameDao.load(0);
            chessGame = ChessGame.load(gameDto.getBoard(), gameDto.getTurn());
            return printGame();
        });
    }

    private void create(){
        get("/create/:roomName", (req, res) -> {
            final String roomName = req.params(":roomName");
            chessGame = ChessGame.initNew();

            final long roomId = System.currentTimeMillis();
            gameDao.save(roomId, chessGame.turn(), chessGame.board());
            roomDao.save(roomName, roomId);

            res.redirect("/load/"+roomName);
            return printGame();
        });
    }

    private void show() {
        post("/show", (req, res) -> {
            final Map<String, String> request = RequestHandler.parse(req.body());
            try {
                chessGame.validateTurn(request);
                return chessGame.reachablePositions(request);
            } catch (Exception e) {
                return Collections.EMPTY_LIST;
            }
        });
    }

    private void move() {
        post("/move", (req, res) -> {
            chessGame.move(RequestHandler.parse(req.body()));
            gameDao.save(0, chessGame.turn(), chessGame.board());

            if (chessGame.isGameEnd()) {
                return OutputView.printResult(chessGame.winner());
            }
            return printGame();
        });
    }

    private String printGame() {
        final ScoreDto whiteScoreDto = new ScoreDto();
        final ScoreDto blackScoreDto = new ScoreDto();
        final BoardDto boardDto = new BoardDto();

        whiteScoreDto.setScore(chessGame.score(Owner.WHITE));
        blackScoreDto.setScore(chessGame.score(Owner.BLACK));
        boardDto.setBoard(chessGame.unicodeBoard());

        return OutputView.printGame(boardDto, whiteScoreDto, blackScoreDto);
    }
}
