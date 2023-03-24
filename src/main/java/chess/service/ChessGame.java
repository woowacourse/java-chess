package chess.service;

import chess.domain.board.Board;
import chess.domain.board.GameResult;
import chess.domain.position.Position;
import chess.dto.MoveDto;
import chess.repository.ChessDao;
import java.util.List;

public class ChessGame {
    private Board board;
    private final ChessDao chessDao;

    public ChessGame(final ChessDao chessDao) {
        this(new Board(), chessDao);
    }

    public ChessGame(final Board board, final ChessDao chessDao) {
        this.board = board;
        this.chessDao = chessDao;
    }

    public void initialize() {
        board.initialize();
        final List<MoveDto> moves = chessDao.findAll();
        for (MoveDto move : moves) {
            final Position source = Position.from(move.getSource());
            final Position target = Position.from(move.getTarget());
            board.move(source, target);
        }
    }

    public boolean isInitialized() {
        return !board.isNotInitialized();
    }

    public boolean isNotInitialized() {
        return board.isNotInitialized();
    }

    public void move(final MoveDto moveDto) {
        final Position sourcePosition = Position.from(moveDto.getSource());
        final Position targetPosition = Position.from(moveDto.getTarget());
        board.move(sourcePosition, targetPosition);
        chessDao.save(moveDto);
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    public void clear() {
        board = new Board();
        chessDao.deleteAll();
    }

    public GameResult getResult() {
        return board.getResult();
    }
}
