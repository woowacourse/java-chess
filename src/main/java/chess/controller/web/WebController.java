package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.ScoreDto;
import chess.domain.piece.Owner;
import chess.service.ChessGame;
import chess.service.PieceSymbolMapper;
import chess.service.RequestHandler;
import chess.view.web.OutputView;

import java.util.Collections;
import java.util.Map;

import static spark.Spark.*;

public class WebController {

    private final ChessGame chessGame;

    public WebController() {
        chessGame = new ChessGame();
    }

    public void mapping() {
        init();
        show();
        move();
    }

    private void init() {
        get("/", (req, res) -> {
            chessGame.initNewGame();
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
