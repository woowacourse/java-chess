package service;

import dao.BoardDao;
import domain.game.ChessGame;
import domain.game.GameState;
import dto.dao.ChessGameDaoResponseDto;
import dto.service.ChessGameServiceResponseDto;

public class ChessGameService {
    private final BoardDao boardDao;

    public ChessGameService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

//    public void saveChessGame(ChessGame chessGame) {
//        boardDao.delete();
//        boardDao.save(chessGame.getBoard(), chessGame.getCurrentTurn());
//    }

    public void createGameRoom(ChessGame chessGame) {
        Long roomId = boardDao.createRoom();
        boardDao.save(chessGame.getBoard(), chessGame.getCurrentTurn(), roomId);
    }

    public ChessGameServiceResponseDto loadChessGame() {
        if (hasGame()) {
            ChessGameDaoResponseDto chessGameResponseDto = boardDao.loadGame();
            return new ChessGameServiceResponseDto(chessGameResponseDto.getBoard(), chessGameResponseDto.getLastTurn(), 0, GameState.RUN);
//            return ChessGameServiceResponseDto.from(chessGameResponseDto);
        }
        throw new IllegalArgumentException("저장된 게임이 없습니다.");
    }

    public boolean hasGame() {
        return boardDao.hasGame();
    }

}
