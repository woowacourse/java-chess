package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.GameDto;
import chess.controller.dto.ScoreDto;
import chess.dao.GameDao;
import chess.domain.piece.Owner;
import chess.service.ChessGame;
import chess.service.PieceSymbolMapper;
import chess.service.RequestHandler;
import chess.view.web.OutputView;

import java.sql.Connection;
import java.util.Collections;
import java.util.Map;

import static spark.Spark.*;

public class WebController {

    private final Connection connection;
    private ChessGame chessGame;

    public WebController(Connection connection) {
        this.connection = connection;
    }

    public void mapping() {
        init();
        newGame();
        show();
        move();
    }

    private void init() {
        get("/init", (req, res) -> {
//            final int roomId = Integer.parseInt(req.params(":room"));
            final GameDao gameDao = new GameDao(connection);
            final GameDto gameDto = gameDao.load(0);

            chessGame = new ChessGame();
            chessGame.load(gameDto.getBoard(), gameDto.getTurn());
            return printGame();
        });
    }

    private void newGame(){
        get("/newGame", (req, res) -> {
            chessGame = new ChessGame();
            chessGame.initNew();

            final GameDao gameDao = new GameDao(connection);
            gameDao.save(0, chessGame.turn(), chessGame.board());
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

            final GameDao gameDao = new GameDao(connection);
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
