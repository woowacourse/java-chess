package chess;

import chess.controller.Command;
import chess.dao.BoardDao;
import chess.dao.DbBoardDao;
import chess.dao.DbGameDao;
import chess.dao.GameDao;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.domain.piece.ChessPiece;
import chess.dto.ChessBoardDto;
import chess.dto.GameInformationDto;
import chess.dto.WebChessStatusDto;
import chess.webview.ChessPieceImagePath;
import chess.webview.ColumnName;
import chess.webview.RowName;
import chess.webview.TeamName;
import java.util.List;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebApplication {
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final Map<Character, Integer> convertColumn = new HashMap<>();
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final int GAME_ID = 1111;
    private static final String IN_GAME_COMMAND_DELIMITER = " ";

    static {
        convertColumn.put('a', 1);
        convertColumn.put('b', 2);
        convertColumn.put('c', 3);
        convertColumn.put('d', 4);
        convertColumn.put('e', 5);
        convertColumn.put('f', 6);
        convertColumn.put('g', 7);
        convertColumn.put('h', 8);
    }

    public static void main(String[] args) {
        port(8082);
        staticFiles.location("/public");
        ChessGame chessGame = ChessGame.create(GAME_ID);

        get("/applicationCommand", (req, res) -> {
            Command command = Command.of(req.queryParams("command"));
            doApplicationCommand(res, chessGame, command);
            return null;
        });

        get("/board", (req, res) -> {
            return render(makeBoardModel(getChessBoardInformation(chessGame.getGameId(), new DbBoardDao())), "board.html");
        });

        post("/inGameCommand", (req, res) -> {
            List<String> inputs = divideInGameCommandInput(req.queryParams("command"));
            return doInGameCommand(res, chessGame, inputs);
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

    private static ChessBoardDto getChessBoardInformation(int gameId, BoardDao boardDao) {
        ChessBoardDto mapInfo = boardDao.findAll(gameId);
        return mapInfo;
    }

    private static String doInGameCommand(Response res, ChessGame chessGame, List<String> inputs) {
        Command command = Command.of(inputs.get(COMMAND_INDEX));
        if (Command.MOVE.equals(command)) {
            ChessBoardPosition source = coordinateToChessBoardPosition(inputs.get(SOURCE_INDEX));
            ChessBoardPosition target = coordinateToChessBoardPosition(inputs.get(TARGET_INDEX));
            return doMoveCommand(res, chessGame, source, target);
        }
        res.redirect("/status");
        return null;
    }

    private static String doMoveCommand(Response res, ChessGame chessGame, ChessBoardPosition source, ChessBoardPosition target) {
        chessGame.move(source, target);
        if (chessGame.isGameEnd()) {
            deleteAllData(chessGame.getGameId(), new DbGameDao(), new DbBoardDao());
            return render(null, "../public/index.html");
        }
        saveDataToDb(chessGame, new DbGameDao(), new DbBoardDao());
        res.redirect("/board");
        return null;
    }

    private static void deleteAllData(int gamdId, DbGameDao dbGameDao, DbBoardDao dbBoardDao) {
        dbBoardDao.deleteAll(gamdId);
        dbGameDao.deleteGameData(gamdId);
    }

    private static void saveDataToDb(ChessGame chessGame, GameDao gameDao, BoardDao boardDao) {
        gameDao.updateGameData(chessGame.getGameId(), GameInformationDto.of(chessGame.getGameId(), chessGame.getTurn()));
        boardDao.updateAll(chessGame.getGameId(), chessGame.getChessBoardInformation());
    }

    private static void doApplicationCommand(Response res, ChessGame chessGame, Command command) {
        if (Command.START.equals(command)) {
            doStartCommand(res, chessGame);
            return;
        }
        stop();
    }

    private static void doStartCommand(Response res, ChessGame chessGame) {
        loadData(chessGame, new DbGameDao(), new DbBoardDao());
        res.redirect("/board");
    }

    private static void loadData(ChessGame chessGame, DbGameDao dbGameDao, DbBoardDao dbBoardDao) {
        GameInformationDto gameInformationDto = dbGameDao.getGameData(chessGame.getGameId());
        if (gameInformationDto == null) {
            saveInitData(chessGame, dbGameDao, dbBoardDao);
            return;
        }
        chessGame.initFromDb(gameInformationDto, dbBoardDao.findAll(chessGame.getGameId()));
    }

    private static void saveInitData(ChessGame chessGame, DbGameDao dbGameDao, DbBoardDao dbBoardDao) {
        dbGameDao.saveGame(GameInformationDto.of(chessGame.getGameId(), chessGame.getTurn()));
        dbBoardDao.updateAll(chessGame.getGameId(), chessGame.getChessBoardInformation());
    }

    private static Map<String, Object> makeStatusModel(WebChessStatusDto webChessStatusDto) {
        Map<String, Object> model = new HashMap<>();
        model.put("blackScore", webChessStatusDto.getBlackScore());
        model.put("whiteScore", webChessStatusDto.getWhiteScore());
        model.put("winner", TeamName.of(webChessStatusDto.getWinner()));
        return model;
    }

    private static List<String> divideInGameCommandInput(String command) {
        return List.of(command.split(IN_GAME_COMMAND_DELIMITER));
    }

    private static ChessBoardPosition coordinateToChessBoardPosition(String coordinate) {
        System.out.println(coordinate);
        return ChessBoardPosition.of(extractColumn(coordinate), extractRow(coordinate));
    }

    private static int extractColumn(String input) {
        return convertColumn.get(input.charAt(COLUMN_INDEX));
    }

    private static int extractRow(String input) {
        return Character.getNumericValue(input.charAt(ROW_INDEX));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static Map<String, Object> makeBoardModel(ChessBoardDto chessBoardDto) {
        Map<ChessBoardPosition, ChessPiece> mapInfo = chessBoardDto.getMapInformation();
        Map<String, Object> boardModel = new HashMap<>();
        for (Entry<ChessBoardPosition, ChessPiece> entry : mapInfo.entrySet()) {
            boardModel.put(chessBoardToString(entry.getKey()), ChessPieceImagePath.of(entry.getValue()));
        }
        return boardModel;
    }

    private static String chessBoardToString(ChessBoardPosition chessBoardPosition) {
        return ColumnName.of(chessBoardPosition.getColumn()) + RowName.of(chessBoardPosition.getRow());
    }
}
