package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Squares;
import chess.domain.piece.property.Color;

import java.util.List;

public final class Waiting implements GameStatus {

    public Waiting() {
        super();
    }

    @Override
    public GameStatus start() {
        return new Running(new Board(), Color.WHITE);
    }

    @Override
    public GameStatus playTurn(final Position source, final Position target) {
        throw new UnsupportedOperationException("시작하기 전에 이동 명령을 내릴 수 없습니다.");
    }

    @Override
    public List<Squares> getBoard() {
        return null;
    }
}
