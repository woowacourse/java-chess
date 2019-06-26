package chess.service;

import chess.domain.board.Board;
import chess.persistence.dao.ChessBoardDAO;
import chess.persistence.dto.ChessBoardDTO;
import chess.persistence.dto.ChessGameDTO;

public class BoardGeneratorService {
    public static BoardGeneratorService getInstance() {
        return BoardGeneratorHolder.INSTANCE;
    }

    public ChessBoardDTO request(ChessGameDTO chessGameDTO) {
        ChessBoardDTO chessBoardDTO = new ChessBoardDTO();

        try {
            chessBoardDTO.setGameId(chessGameDTO.getGameId());
            chessBoardDTO.setRoundNo(ChessBoardDAO.getInstance().findMaxRoundByGameId(chessGameDTO.getGameId()));
            chessBoardDTO = ChessBoardDAO.getInstance().findByBoardStatus(chessBoardDTO);
        } catch (IllegalArgumentException e) {
            chessBoardDTO.setBoard(Board.drawBoard().getBoard());
            ChessBoardDAO.getInstance().addBoardStatus(chessBoardDTO);
        }

        return chessBoardDTO;
    }

    private static class BoardGeneratorHolder {
        static final BoardGeneratorService INSTANCE = new BoardGeneratorService();
    }
}