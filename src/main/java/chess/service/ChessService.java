package chess.service;

import chess.dao.BoardDao;
import chess.dao.PieceDao;
import chess.dto.response.BoardResult;
import chess.dto.response.Turn;
import chess.game.Board;
import chess.game.BoardInitializer;
import chess.game.MoveCommand;
import chess.game.Position;
import chess.piece.Color;
import chess.piece.Piece;
import chess.state.Moving;
import chess.state.Status;
import java.util.Map;
import java.util.stream.Collectors;

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
        return new BoardResult(boardId, pieceDao.findAllByBoardId(boardId));
    }

    public Map<String, Double> getScore(final Long boardId) {
        final Map<Color, Double> score = new Status(new Board(pieceDao.findAllByBoardId(boardId)), Color.NONE).score().getScore();
        return score.keySet()
                .stream()
                .collect(Collectors.toMap(Enum::name, score::get));
    }
}
