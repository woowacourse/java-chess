package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {


    @DisplayName("룩이 이동할 수 있는 모든 위치를 구한다.")
    @Test
    void generatePath() {
        Position current = new Position(Column.E, Row.FOUR);
        Piece queen = new Queen(PieceColor.WHITE);

        List<Position> positions = queen.findAllPath(current);

        for (Position position : positions) {
            System.out.println(position.name());
        }
        System.out.println(positions.size());
    }
}