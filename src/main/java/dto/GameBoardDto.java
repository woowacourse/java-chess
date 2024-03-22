package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import model.GameBoard;
import model.piece.Piece;
import model.position.File;
import model.position.Position;
import model.position.Rank;
import view.message.PieceType;

public class GameBoardDto {

    private final List<String> value;

    private GameBoardDto(final List<String> value) {
        this.value = value;
    }

    // TODO 리팩터링 된건가?
    public static GameBoardDto from(final GameBoard gameBoard) {
        List<List<String>> tmp = createEmptyBoard();
        Map<Position, Piece> pieceOfPosition = gameBoard.getBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = new Position(File.from(j), Rank.from(i));
                tmp.get(i).set(j, convertToString(pieceOfPosition, position));
            }
        }
        List<List<String>> paddingBoard = paddingGuideLine(tmp);
        List<String> result = paddingBoard.stream()
                .map(list -> String.join("", list))
                .toList();
        return new GameBoardDto(result);
    }


    private static String convertToString(Map<Position, Piece> board, Position position) {
        if (board.containsKey(position)) {
            return PieceType.from(board.get(position)).getValue();
        }
        return ".";
    }

    private static List<List<String>> paddingGuideLine(List<List<String>> board) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            List<String> rank = board.get(i);
            rank.add("   " + (board.size() - i));
            result.add(rank);
        }
        result.add(new ArrayList<>());
        List<String> fileInfo = new ArrayList<>();
        Arrays.stream(File.values())
                .forEach(file -> fileInfo.add(file.getValue()));
        result.add(fileInfo);
        return result;
    }

    private static List<List<String>> createEmptyBoard() {
        List<List<String>> tmp = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            List<String> line = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                line.add("");
            }
            tmp.add(line);
        }
        return tmp;
    }

    public List<String> getValue() {
        return value;
    }
}
