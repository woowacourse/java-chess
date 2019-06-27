package chess.service;

import chess.dao.ChessBoardDAO;
import chess.dao.ChessTurnDAO;
import chess.dao.DBConnection;
import chess.dao.JdbcTemplate;
import chess.domain.ChessGame;
import chess.domain.board.BoardInitializer;
import chess.domain.board.GameOverException;
import chess.domain.piece.PieceColor;
import chess.dto.ChessBoardDTO;
import chess.dto.ChessGameDTO;

import java.sql.SQLException;

public class ChessGameService {
    private static ChessGameService instance;

    private ChessGameService() {
    }

    public static ChessGameService getInstance() {
        if (instance == null) {
            instance = new ChessGameService();
        }
        return instance;
    }

    public ChessGameDTO getGame(int gameId) throws SQLException {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance(DBConnection.getConnection());
        ChessBoardDTO chessBoardDTO = ChessBoardDAO.getInstance(jdbcTemplate).selectChessBoard(gameId);
        PieceColor turn = ChessTurnDAO.getInstance(jdbcTemplate).selectChessTurn(gameId);

        ChessGameDTO chessGameDTO = new ChessGameDTO();
        chessGameDTO.setBoard(chessBoardDTO);
        chessGameDTO.setTurn(turn);

        return chessGameDTO;
    }

    public int getId(String idText) throws SQLException {
        int id;

        if (idText == null) {
            JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance(DBConnection.getConnection());
            ChessTurnDAO.getInstance(jdbcTemplate).insertChessTurn(PieceColor.WHITE);
            id = ChessTurnDAO.getInstance(jdbcTemplate).selectMaxGameId();
            ChessBoardDAO.getInstance(jdbcTemplate).insertChessBoard(id, new ChessBoardDTO(BoardInitializer.initialize()));
        } else {
            id = Integer.parseInt(idText);
        }

        return id;
    }

    public ChessGameDTO move(int id, String from, String to) throws SQLException {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance(DBConnection.getConnection());

        ChessGameDTO chessGameDTO = getGame(id);

        ChessGame chessGame = new ChessGame(chessGameDTO.getTurn(), chessGameDTO.getBoard().getBoard());

        try {
            chessGame.move(from, to);
            ChessBoardDTO chessBoardDTO = new ChessBoardDTO(chessGame.getBoard());
            chessGameDTO.setBoard(chessBoardDTO);
            chessGameDTO.setTurn(chessGame.getTurn());

            ChessTurnDAO.getInstance(jdbcTemplate).updateChessTurn(id, chessGameDTO.getTurn());
            ChessBoardDAO.getInstance(jdbcTemplate).deleteChessBoard(id);
            ChessBoardDAO.getInstance(jdbcTemplate).insertChessBoard(id, chessBoardDTO);
        } catch (GameOverException e) {
            ChessTurnDAO.getInstance(jdbcTemplate).deleteChessTurn(id);
            throw new GameOverException(chessGameDTO.getTurn().toString());
        }
        return chessGameDTO;
    }

    public ChessGameDTO getStatus(int id) throws SQLException {
        ChessGameDTO chessGameDTO = getGame(id);
        ChessGame chessGame = new ChessGame(chessGameDTO.getTurn(), chessGameDTO.getBoard().getBoard());
        chessGameDTO.setStatus(chessGame.status());
        return chessGameDTO;
    }
}
