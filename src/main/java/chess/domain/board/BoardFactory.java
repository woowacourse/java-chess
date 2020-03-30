package chess.domain.board;

import java.util.LinkedHashMap;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class BoardFactory {
    private static final Map<Position, Piece> board = new LinkedHashMap<>();

    static {
        board.put(Position.of("a8"), new Rook(Position.of("a8"), Team.BLACK));
        board.put(Position.of("b8"), new Knight(Position.of("b8"), Team.BLACK));
        board.put(Position.of("c8"), new Bishop(Position.of("c8"), Team.BLACK));
        board.put(Position.of("d8"), new Queen(Position.of("d8"), Team.BLACK));
        board.put(Position.of("e8"), new King(Position.of("e8"), Team.BLACK));
        board.put(Position.of("f8"), new Bishop(Position.of("f8"), Team.BLACK));
        board.put(Position.of("g8"), new Knight(Position.of("g8"), Team.BLACK));
        board.put(Position.of("h8"), new Rook(Position.of("h8"), Team.BLACK));

        board.put(Position.of("a1"), new Rook(Position.of("a1"), Team.WHITE));
        board.put(Position.of("b1"), new Knight(Position.of("b1"), Team.WHITE));
        board.put(Position.of("c1"), new Bishop(Position.of("c1"), Team.WHITE));
        board.put(Position.of("d1"), new Queen(Position.of("d1"), Team.WHITE));
        board.put(Position.of("e1"), new King(Position.of("e1"), Team.WHITE));
        board.put(Position.of("f1"), new Bishop(Position.of("f1"), Team.WHITE));
        board.put(Position.of("g1"), new Knight(Position.of("g1"), Team.WHITE));
        board.put(Position.of("h1"), new Rook(Position.of("h1"), Team.WHITE));

        for (char c = 'a'; c <= 'h'; c++) {
            String key = String.valueOf(c) + 7;
            board.put(Position.of(key), new Pawn(Position.of(key), Team.BLACK));
            key = String.valueOf(c) + 2;
            board.put(Position.of(key), new Pawn(Position.of(key), Team.WHITE));

            for (int i = 3; i <= 6; i++) {
                key = String.valueOf(c) + i;
                board.put(Position.of(key), new Empty(Position.of(key), Team.NONE));
            }
        }
    }

    public static Board create() {
        return Board.of(board);
    }
}
