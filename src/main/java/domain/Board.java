package domain;

import domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard;

    public Board(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static Board init() {
        Map<Position, Piece> initialPositions = new HashMap<>();
        Arrays.stream(PieceType.values())
                .forEach(pieceType -> pieceType.getInitPosition()
                        .forEach(position -> initialPositions.put(position, new Piece(pieceType))));

        return new Board(initialPositions);
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
