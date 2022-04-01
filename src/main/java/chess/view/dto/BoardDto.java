package chess.view.dto;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BoardDto {
    private final List<List<Optional<PieceDto>>> value;

    private BoardDto(List<List<Optional<PieceDto>>> value) {
        this.value = value;
    }

    // TODO: 리팩토링
    public static BoardDto from(Board board) {
        List<List<Optional<PieceDto>>> value = new ArrayList<>();

        for (YAxis yAxis : YAxis.values()) {
            value.add(generateRow(board, yAxis));
        }

        Collections.reverse(value);
        return new BoardDto(value);
    }

    // TODO: 리팩토링
    private static List<Optional<PieceDto>> generateRow(Board board, YAxis yAxis) {
        List<Optional<PieceDto>> row = new ArrayList<>();

        for (XAxis xAxis : XAxis.values()) {
            Position position = Position.from(xAxis, yAxis);
            Optional<PieceDto> piece = board.find(position)
                    .map(PieceDto::from);

            row.add(piece);
        }

        return row;
    }

    public List<List<Optional<PieceDto>>> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "value=" + value +
                '}';
    }
}
