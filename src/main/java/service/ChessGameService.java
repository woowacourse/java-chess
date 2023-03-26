package service;

import dao.ChessGameDao;
import domain.game.Board;
import domain.game.ChessGame;
import dto.dao.ChessGameDaoResponseDto;
import dto.service.ChessGameCreateResponseDto;

import java.util.List;

public class ChessGameService {
    private final ChessGameDao boardDao;

    public ChessGameService(ChessGameDao boardDao) {
        this.boardDao = boardDao;
    }

    public void updateChessGame(ChessGame chessGame, Long roomId) {
        boardDao.updateGame(roomId, chessGame.getBoard(), chessGame.getCurrentTurn());
    }

    public ChessGameCreateResponseDto createGameRoom(ChessGame chessGame) {
        Long roomId = boardDao.createRoom();
        boardDao.save(chessGame.getBoard(), chessGame.getCurrentTurn(), roomId);
        return new ChessGameCreateResponseDto(chessGame, roomId);
    }

    public ChessGameCreateResponseDto loadChessGame(Long roomId) {
        if (hasGame(roomId)) {
            ChessGameDaoResponseDto chessGameResponseDto = boardDao.loadGame(roomId);
            Board board = new Board(chessGameResponseDto.getBoard());
            ChessGame chessGame = new ChessGame(board, chessGameResponseDto.getLastTurn(), chessGameResponseDto.getState());
            return new ChessGameCreateResponseDto(chessGame, roomId);
        }
        throw new IllegalArgumentException("저장된 게임이 없습니다.");
    }

    public List<Long> findAllRooms() {
        return boardDao.findAllGameRooms();
    }

    public boolean hasGame(Long roomId) {
        return boardDao.hasGame(roomId);
    }

}
