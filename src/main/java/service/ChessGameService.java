package service;

import dao.BoardDao;
import dto.ChessGameSaveRequestDto;
import dto.ChessGameServiceResponseDto;
import dto.service.ChessGameControllerResponseDto;

public class ChessGameService {
    private final BoardDao boardDao;

    public ChessGameService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void saveChessGame(ChessGameSaveRequestDto chessGameSaveRequestDto) {
        boardDao.delete();
        boardDao.save(chessGameSaveRequestDto.getBoard(), chessGameSaveRequestDto.getLastTurn());
    }

    public ChessGameControllerResponseDto loadChessGame() {
        if (hasGame()) {
            ChessGameServiceResponseDto chessGameResponseDto = boardDao.loadGame();
            return ChessGameControllerResponseDto.from(chessGameResponseDto);
        }
        throw new IllegalArgumentException("저장된 게임이 없습니다.");
    }

    public boolean hasGame() {
        return boardDao.hasGame();
    }

}
