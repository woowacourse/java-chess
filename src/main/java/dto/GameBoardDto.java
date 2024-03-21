package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.GameBoard;
import view.message.PieceType;
import model.piece.Piece;
import model.position.Position;

public class GameBoardDto {

    private final List<String> value;

    private GameBoardDto(final List<String> value) {
        this.value = value;
    }

    public static GameBoardDto from(GameBoard gameBoard) {
        List<List<String>> tmp = createEmptyBoard();
        Map<Position, Piece> board = gameBoard.getBoard();

        for (Entry<Position, Piece> entry : board.entrySet()) {
            tmp.get(entry.getKey().getRowIndex()).set(entry.getKey().getColumnIndex(), PieceType.from(entry.getValue()).getValue());
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                stringBuilder.append(tmp.get(i).get(j));
            }
            stringBuilder.append("   ").append(8 - i);
            result.add(stringBuilder.toString());
        }
        result.add("");
        result.add("abcdefgh");
        return new GameBoardDto(result);
    }

    private static List<List<String>> createEmptyBoard() {
        List<List<String>> tmp = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            List<String> line = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                line.add(".");
            }
            tmp.add(line);
        }
        return tmp;
    }

    public List<String> getValue() {
        return value;
    }
}
