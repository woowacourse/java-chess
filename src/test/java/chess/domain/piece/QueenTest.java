package chess.domain.piece;

import chess.domain.BoardFactory;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {


    @DisplayName("룩이 이동할 수 있는 모든 위치를 구한다.")
    @Test
    void generatePath() {
        Position current = Position.of("e4");
        Piece queen = new Queen(PieceColor.WHITE);

        Paths paths = new Paths(queen.findAllPath(current));

        for (Position position : paths.removeObstacles(BoardFactory.initializeBoard()).positions()) {
            System.out.println(position.name());
        }
//        System.out.println(positions.size());
    }
}