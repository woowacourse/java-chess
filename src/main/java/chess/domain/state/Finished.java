package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public class Finished extends Game {
    private final Map<Position, Piece> chessBoard;

    public Finished(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new UnsupportedOperationException("게임이 종료되었으므로 움직일 수 없습니다.");
    }

    @Override
    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    @Override
    public GameState terminate() {
        throw new UnsupportedOperationException("이미 끝난 게임입니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

}
