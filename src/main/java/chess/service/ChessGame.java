package chess.service;

import chess.domain.board.Board;
import chess.domain.board.GameResult;
import chess.domain.board.InitialState;
import chess.domain.position.Position;
import chess.dto.MoveDto;
import chess.repository.ChessDao;
import java.util.List;

public class ChessGame {
    private Board board;
    private final ChessDao chessDao;

    public ChessGame(final ChessDao chessDao) {
        this(new InitialState(), chessDao);
    }

    public ChessGame(final Board board, final ChessDao chessDao) {
        this.board = board;
        this.chessDao = chessDao;
    }

    public void initialize() {
        board = board.initialize();
        final List<MoveDto> moves = chessDao.findAll();
        for (MoveDto move : moves) {
            final Position source = Position.from(move.getSource());
            final Position target = Position.from(move.getTarget());
            board = board.move(source, target);
        }
    }

    public boolean isInitialized() {
        return board.isInitialized();
    }

    public void move(final String source, final String target) {
        final Position sourcePosition = Position.from(source);
        final Position targetPosition = Position.from(target);
        board = board.move(sourcePosition, targetPosition);
        chessDao.save(new MoveDto(source, target));
    }

    public GameResult getResult() {
        return board.getResult();
    }
}
