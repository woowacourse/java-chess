package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.postion.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardFixture {

    static Board setup() {
        Map<Position, Piece> cells = new HashMap<>();
       return new Board(cells);
    }
}
