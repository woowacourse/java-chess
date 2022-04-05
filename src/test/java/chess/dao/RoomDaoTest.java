package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.BoardInitialize;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class RoomDaoTest {
    @Test
    void save() {
        Map<Position, Piece> board = BoardInitialize.create();
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            String positionToString = position.getPositionToString();
            String symbol = piece.getSymbol();
        }
    }
}
