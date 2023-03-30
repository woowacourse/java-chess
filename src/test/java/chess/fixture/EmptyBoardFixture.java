package chess.fixture;

import java.util.HashMap;
import java.util.Map;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.piece.EmptyPiece;
import chess.piece.Piece;

public class EmptyBoardFixture {

    private final Map<Position, Piece> board;

    public EmptyBoardFixture() {
        board = new HashMap<>();
        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                board.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
