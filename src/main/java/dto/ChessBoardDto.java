package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.ChessBoard;
import model.piece.Piece;
import model.position.File;
import model.position.Position;
import model.position.Rank;
import view.message.PieceType;

public class ChessBoardDto {

    private final List<String> value;

    private ChessBoardDto(final List<String> value) {
        this.value = value;
    }

    // TODO 리팩터링 된건가?
    public static ChessBoardDto from(final ChessBoard chessBoard) {
        List<List<String>> tmp = createEmptyBoard();
        Map<Position, Piece> pieceOfPosition = chessBoard.getBoard();

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
        return new ChessBoardDto(result);
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
        List<String> fileInfo = Arrays.stream(File.values())
                .map(File::getValue)
                .toList();
        result.add(fileInfo);
        return result;
    }

    private static List<List<String>> createEmptyBoard() {
        return IntStream.range(0, 8)
                .mapToObj(i -> new ArrayList<>(Collections.nCopies(8, "")))
                .collect(Collectors.toList());
    }

    public List<String> getValue() {
        return value;
    }
}
