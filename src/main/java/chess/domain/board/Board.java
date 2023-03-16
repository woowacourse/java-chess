package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> boards;

    public Board(Map<Position, Piece> boards) {
        this.boards = boards;
    }

    //source포지션에서 기물꺼내기
    // 기물에게 소스, 타겟 넘겨서
    // 리스트받아오기
    public Position findSquare(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate) {
        return null;
    }

    public Map<Position, Piece> getBoards() {
        return boards;
    }

    public Piece findPiece(Position position) {
        if (boards.containsKey(position)) {
            return boards.get(position);
        }
        throw new IllegalArgumentException("잘못된 위치를 입력했습니다");
    }
}
