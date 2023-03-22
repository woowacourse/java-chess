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

    private static final int POSITION_INDEX = 0;
    private static final int PIECE_INDEX = 1;

    private final BoardDao boardDao = new BoardDao();
    private final BoardUtil boardUtil = new BoardUtil();

    public void save(final int boardId, final Map<Position, Piece> board, final boolean isLowerTeamTurn) {
        List<String> compressedBoard = boardUtil.compressBoard(board);
        String compressedPosition = compressedBoard.get(POSITION_INDEX);
        String compressedPiece = compressedBoard.get(PIECE_INDEX);

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
