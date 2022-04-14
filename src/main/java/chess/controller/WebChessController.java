package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.stop;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.ChessBoardInitLogic;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import chess.service.StorageService;
import chess.webview.WebInputView;
import chess.webview.WebOutputView;
import java.util.Map;
import spark.Request;

public class WebChessController {
    private static final int GAME_ID = 1111;

    public void run() {
        port(8082);
        staticFiles.location("/public");
        ChessGame chessGame = ChessGame.create(GAME_ID);
        StorageService storageService = StorageService.create(new GameDao(), new BoardDao());

        get("/applicationCommand", (req, res) -> {
            ApplicationCommand command = WebInputView.toApplicationCommand(req.queryParams("command"));
            if (ApplicationCommand.START.equals(command)) {
                start(chessGame, storageService);
                return WebOutputView.goBoardPage(storageService.loadChessBoardData(chessGame.getGameId()));
            }
            stop();
            return null;
        });

        post("/inGameCommand", (req, res) -> {
            InGameCommand command = WebInputView.toInGameCommand(req.queryParams("command"));
            if (InGameCommand.MOVE.equals(command)) {
                move(req, chessGame, storageService);
                return goFirstPageIfGameEnd(chessGame, storageService);
            }
            return WebOutputView.goStatusPage(chessGame.getTeamScore(), chessGame.getWinner());
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static void start(ChessGame chessGame, StorageService storageService) {
        if (!storageService.hasData(chessGame.getGameId())) {
            storageService.saveInitData(chessGame.getGameId(), Team.WHITE, ChessBoardInitLogic.initialize());
        }

        Team turn = storageService.loadGameTurn(chessGame.getGameId());
        Map<ChessBoardPosition, ChessPiece> mapData = storageService.loadChessBoardData(chessGame.getGameId());
        chessGame.initialize(turn, mapData);
    }

    private static void move(Request req, ChessGame chessGame, StorageService storageService) {
        ChessBoardPosition source = WebInputView.extractSource(req.queryParams("command"));
        ChessBoardPosition target = WebInputView.extractTarget(req.queryParams("command"));
        chessGame.move(source, target);
        storageService.saveDataToDb(chessGame.getGameId(), chessGame.getTurn(), chessGame.getChessBoardData());
    }

    private static String goFirstPageIfGameEnd(ChessGame chessGame, StorageService storageService) {
        if (chessGame.isGameEnd()) {
            return WebOutputView.goInitialPage();
        }
        return WebOutputView.goBoardPage(storageService.loadChessBoardData(chessGame.getGameId()));
    }
}
