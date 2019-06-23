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
import chess.web.TemplateEngine;
import chess.dto.TurnDTO;
import chess.utils.DataParser;
import chess.utils.QueryParser;
import chess.web.Model;
import spark.Request;

public class ChessGameController {
    public static String index() {
        return TemplateEngine.render(Model.empty(), "index.html");
    }

    public static String playGet() throws SQLException{
        deleteHandler();
        ChessBoard chessBoard = new ChessBoard();
        ChessBoardDTO chessBoardDTO = dataLoadedChessBoardDTO(chessBoard);
        addHandler(chessBoardDTO);
        return TemplateEngine.render(Model.turn(chessBoard), "game_play.html");
    }

    private static void deleteHandler() throws SQLException {
        BoardDAO.deleteAll();
        TurnDAO.deleteAll();
        ResultCounterDAO.deleteAll();
    }

    private static ChessBoardDTO dataLoadedChessBoardDTO(ChessBoard chessBoard) {
        ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
        chessBoardDTO.setBoardDTO(dataLoadedBoardDTO(chessBoard));
        chessBoardDTO.setTurnDTO(dataLoadedTurnDTO(chessBoard));
        chessBoardDTO.setResultCounterDTO(dataLoadedResultCounterDTO(chessBoard));
        return chessBoardDTO;
    }

    private static BoardDTO dataLoadedBoardDTO(ChessBoard chessBoard) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoard(chessBoard.getBoard());
        return boardDTO;
    }

    private static TurnDTO dataLoadedTurnDTO(ChessBoard chessBoard) {
        TurnDTO turnDTO = new TurnDTO();
        turnDTO.setTurn(chessBoard.getTurn());
        return turnDTO;
    }

    private static ResultCounterDTO dataLoadedResultCounterDTO(ChessBoard chessBoard) {
        ResultCounterDTO resultCounterDTO = new ResultCounterDTO();
        resultCounterDTO.setResultCounter(chessBoard.getResultCounter());
        return resultCounterDTO;
    }

    private static void addHandler(ChessBoardDTO chessBoardDTO) throws SQLException {
        BoardDAO.add(chessBoardDTO.getBoardDTO());
        TurnDAO.add(chessBoardDTO.getTurnDTO());
        ResultCounterDAO.add(chessBoardDTO.getResultCounterDTO());
    }

    public static String continueGame() throws SQLException {
        return TemplateEngine.render(Model.turn(), "game_play.html");
    }

    public static String playPost(Request req) throws SQLException {
        Position source = QueryParser.position(req.queryParams("source"));
        Position target = QueryParser.position(req.queryParams("target"));

        ChessBoard chessBoard = dtoChessBoard();
        boolean isKingDead = chessBoard.move(source, target);

        ChessBoardDTO chessBoardDTO = dataLoadedChessBoardDTO(chessBoard);
        moveHandler(chessBoardDTO);

        if (isKingDead) {
            return TemplateEngine.render(Model.result(chessBoard), "result.html");
        }
        return TemplateEngine.render(Model.turn(chessBoard), "game_play.html");
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

    public static String result() throws SQLException {
        ChessBoard chessBoard = dtoChessBoard();

        Double whiteScore = chessBoard.totalScore(Team.WHITE);
        Double blackScore = chessBoard.totalScore(Team.BLACK);

        return TemplateEngine.render(Model.result(whiteScore, blackScore), "result.html");
    }

    public static Map<String, String> status() throws SQLException {
        return DataParser.board(dtoChessBoard().getBoard());
    }

    public static String end() throws SQLException {
        deleteHandler();
        return TemplateEngine.render(Model.empty(), "end.html");
    }
}
