package chess.service;

import chess.dao.ChessBoardDAO;
import chess.domain.piece.Color;
import chess.dto.ChessBoardDTO;

import java.sql.SQLException;

public class ChessBoardService {

    private ChessBoardDAO chessBoardDAO = new ChessBoardDAO();

    public void updateChessBoard(ChessBoardDTO chessBoardDTO) throws SQLException {
        chessBoardDAO.updateChessBoard(chessBoardDTO.getId(), chessBoardDTO.getTurn());
    }

    public void deleteChessBoard(ChessBoardDTO chessBoardDTO) throws SQLException {
        chessBoardDAO.deleteChessBoard(chessBoardDTO.getId());
    }

    public Color findTurn(String id) throws SQLException {
        String turn = chessBoardDAO.findByPlayerId(id);
        return Color.of(turn);
    }
}
