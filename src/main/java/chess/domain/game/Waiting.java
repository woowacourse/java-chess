package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Squares;
import chess.domain.piece.property.Color;
import database.BoardDao;

import java.util.List;

public final class Waiting implements GameStatus {

    BoardDao boardDao = new BoardDao();

    public Waiting() {
        super();
    }

    @Override
    public GameStatus start() {
        return new Running(boardDao, new Board(), Color.WHITE);
    }

    @Override
    public GameStatus playTurn(final Position source, final Position target) {
        throw new UnsupportedOperationException("시작하기 전에 이동 명령을 내릴 수 없습니다.");
    }

    @Override
    public GameStatus end() {
        return new Finished();
    }

    @Override
    public boolean isOnGoing() {
        return true;
    }

    @Override
    public List<Squares> getBoard() {
        throw new UnsupportedOperationException("보드가 존재하지 않습니다.");
    }

    @Override
    public GameStatus save() {
        return null;
    }
}
