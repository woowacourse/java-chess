package chess.service;

import static chess.domain.piece.Color.*;

import chess.dao.ConnectedMoveDao;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.service.dto.MoveDto;
import java.util.List;

public class ChessGameService {

    private final ConnectedMoveDao moveDao;

    public ChessGameService() {
        this.moveDao = new ConnectedMoveDao();
    }

    public Board load() {
        final Board board = BoardInitializer.initialize();
        final List<MoveDto> moves = moveDao.restart();
        for (MoveDto move : moves) {
            final String source = move.getSource();
            final String target = move.getTarget();

            board.move(source, target);
        }
        return loadStoredBoard(board, moves);
    }

    private Board loadStoredBoard(final Board board, final List<MoveDto> moves) {
        if (isWhiteTurn(moves)) {
            return new Board(board.getBoard(), WHITE);
        }
        return new Board(board.getBoard(), BLACK);
    }

    private boolean isWhiteTurn(final List<MoveDto> moves) {
        return moves.size() % 2 == 0;
    }

    public void move(final String source, final String target) {
        MoveDto moveDto = new MoveDto(source, target);
        moveDao.save(moveDto);
    }

    public void start() {
        moveDao.delete();
    }
}