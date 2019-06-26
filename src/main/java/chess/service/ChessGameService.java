package chess.service;

import chess.dao.ChessBoardDAO;
import chess.dao.ChessTurnDAO;
import chess.domain.board.BoardInitializer;
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
        ChessBoardDTO chessBoardDTO = ChessBoardDAO.getInstance().selectChessBoard(gameId);
        PieceColor turn = ChessTurnDAO.getInstance().selectChessTurn(gameId);

        ChessGameDTO chessGameDTO = new ChessGameDTO();
        chessGameDTO.setBoard(chessBoardDTO);
        chessGameDTO.setTurn(turn);

        return chessGameDTO;
    }

    public int getId(String idText) throws SQLException {
        int id;

        if (idText == null) {
            ChessTurnDAO.getInstance().insertChessTurn(PieceColor.WHITE);
            id = ChessTurnDAO.getInstance().selectMaxGameId();
            ChessBoardDAO.getInstance().insertChessBoard(id, new ChessBoardDTO(BoardInitializer.initialize()));
        } else {
            id = Integer.parseInt(idText);
        }

        return id;
    }
}
