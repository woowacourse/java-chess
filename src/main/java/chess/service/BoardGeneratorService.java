package chess.service;

import chess.domain.board.Board;
import chess.persistence.dao.ChessBoardDAO;
import chess.persistence.dto.ChessBoardDTO;
import chess.persistence.dto.ChessGameDTO;

public class BoardGeneratorService {
    private BoardGeneratorService() {

    }

    public static BoardGeneratorService getInstance() {
        return BoardGeneratorHolder.INSTANCE;
    }

    public ChessBoardDTO request(ChessGameDTO chessGameDTO) {
        ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
        chessBoardDTO.setGameId(chessGameDTO.getGameId());
        int maxRound = ChessBoardDAO.getInstance().findMaxRoundByGameId(chessGameDTO.getGameId());

        if (maxRound == -1) {
            chessBoardDTO.setBoard(Board.drawBoard().getBoard());
            ChessBoardDAO.getInstance().addBoardStatus(chessBoardDTO);
            return chessBoardDTO;
        }

        chessBoardDTO.setRoundNo(maxRound);
        chessBoardDTO = ChessBoardDAO.getInstance().findByBoardStatus(chessBoardDTO);
        return chessBoardDTO;
    }

    private static class BoardGeneratorHolder {
        static final BoardGeneratorService INSTANCE = new BoardGeneratorService();
    }
}