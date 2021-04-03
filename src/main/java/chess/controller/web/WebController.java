package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.ScoreDto;
import chess.domain.board.position.Position;
import chess.domain.manager.ChessGame;
import chess.service.PieceSymbolMapper;
import chess.service.RequestHandler;
import chess.view.web.OutputView;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            final JSONObject jsonData = new JSONObject(req.body());
            final Position source = new Position(jsonData.getString("position"));
            try {
                chessGame.validateTurn(source);
                return reachablePositions(source);
            } catch (Exception e) {
                return Collections.EMPTY_LIST;
            }
        });
    }

    private void move() {
        post("/move", (req, res) -> {
            final Map<String, String> reqPosition = RequestHandler.parseRequestQuery(req.body());

            final Position source = new Position(reqPosition.get("source"));
            final Position target = new Position(reqPosition.get("target"));

            chessGame.validateTurn(source);
            chessGame.move(source, target);
            chessGame.changeTurn();

            if (chessGame.isGameEnd()) {
                return OutputView.printResult(chessGame.winner());
            }

            return printGame();
        });
    }

    private String printGame() {
        final ScoreDto whiteScoreDto = new ScoreDto();
        whiteScoreDto.setScore(chessGame.getWhiteScore());

        final ScoreDto blackScoreDto = new ScoreDto();
        blackScoreDto.setScore(chessGame.getBlackScore());

        final BoardDto boardDto = new BoardDto();
        boardDto.setBoard(PieceSymbolMapper.parseBoardAsUnicode(chessGame.board()));
        return OutputView.printGame(boardDto, whiteScoreDto, blackScoreDto);
    }

    private List<String> reachablePositions(Position source) {
        return chessGame.reachablePositions(source).stream()
                .map(position -> position.parseAsString())
                .collect(Collectors.toList());
    }
}
