package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.PieceNameConverter;
import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Piece;

public class BoardResponseDto {
    private final Map<Square, Piece> board;

    public BoardResponseDto(Map<Square, Piece> board) {
        this.board = board;
    }

    public List<String> getBoard() {
        Map<Square, String> chessBoard = board
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(Map.Entry::getKey, entry -> PieceNameConverter.convert(entry.getValue())));

        List<String> messages = new ArrayList<>();
        for (Rank value : Rank.values()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (File file : File.values()) {
                stringBuilder.append(chessBoard.get(new Square(file, value)));
            }
            messages.add(stringBuilder.toString());
        }
        Collections.reverse(messages);

        return messages;
    }

}
