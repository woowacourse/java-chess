package service;

import dao.BoardDao;
import domain.game.Board;
import domain.game.ChessGame;
import domain.game.GameState;
import dto.ChessGameResponseDto;
import dto.ChessGameSaveRequestDto;

public class ChessGameService {
    private final BoardDao boardDao;

    public ChessGameService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void saveChessGame(ChessGameSaveRequestDto chessGameSaveRequestDto) {
        boardDao.delete();
        boardDao.save(chessGameSaveRequestDto.getBoard(), chessGameSaveRequestDto.getLastTurn());
    }

    public ChessGame loadChessGame() {
        ChessGameResponseDto chessGameResponseDto = boardDao.loadGame();
        return new ChessGame(new Board(chessGameResponseDto.getBoard()), chessGameResponseDto.getLastTurn(), GameState.RUN);
    }

    public boolean hasGame() {
        return boardDao.hasGame();
    }

}
