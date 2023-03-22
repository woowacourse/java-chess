package chess.service;

import chess.dao.board.BoardDao;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.pieces.Piece;
import chess.factory.BoardFactory;
import chess.util.BoardUtil;
import java.util.List;
import java.util.Map;

public class BoardService {

    private final BoardDao boardDao = new BoardDao();
    private final BoardUtil boardUtil = new BoardUtil();

    public void save(final int boardId, final Map<Position, Piece> board, final boolean isLowerTeamTurn) {
        List<String> compressedBoard = boardUtil.compressBoard(board);
        String compressedPosition = compressedBoard.get(0);
        String compressedPiece = compressedBoard.get(1);

        boardDao.save(boardId, compressedPosition, compressedPiece, isLowerTeamTurn);
    }

    public Board findById(final int boardId) {
        List<String> compressedBoardFromDatabase = boardDao.findById(boardId);

        if (compressedBoardFromDatabase.isEmpty()) {
            return BoardFactory.createBoard();
        }

        Map<Position, Piece> unCompressedBoard = boardUtil.unCompressBoard(compressedBoardFromDatabase);
        return new Board(unCompressedBoard);
    }

    public boolean isLowerTeamTurnByBoardId(final int boardId) {
        return boardDao.isLowerTeamTurnByBoardId(boardId);
    }


    public void delete(final int boardId) {
        boardDao.remove(boardId);
    }
}
