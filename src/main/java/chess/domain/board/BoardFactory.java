package chess.domain.board;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;

public class BoardFactory {

    public static Board createEmptyBoard() {
        return Board.from(createEmptyMap(), Status.readyStatus());
    }

    private static Map<Position, GamePiece> createEmptyMap() {
        return Position.list()
                .stream()
                .collect(Collectors.toMap(Function.identity(), position -> EmptyPiece.getInstance()));
    }

    public static Board createInitialBoard() {
        Map<Position, GamePiece> initialBoard = createEmptyMap();
        for (GamePiece piece : GamePiece.createGamePieces()) {
            placePiecesOnInitialPositions(initialBoard, piece);
        }

        return Board.from(initialBoard, Status.initialStatus());
    }

    private static void placePiecesOnInitialPositions(Map<Position, GamePiece> board, GamePiece piece) {
        for (Position position : piece.getOriginalPositions()) {
            board.put(position, piece);
        }
    }
}
