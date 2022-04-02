package chess.domain.board;

import java.util.LinkedHashMap;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;

public class InitialBoardGenerator implements BoardGenerator {

    @Override
    public Map<Square, Piece> generate() {
        Map<Square, Piece> board = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            createRow(board, rank);
        }
        return board;
    }

    private static void createRow(Map<Square, Piece> board, Rank rank) {
        for (File file : File.values()) {
            board.put(new Square(file, rank), Piece.from(file, rank));
        }
    }
}
