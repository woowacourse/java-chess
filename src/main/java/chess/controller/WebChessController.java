package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.stop;

import chess.dao.DbBoardDao;
import chess.dao.DbGameDao;
import chess.domain.ChessBoardInitLogic;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import chess.dto.ChessBoardDto;
import chess.dto.GameInformationDto;
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
            if (ApplicationCommand.START.equals(command)) {
                doStartCommand(chessGame, dbService);
                res.redirect("/board");
                return null;
            }
            stop();
            return null;
        });

        get("/board", (req, res) -> {
            return render(makeBoardModel(dbService.getChessBoardInformation(chessGame.getGameId())), "board.html");
        });

        post("/inGameCommand", (req, res) -> {
            InGameCommand command = WebInputView.toInGameCommand(req.queryParams("command"));
            if (InGameCommand.MOVE.equals(command)) {
                doMoveCommand(req, chessGame, dbService);
                return goFirstPageIfGameEnd(res, chessGame);
            }
            res.redirect("/status");
            return null;
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

    private static void doStartCommand(ChessGame chessGame, DbService dbService) {
        putDataIfStorageEmpty(chessGame.getGameId(), dbService);
        GameInformationDto gameInformationDto = dbService.loadGameInformationDto(chessGame.getGameId());
        ChessBoardDto chessBoardDto = dbService.getChessBoardInformation(chessGame.getGameId());
        chessGame.initialize(gameInformationDto.getTurn(), chessBoardDto.getMapInformation());
    }

    private static void putDataIfStorageEmpty(int gameId, DbService dbService) {
        if (!dbService.hasData(gameId)) {
            dbService.saveInitData(gameId, Team.WHITE, ChessBoardDto.of(ChessBoardInitLogic.initialize()));
        }
    }

    public static Map<String, Object> makeBoardModel(ChessBoardDto chessBoardDto) {
        Map<ChessBoardPosition, ChessPiece> mapInfo = chessBoardDto.getMapInformation();
        Map<String, Object> boardModel = new HashMap<>();
        for (Entry<ChessBoardPosition, ChessPiece> entry : mapInfo.entrySet()) {
            boardModel.put(chessBoardToString(entry.getKey()), ChessPieceImagePath.of(entry.getValue()));
        }
        return boardModel;
    }

    private static void doMoveCommand(Request req, ChessGame chessGame, DbService dbService) {
        ChessBoardPosition source = WebInputView.extractSource(req.queryParams("command"));
        ChessBoardPosition target = WebInputView.extractTarget(req.queryParams("command"));
        chessGame.move(source, target);
        dbService.saveDataToDb(chessGame.getGameId(), chessGame.getTurn(), chessGame.getChessBoardInformation());
    }

    private static String goFirstPageIfGameEnd(Response res, ChessGame chessGame) {
        if (chessGame.isGameEnd()) {
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
