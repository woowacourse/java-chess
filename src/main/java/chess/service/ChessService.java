package chess.service;

import chess.dao.BoardDao;
import chess.dao.PieceDao;
import chess.dto.response.BoardResult;
import chess.dto.response.ScoreResult;
import chess.dto.response.Turn;
import chess.game.*;
import chess.piece.Color;
import chess.piece.Piece;
import chess.state.Moving;
import chess.state.Status;
import java.util.Map;

public class ChessService {
    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public ChessService() {
        this.boardDao = new BoardDao();
        this.pieceDao = new PieceDao();
    }

    public Long initBoard() {
        final Long boardId = boardDao.save(Color.WHITE);
        pieceDao.saveAll(boardId, BoardInitializer.getBoard());
        return boardId;
    }

    public void move(final Long boardId, final String from, final String to) {
        final Turn turn = boardDao.findById(boardId);
        final Color color = Color.valueOf(turn.getColor());
        final Moving moving = new Moving(new Board(pieceDao.findAllByBoardId(boardId)), color);
        moving.move(new MoveCommand(Position.of(from), Position.of(to)));
        final Board board = moving.getBoard();
        final Piece piece = board.findPiece(Position.of(to));

        pieceDao.deleteByPositionAndBoardId(from, boardId);
        pieceDao.save(boardId, to, piece);
        boardDao.update(boardId, color.reverse());
    }

    public BoardResult findBoardById(final Long boardId) {
        final Map<Position, Piece> findBoard = pieceDao.findAllByBoardId(boardId);
        final Board board = new Board(findBoard);
        return new BoardResult(boardId, findBoard, board.findWinColor());
    }

    public ScoreResult getScore(final Long boardId) {
        final Board board = new Board(pieceDao.findAllByBoardId(boardId));
        final Score score = new Status(board, Color.NONE).score();
        return new ScoreResult(score.getScore());
    }
}
