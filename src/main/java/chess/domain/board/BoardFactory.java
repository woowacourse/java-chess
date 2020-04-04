package chess.domain.board;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
import chess.domain.piece.GamePieces;

public class BoardFactory {

    public static Board createEmptyBoard() {
        return Board.of(createEmptyMap(), Status.readyStatus());
    }

    private static Map<Position, GamePiece> createEmptyMap() {
        return Position.list()
                .stream()
                .collect(Collectors.toMap(Function.identity(), position -> EmptyPiece.getInstance()));
    }

    public static Board createInitialBoard() {
        Map<Position, GamePiece> initialBoard = createEmptyMap();
        for (GamePiece piece : GamePieces.createGamePieces()) {
            placePiecesOnInitialPositions(initialBoard, piece);
        }

        return Board.of(initialBoard, Status.initialStatus());
    }

    private static void placePiecesOnInitialPositions(Map<Position, GamePiece> board, GamePiece piece) {
        for (Position position : piece.getOriginalPositions()) {
            board.put(position, piece);
        }
    }

    public static Board of(Map<Position, GamePiece> boardInput, int turn) {
        Map<Position, GamePiece> board = createEmptyMap();

        for (Map.Entry<Position, GamePiece> entry : boardInput.entrySet()) {
            board.put(entry.getKey(), entry.getValue());
        }

        return Board.of(board, Status.from(turn));
    }
}
