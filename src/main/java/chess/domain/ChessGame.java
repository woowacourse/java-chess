package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ChessGame {
    private Board board;

    public void start() {
        if (board != null) {
            throw new IllegalStateException("게임이 이미 시작중입니다.");
        }
        board = new Board();
    }

    public boolean isPlaying() {
        return board != null;
    }

    public void move(final String sourcePosition, final String targetPosition) {
        board = board.movePiece(Position.from(sourcePosition), Position.from(targetPosition));
    }

    public Map<String, Object> toMap() {
        return board.getPieces()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(), Entry::getValue));
    }
}
