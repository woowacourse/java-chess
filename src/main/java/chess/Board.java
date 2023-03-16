package chess;

import chess.piece.Piece;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final BoardFactory boardFactory) {
        this.board = boardFactory.createInitialBoard();
    }

    public Map<Position, Piece> board() {
        return board;
    }

    public void move(Position from, Position to) {
        if (!board.containsKey(from)) {
            throw new IllegalArgumentException("출발점에 말이 없습니다.");
        }
        Path path = board.get(from)
                .searchPathTo(to, Optional.ofNullable(board.get(to)));
        //패스에 기물이 있는지 확인하는 기능 추가
        path.validateObstacle(board.keySet());

        // 패스에 기물이 없으니 이동
        Piece piece = board.remove(from);
        board.put(to, piece);
    }
}
