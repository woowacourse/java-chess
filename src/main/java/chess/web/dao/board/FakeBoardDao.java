package chess.web.dao.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FakeBoardDao implements BoardDao {

    private final HashMap<Integer, HashMap<String, String>> board = new HashMap<>();

    @Override
    public void save(final int roomId, final Map<String, String> inputBoard) {
        final HashMap<String, String> gameBoard = new HashMap<>();
        for (final Entry<String, String> boardEntry : inputBoard.entrySet()) {
            final String position = boardEntry.getKey();
            final String piece = boardEntry.getValue();
            gameBoard.put(position, piece);
        }
        board.put(roomId, gameBoard);
    }

    @Override
    public Map<String, String> findAll() {
        final HashMap<String, String> returnBoard = new HashMap<>();
        for (final HashMap<String, String> value : board.values()) {
            addElementToBoard(returnBoard, value);
        }
        return returnBoard;
    }

    private void addElementToBoard(final HashMap<String, String> returnBoard, final HashMap<String, String> value) {
        for (final Entry<String, String> entry : value.entrySet()) {
            final String position = entry.getKey();
            final String piece = entry.getValue();
            returnBoard.put(position, piece);
        }
    }

    @Override
    public void updateBoard(final int roomId, final Map<String, String> inputBoard) {
        final HashMap<String, String> currentBoard = board.get(roomId);
        for (final Entry<String, String> entry : inputBoard.entrySet()) {
            final String position = entry.getKey();
            final String piece = entry.getValue();
            currentBoard.put(position, piece);
        }
        board.put(roomId, currentBoard);

    }

    @Override
    public void remove(final int roomId) {
        board.remove(roomId);
    }


}
