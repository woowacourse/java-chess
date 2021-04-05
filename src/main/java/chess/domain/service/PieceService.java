package chess.domain.service;

import java.sql.SQLException;
import java.util.List;

import chess.domain.board.BoardDTO;
import chess.domain.chess.Chess;
import chess.domain.piece.PieceDAO;
import chess.domain.piece.PieceDTO;

public class PieceService {
    private final PieceDAO pieceDAO;

    public PieceService() {
        this.pieceDAO = new PieceDAO();
    }

    public void saveAll(Long chessId) throws SQLException {
        Chess chess = Chess.createWithEmptyBoard()
                           .start();
        BoardDTO boardDTO = BoardDTO.from(chess);
        pieceDAO.saveAll(chessId, boardDTO);
    }

    public BoardDTO makeBoard(Long chessId) throws SQLException {
        List<PieceDTO> pieceDTOS = pieceDAO.findPiecesByChessId(chessId);
        return new BoardDTO(pieceDTOS);
    }
}
