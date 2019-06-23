package chess.api;

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
        ChessBoardDto chessBoardDTO = dataLoadedChessBoardDTO(chessBoard);
        addHandler(chessBoardDTO);
        return TemplateEngine.render(Model.turn(chessBoard), "game_play.html");
    }

    private static void deleteHandler() throws SQLException {
        BoardDao.deleteAll();
        TurnDao.deleteAll();
        ResultCounterDao.deleteAll();
    }

    private static ChessBoardDto dataLoadedChessBoardDTO(ChessBoard chessBoard) {
        ChessBoardDto chessBoardDTO = new ChessBoardDto();
        chessBoardDTO.setBoardDTO(dataLoadedBoardDTO(chessBoard));
        chessBoardDTO.setTurnDTO(dataLoadedTurnDTO(chessBoard));
        chessBoardDTO.setResultCounterDTO(dataLoadedResultCounterDTO(chessBoard));
        return chessBoardDTO;
    }

    private static BoardDto dataLoadedBoardDTO(ChessBoard chessBoard) {
        BoardDto boardDTO = new BoardDto();
        boardDTO.setBoard(chessBoard.getBoard());
        return boardDTO;
    }

    private static TurnDto dataLoadedTurnDTO(ChessBoard chessBoard) {
        TurnDto turnDTO = new TurnDto();
        turnDTO.setTurn(chessBoard.getTurn());
        return turnDTO;
    }

    private static ResultCounterDto dataLoadedResultCounterDTO(ChessBoard chessBoard) {
        ResultCounterDto resultCounterDTO = new ResultCounterDto();
        resultCounterDTO.setResultCounter(chessBoard.getResultCounter());
        return resultCounterDTO;
    }

    private static void addHandler(ChessBoardDto chessBoardDTO) throws SQLException {
        BoardDao.add(chessBoardDTO.getBoardDTO());
        TurnDao.add(chessBoardDTO.getTurnDTO());
        ResultCounterDao.add(chessBoardDTO.getResultCounterDTO());
    }

    public static String continueGame(Request req, Response res) throws SQLException {
        return TemplateEngine.render(Model.turn(), "game_play.html");
    }

    public static String playPost(Request req, Response res) throws SQLException {
        Position source = PositionParser.parse(req.queryParams("source"));
        Position target = PositionParser.parse(req.queryParams("target"));

        ChessBoard chessBoard = dtoChessBoard();
        boolean isKingDead = chessBoard.move(source, target);

        ChessBoardDto chessBoardDTO = dataLoadedChessBoardDTO(chessBoard);
        moveHandler(chessBoardDTO);

        if (isKingDead) {
            return TemplateEngine.render(Model.result(chessBoard), "result.html");
        }
        return TemplateEngine.render(Model.turn(chessBoard), "game_play.html");
    }

    private static ChessBoard dtoChessBoard() throws SQLException {
        BoardDto boardDTO = BoardDao.selectAll();
        TurnDto turnDTO = TurnDao.selectLastTurn();
        ResultCounterDto resultCounterDTO = ResultCounterDao.selectAll();
        return new ChessBoard(boardDTO.getBoard(), turnDTO.getTurn(), resultCounterDTO.getResultCounter());
    }

    private static void moveHandler(ChessBoardDto chessBoardDTO) throws SQLException {
        BoardDao.afterMove(chessBoardDTO.getBoardDTO());
        TurnDao.afterMove(chessBoardDTO.getTurnDTO());
        ResultCounterDao.afterMove(chessBoardDTO.getResultCounterDTO());
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
