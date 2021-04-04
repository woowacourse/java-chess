package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.GameDto;
import chess.controller.dto.RoomStatusDto;
import chess.controller.dto.ScoresDto;
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

    private void create(){
        get("/create/:roomName", (req, res) -> {
            final String roomName = req.params(":roomName");
            chessGame = ChessGame.initNew();

            final long roomId = System.currentTimeMillis();
            gameDao.save(roomId, chessGame.turn(), chessGame.board());
            roomDao.save(roomName, roomId);

            res.redirect("/load/"+roomName);
            return printGame(roomName);
        });
    }

    private void load() {
        get("/load/:roomName", (req, res) -> {
            final String roomName = req.params(":roomName");
            final long roomId = roomDao.roomId(roomName);

            final GameDto gameDto = gameDao.load(roomId);
            chessGame = ChessGame.load(gameDto.getBoard(), gameDto.getTurn());

            return printGame(roomName);
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
        post("/move/:roomName", (req, res) -> {
            final String roomName = req.params(":roomName");
            final long roomId = roomDao.roomId(roomName);

            chessGame.move(RequestHandler.parse(req.body()));
            gameDao.save(roomId, chessGame.turn(), chessGame.board());

            if (chessGame.isGameEnd()) {
                return OutputView.printResult(chessGame.winner());
            }
            return printGame(roomName);
        });
    }

    private String printGame(final String roomName) {
        final ScoresDto scoresDto = new ScoresDto();
        final BoardDto boardDto = new BoardDto();
        final RoomStatusDto roomStatusDto = new RoomStatusDto();

        scoresDto.setWhiteScore(chessGame.score(Owner.WHITE));
        scoresDto.setBlackScore(chessGame.score(Owner.BLACK));
        boardDto.setBoard(chessGame.unicodeBoard());
        roomStatusDto.setName(roomName);
        roomStatusDto.setTurn(chessGame.turn().name());

        return OutputView.printGame(roomStatusDto,boardDto,scoresDto);
    }
}
