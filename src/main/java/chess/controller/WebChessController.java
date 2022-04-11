package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.stop;

import chess.dao.DbBoardDao;
import chess.dao.DbGameDao;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.domain.piece.ChessPiece;
import chess.dto.ChessBoardDto;
import chess.dto.WebChessStatusDto;
import chess.service.DbService;
import chess.webview.ChessPieceImagePath;
import chess.webview.ColumnName;
import chess.webview.RowName;
import chess.webview.TeamName;
import chess.webview.WebInputView;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    private static final int GAME_ID = 1111;

    public void run() {
        port(8082);
        staticFiles.location("/public");
        ChessGame chessGame = ChessGame.create(GAME_ID);
        DbService dbService = DbService.create(new DbGameDao(), new DbBoardDao());

        get("/applicationCommand", (req, res) -> {
            ApplicationCommand command = WebInputView.toApplicationCommand(req.queryParams("command"));
            doApplicationCommand(res, chessGame, command);
            return null;
        });

        get("/board", (req, res) -> {
            return render(makeBoardModel(dbService.getChessBoardInformation(chessGame.getGameId())), "board.html");
        });

        post("/inGameCommand", (req, res) -> {
            return doInGameCommand(req, res, chessGame);
        });

        get("/status", (req, res) -> {
            WebChessStatusDto webChessStatusDto = chessGame.getStatusInformationForWeb();
            Map<String, Object> model = makeStatusModel(webChessStatusDto);
            return render(model, "status.html");
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static void doApplicationCommand(Response res, ChessGame chessGame, ApplicationCommand command) {
        if (ApplicationCommand.START.equals(command)) {
            chessGame.setChessGameForStart();
            res.redirect("/board");
            return;
        }
        stop();
    }

    public static Map<String, Object> makeBoardModel(ChessBoardDto chessBoardDto) {
        Map<ChessBoardPosition, ChessPiece> mapInfo = chessBoardDto.getMapInformation();
        Map<String, Object> boardModel = new HashMap<>();
        for (Entry<ChessBoardPosition, ChessPiece> entry : mapInfo.entrySet()) {
            boardModel.put(chessBoardToString(entry.getKey()), ChessPieceImagePath.of(entry.getValue()));
        }
        return boardModel;
    }

    private static String doInGameCommand(Request req, Response res, ChessGame chessGame) {
        InGameCommand command = WebInputView.toInGameCommand(req.queryParams("command"));
        if (InGameCommand.MOVE.equals(command)) {
            return doMoveCommand(req, res, chessGame);
        }
        res.redirect("/status");
        return null;
    }

    private static String doMoveCommand(Request req, Response res, ChessGame chessGame) {
        ChessBoardPosition source = WebInputView.extractSource(req.queryParams("command"));
        ChessBoardPosition target = WebInputView.extractTarget(req.queryParams("command"));
        chessGame.moveAndSave(source, target);
        if (chessGame.isGameEnd()) {
            chessGame.initialze();
            return render(null, "../public/index.html");
        }
        res.redirect("/board");
        return null;
    }

    private static Map<String, Object> makeStatusModel(WebChessStatusDto webChessStatusDto) {
        Map<String, Object> model = new HashMap<>();
        model.put("blackScore", webChessStatusDto.getBlackScore());
        model.put("whiteScore", webChessStatusDto.getWhiteScore());
        model.put("winner", TeamName.of(webChessStatusDto.getWinner()));
        return model;
    }


    private static String chessBoardToString(ChessBoardPosition chessBoardPosition) {
        return ColumnName.of(chessBoardPosition.getColumn()) + RowName.of(chessBoardPosition.getRow());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
