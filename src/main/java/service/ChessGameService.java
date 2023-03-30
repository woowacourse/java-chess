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

    public ChessGameCreateResponseDto createGameRoom(ChessGame chessGame) {
        Long roomId = 0L;
        roomId = createRoom();
        return insertPieces(chessGame, roomId);
    }

    public void updateChessGame(ChessGame chessGame, Long roomId) {
        updateGameRoom(chessGame, roomId);
        updatePieces(chessGame, roomId);
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

    private Long createRoom() {
        Long roomId;
        try {
            roomId = boardDao.createRoom();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("게임 생성 중 예외가 발생했습니다.");
        }
        return roomId;
    }

    private ChessGameCreateResponseDto insertPieces(ChessGame chessGame, Long roomId) {
        try {
            boardDao.saveChessBoard(chessGame.getBoard(), chessGame.getCurrentTurn(), roomId);
            return new ChessGameCreateResponseDto(chessGame, roomId);
        } catch (RuntimeException e) {
            boardDao.deleteGameRoom(roomId);
            throw new IllegalArgumentException("게임 생성 중 예외가 발생했습니다.");
        }
    }

    private void updateGameRoom(ChessGame chessGame, Long roomId) {
        try {
            boardDao.updateGameRoom(roomId, chessGame.getCurrentTurn(), chessGame.getState());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("게임 방 정보(상태) 저장 중 예외가 발생했습니다.");
        }
    }

    private void updatePieces(ChessGame chessGame, Long roomId) {
        try {
            boardDao.updateChessBoard(roomId, chessGame.getBoard());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("게임 방 정보(기물) 저장 중 예외가 발생했습니다.");
        }
    }
}
