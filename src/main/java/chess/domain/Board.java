package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.strategy.EmptyMoveStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board initialize() {
        Map<Position, Piece> board = new HashMap<>();

        List<String> locations = createLocations();
        for (String location : locations) {
            Position position = Position.of(location);
            PieceInfo pieceInfo = new PieceInfo(position, Team.NONE);
            EmptyPiece emptyPiece = new EmptyPiece(pieceInfo, new EmptyMoveStrategy());
            board.put(position, emptyPiece);
        }

        return new Board(board);
    }

    private static List<String> createLocations() {
        List<String> positions = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            char ch1 = (char) (i / 8 + 'a');
            char ch2 = (char) (i % 8 + '1');
            positions.add("" + ch1 + ch2);
        }

        return positions;
    }
}
