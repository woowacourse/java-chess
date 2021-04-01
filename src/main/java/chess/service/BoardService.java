package chess.service;

import chess.domain.DTO.BoardDTO;
import chess.domain.ChessGame;

public class BoardService {

    public BoardDTO initiateBoard(ChessGame chessGame) {
        chessGame.settingBoard();
        return BoardDTO.of(chessGame.getBoard());
    }
}
