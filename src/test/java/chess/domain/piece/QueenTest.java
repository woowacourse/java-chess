package chess.domain.piece;

import chess.domain.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {


    @DisplayName("퀸이 이동가능한 전체 위치를 구한다. 상황 : 흰퀸-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.ofName("e4");
        Piece queen = new Queen(PieceColor.WHITE);

        Paths paths = new Paths(queen.findAllPath(current));

        for (Position position : paths.removeObstacles(queen, BoardFactory.initializeBoard()).positions()) {
            System.out.println(position.name());
        }
//        System.out.println(positions.size());
    }
}