package domain.board;

import domain.piece.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessBoard {
    private final List<List<Type>> board;

    private ChessBoard(List<List<Type>> board) {
        this.board = board;
    }

    public static ChessBoard createEmptyBoard() {
        List<List<Type>> board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board.add(createEmptyLine());
        }
        return new ChessBoard(board);
    }

    private static List<Type> createEmptyLine() {
        List<Type> types = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            types.add(Type.EMPTY);
        }
        return types;
    }

    public void fillPieces(Map<Position, Type> pieces) {
        for (Entry<Position, Type> entry : pieces.entrySet()) {
            Position position = entry.getKey();
            Type type = entry.getValue();
            board.get(position.getRow()).set(position.getColumn(), type);
        }
    }
}
