package chessgame.db;

import java.util.List;

import chessgame.domain.Board;
import chessgame.domain.Game;

public class BoardService {
    private static final int NEW_BOARD_NUMBER = 0;

    private final BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public Board getBoard(int boardNo) {
        validateBoardNo(boardNo);
        return generateBoard(boardNo);
    }

    private void validateBoardNo(int boardNo) {
        if (boardNo != NEW_BOARD_NUMBER && boardDao.isNotRunning(boardNo)) {
            throw new IllegalArgumentException("진행중인 게임 번호가 아닙니다.");
        }
    }

    private Board generateBoard(int boardNo) {
        if (boardNo == NEW_BOARD_NUMBER) {
            boardDao.addBoard();
            boardNo = boardDao.findLastBoardNo();
            boardDao.addPoints(boardNo);
        }
        return boardDao.findBoardByNo(boardNo);
    }

    public List<Integer> getRunningBoards() {
        return boardDao.findRunningBoards();
    }

    public String getState(int boardNo) {
        return boardDao.getState(boardNo);
    }

    public void updateBoard(Game game) {
        boardDao.updatePoints(game.board());
        boardDao.updateBoardState(game);
    }
}
