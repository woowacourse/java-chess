package chess.service;

import chess.domain.board.Board;
import chess.persistence.dao.ChessBoardDAO;
import chess.persistence.dto.ChessBoardDTO;
import chess.persistence.dto.ChessGameDTO;

public class BoardGeneratorService {
    private static final int FIRST_ROUND_NUMBER = 0;

    public static BoardGeneratorService getInstance() {
        return BoardGeneratorHolder.INSTANCE;
    }

    public ChessBoardDTO request(ChessGameDTO chessGameDTO) {
        ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
        chessBoardDTO.setGameId(chessGameDTO.getGameId());
        int maxRoundNo = ChessBoardDAO.getInstance().findMaxRoundByGameId(chessGameDTO.getGameId());
        if (maxRoundNo == ChessBoardDAO.NO_GAME_ID) {
            chessBoardDTO.setRoundNo(FIRST_ROUND_NUMBER);
            chessBoardDTO.setBoard(Board.drawBoard().getBoard());
            ChessBoardDAO.getInstance().addBoardStatus(chessBoardDTO);
        }
        chessBoardDTO.setRoundNo(ChessBoardDAO.getInstance().findMaxRoundByGameId(chessGameDTO.getGameId()));
        chessBoardDTO = ChessBoardDAO.getInstance().findByBoardStatus(chessBoardDTO);
        chessBoardDTO = ChessBoardDAO.getInstance().findByBoardStatus(chessBoardDTO);

        return chessBoardDTO;
    }

    private static class BoardGeneratorHolder {
        static final BoardGeneratorService INSTANCE = new BoardGeneratorService();
    }
}