package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
import chess.domain.piece.GamePieces;
import chess.domain.player.User;

public class BoardFactory {

    public static Board createEmptyBoard(User first, User second) {
        return Board.of(createEmptyMap(), Status.readyStatus(), first, second);
    }

    private static Map<Position, GamePiece> createEmptyMap() {
        return Position.list()
                .stream()
                .collect(Collectors.toMap(Function.identity(), position -> EmptyPiece.getInstance()));
    }

    public static Board createInitialBoard(User first, User second) {
        Map<Position, GamePiece> initialBoard = createEmptyMap();
        for (GamePiece piece : GamePieces.createGamePieces()) {
            placePiecesOnInitialPositions(initialBoard, piece);
        }

        return Board.of(initialBoard, Status.initialStatus(), first, second);
    }

    private static void placePiecesOnInitialPositions(Map<Position, GamePiece> board, GamePiece piece) {
        for (Position position : piece.getOriginalPositions()) {
            board.put(position, piece);
        }
    }

    public static Board of(Map<Position, GamePiece> boardInput, int turn, User first, User second) {
        Map<Position, GamePiece> board = new HashMap<>();

        for (Map.Entry<Position, GamePiece> entry : boardInput.entrySet()) {
            board.put(entry.getKey(), entry.getValue());
        }

        return Board.of(board, Status.from(turn), first, second);
    }
}
