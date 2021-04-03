package chess.service;

import chess.domain.DTO.BoardDTO;
import chess.domain.ChessGame;
import chess.repository.ChessRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessService {
    private final ChessRepository chessRepository;

    public ChessService() {
        chessRepository = new ChessRepository();
    }

    public BoardDTO initiateBoard(ChessGame chessGame) {
        chessGame.settingBoard();
        return BoardDTO.of(chessGame.getBoard());
    }

    public BoardDTO getSavedBoardInfo() throws SQLException {
        ResultSet savedBoardInfo = chessRepository.getSavedBoardInfo();
        Map<String, String> boardInfo = new HashMap<>();
        while (savedBoardInfo.next()) {
            boardInfo.put(savedBoardInfo.getString("position"),
                    savedBoardInfo.getString("piece"));
        }
        return BoardDTO.of(boardInfo);
    }
}
