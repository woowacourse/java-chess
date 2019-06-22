package chess.api;

import java.sql.SQLException;
import java.util.Map;

import chess.dao.BoardDAO;
import chess.dao.ResultCounterDAO;
import chess.dao.TurnDAO;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.BoardDTO;
import chess.dto.ChessBoardDTO;
import chess.dto.ResultCounterDTO;
import chess.dto.TemplateEngine;
import chess.dto.TurnDTO;
import chess.utils.DataParser;
import chess.utils.QueryParser;
import chess.web.Model;
import spark.Request;
import spark.Response;

public class ChessGameAPI {
    public static String index(Request req, Response res) {
        return TemplateEngine.render(Model.empty(), "index.html");
    }

    public static String gamePlayGet(Request req, Response res) throws SQLException{
        deleteHandler();
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardDTO chessBoardDTO = setChessBoardDTO(chessBoard);
        addHandler(chessBoardDTO);
        return TemplateEngine.render(Model.turn(chessBoard), "game_play.html");
    }

    public static String continueGame(Request req, Response res) throws SQLException {
        return TemplateEngine.render(Model.turn(), "game_play.html");
    }

    public static String gamePlayPost(Request req, Response res) throws SQLException {
        Position source = QueryParser.position(req.queryParams("source"));
        Position target = QueryParser.position(req.queryParams("target"));

        ChessBoard chessBoard = dtoChessBoard();
        boolean isKingDead = chessBoard.move(source, target);

        ChessBoardDTO chessBoardDTO = setChessBoardDTO(chessBoard);
        moveHandler(chessBoardDTO);

        if (isKingDead) {
            return TemplateEngine.render(Model.result(chessBoard), "result.html");
        }
        return TemplateEngine.render(Model.turn(chessBoard), "game_play.html");
    }

    public static String result(Request req, Response res) throws SQLException {
        ChessBoard chessBoard = dtoChessBoard();

        Double whiteScore = chessBoard.totalScore(Team.WHITE);
        Double blackScore = chessBoard.totalScore(Team.BLACK);

        return TemplateEngine.render(Model.result(whiteScore, blackScore), "result.html");
    }

    public static Map<String, String> status(Request req, Response res) throws SQLException {
        return DataParser.board(dtoChessBoard().getBoard());
    }

    public static String end(Request req, Response res) throws SQLException {
        deleteHandler();
        return TemplateEngine.render(Model.empty(), "end.html");
    }

    private static void addHandler(ChessBoardDTO chessBoardDTO) throws SQLException {
        BoardDAO.add(chessBoardDTO.getBoardDTO());
        TurnDAO.add(chessBoardDTO.getTurnDTO());
        ResultCounterDAO.add(chessBoardDTO.getResultCounterDTO());
    }

    private static ChessBoardDTO initChessBoardDTO() {
        ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
        chessBoardDTO.setBoardDTO(new BoardDTO());
        chessBoardDTO.setTurnDTO(new TurnDTO());
        chessBoardDTO.setResultCounterDTO(new ResultCounterDTO());
        return chessBoardDTO;
    }

    private static void deleteHandler() throws SQLException {
        BoardDAO.deleteAll();
        TurnDAO.deleteAll();
        ResultCounterDAO.deleteAll();
    }

    private static ChessBoardDTO setChessBoardDTO(ChessBoard chessBoard) {
        BoardDTO boardDTO = new BoardDTO();
        TurnDTO turnDTO = new TurnDTO();
        ResultCounterDTO resultCounterDTO = new ResultCounterDTO();
        boardDTO.setBoard(chessBoard.getBoard());
        turnDTO.setTurn(chessBoard.getTurn());
        resultCounterDTO.setResultCounter(chessBoard.getResultCounter());
        ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
        chessBoardDTO.setBoardDTO(boardDTO);
        chessBoardDTO.setTurnDTO(turnDTO);
        chessBoardDTO.setResultCounterDTO(resultCounterDTO);
        return chessBoardDTO;
    }

    private static ChessBoard dtoChessBoard() throws SQLException {
        BoardDTO boardDTO = BoardDAO.selectAll();
        TurnDTO turnDTO = TurnDAO.selectLastTurn();
        ResultCounterDTO resultCounterDTO = ResultCounterDAO.selectAll();
        return new ChessBoard(boardDTO.getBoard(), turnDTO.getTurn(), resultCounterDTO.getResultCounter());
    }

    private static void moveHandler(ChessBoardDTO chessBoardDTO) throws SQLException {
        BoardDAO.afterMove(chessBoardDTO.getBoardDTO());
        TurnDAO.afterMove(chessBoardDTO.getTurnDTO());
        ResultCounterDAO.afterMove(chessBoardDTO.getResultCounterDTO());
    }
}
