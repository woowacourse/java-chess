package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.RoomDto;
import chess.controller.dto.ScoresDto;
import chess.dao.RoomDao;
import chess.domain.piece.Owner;
import chess.domain.ChessGame;
import chess.service.GameService;
import chess.view.web.OutputView;
import spark.Request;

import java.sql.Connection;

import static spark.Spark.*;

public class WebController {

    private final GameService gameService;
    private final RoomDao roomDao;

    public WebController(Connection connection) {
        this.roomDao = new RoomDao(connection);
        this.gameService = new GameService(connection);
    }

    public void mapping() {
        list();
        create();
        load();
        show();
        move();
    }

    private void list(){
        get("/mainPage", (req, res) -> OutputView.printRoomList(roomDao.load()));
    }

    private void create(){
        get("/create/:roomName", (req, res) -> {
            gameService.createGame(req);
            res.redirect("/load/"+roomName(req));
            return null;
        });
    }

    private void load() {
        get("/load/:roomName", (req, res) -> {
            return printGame(gameService.loadChessGame(req), roomName(req));
        });
    }

    private void show() {
        post("/show/:roomName", (req, res) -> gameService.show(req));
    }

    private void move() {
        post("/move/:roomName", (req, res) -> {
            gameService.move(req);

            final ChessGame chessGame = gameService.loadChessGame(req);
            if (chessGame.isGameEnd()) {
                return OutputView.printResult(chessGame.winner());
            }
            return printGame(chessGame, roomName(req));
        });
    }

    private String printGame(final ChessGame chessGame, final String roomName) {
        final ScoresDto scoresDto = new ScoresDto();
        final BoardDto boardDto = new BoardDto();
        final RoomDto roomDto = new RoomDto();

        scoresDto.setWhiteScore(chessGame.score(Owner.WHITE));
        scoresDto.setBlackScore(chessGame.score(Owner.BLACK));
        boardDto.setBoard(chessGame.unicodeBoard());
        roomDto.setName(roomName);

        return OutputView.printGame(roomDto,boardDto,scoresDto);
    }

    private String roomName(final Request req){
        return req.params(":roomName");
    }
}
