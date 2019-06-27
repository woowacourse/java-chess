package chess.controller;

import java.sql.SQLException;
import java.util.Map;

import chess.dao.BoardDao;
import chess.dao.ResultCounterDao;
import chess.dao.TurnDao;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.BoardDto;
import chess.dto.ChessBoardDto;
import chess.dto.ResultCounterDto;
import chess.web.TemplateEngine;
import chess.dto.TurnDto;
import chess.utils.DataProcessor;
import chess.utils.PositionParser;
import chess.web.Model;
import spark.Request;
import spark.Response;

public class ChessGameController {
    public static String index(Request req, Response res) {
        return TemplateEngine.render(Model.empty(), "index.html");
    }

    public static String playGet(Request req, Response res) throws SQLException{
        deleteHandler();
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardDto chessBoardDto = dataLoadedChessBoardDto(chessBoard);
        addHandler(chessBoardDto);
        return TemplateEngine.render(Model.turn(chessBoard), "game_play.html");
    }

    private static void deleteHandler() throws SQLException {
        BoardDao.deleteAll();
        TurnDao.deleteAll();
        ResultCounterDao.deleteAll();
    }

    private static ChessBoardDto dataLoadedChessBoardDto(ChessBoard chessBoard) {
        ChessBoardDto chessBoardDto = new ChessBoardDto();
        chessBoardDto.setBoardDto(dataLoadedBoardDto(chessBoard));
        chessBoardDto.setTurnDto(dataLoadedTurnDto(chessBoard));
        chessBoardDto.setResultCounterDto(dataLoadedResultCounterDto(chessBoard));
        return chessBoardDto;
    }

    private static BoardDto dataLoadedBoardDto(ChessBoard chessBoard) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoard(chessBoard.getBoard());
        return boardDto;
    }

    private static TurnDto dataLoadedTurnDto(ChessBoard chessBoard) {
        TurnDto turnDto = new TurnDto();
        turnDto.setTurn(chessBoard.getTurn());
        return turnDto;
    }

    private static ResultCounterDto dataLoadedResultCounterDto(ChessBoard chessBoard) {
        ResultCounterDto resultCounterDto = new ResultCounterDto();
        resultCounterDto.setResultCounter(chessBoard.getResultCounter());
        return resultCounterDto;
    }

    private static void addHandler(ChessBoardDto chessBoardDto) throws SQLException {
        BoardDao.add(chessBoardDto.getBoardDto());
        TurnDao.add(chessBoardDto.getTurnDto());
        ResultCounterDao.add(chessBoardDto.getResultCounterDto());
    }

    public static String continueGame(Request req, Response res) throws SQLException {
        return TemplateEngine.render(Model.turn(), "game_play.html");
    }

    public static String playPost(Request req, Response res) throws SQLException {
        Position source = PositionParser.parse(req.queryParams("source"));
        Position target = PositionParser.parse(req.queryParams("target"));

        ChessBoard chessBoard = dtoChessBoard();
        boolean isKingDead = chessBoard.move(source, target);

        ChessBoardDto chessBoardDto = dataLoadedChessBoardDto(chessBoard);
        moveHandler(chessBoardDto);

        if (isKingDead) {
            return TemplateEngine.render(Model.result(chessBoard), "result.html");
        }
        return TemplateEngine.render(Model.turn(chessBoard), "game_play.html");
    }

    private static ChessBoard dtoChessBoard() throws SQLException {
        BoardDto boardDto = BoardDao.selectAll();
        TurnDto turnDto = TurnDao.selectLastTurn();
        ResultCounterDto resultCounterDto = ResultCounterDao.selectAll();
        return new ChessBoard(boardDto.getBoard(), turnDto.getTurn(), resultCounterDto.getResultCounter());
    }

    private static void moveHandler(ChessBoardDto chessBoardDto) throws SQLException {
        BoardDao.afterMove(chessBoardDto.getBoardDto());
        TurnDao.afterMove(chessBoardDto.getTurnDto());
        ResultCounterDao.afterMove(chessBoardDto.getResultCounterDto());
    }

    public static String result(Request req, Response res) throws SQLException {
        ChessBoard chessBoard = dtoChessBoard();

        Double whiteScore = chessBoard.totalScore(Team.WHITE);
        Double blackScore = chessBoard.totalScore(Team.BLACK);

        return TemplateEngine.render(Model.result(whiteScore, blackScore), "result.html");
    }

    public static Map<String, String> status(Request req, Response res) throws SQLException {
        return DataProcessor.board(dtoChessBoard().getBoard());
    }

    public static String end(Request req, Response res) throws SQLException {
        deleteHandler();
        return TemplateEngine.render(Model.empty(), "end.html");
    }
}
