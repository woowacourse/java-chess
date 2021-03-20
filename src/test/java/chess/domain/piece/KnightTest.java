package chess.domain.piece;

import chess.domain.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {


    @DisplayName("나이트가 이동할 수 있는 모든 위치를 구한다.")
    @Test
    void generatePath() {
        Position current = Position.ofName("e4");
        Piece knight = new Knight(PieceColor.WHITE);

        Paths paths = new Paths(knight.findAllPath(current));

        for (Position position : paths.removeObstacles(BoardFactory.initializeBoard()).positions() ) {
            System.out.println(position.name());
        }
    }
}