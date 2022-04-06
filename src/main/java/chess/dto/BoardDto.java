package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoardDto {
    // TODO: Map 으로 리팩토링 필요함
    private final List<List<Optional<PieceDto>>> value;

    private BoardDto(List<List<Optional<PieceDto>>> value) {
        this.value = value;
    }

    // TODO: 리팩토링
    public static BoardDto from(Board board) {
        List<List<Optional<PieceDto>>> value = new ArrayList<>();

        for (YAxis yAxis : YAxis.values()) {
            value.add(generateRowFromBoard(board, yAxis));
        }

        Collections.reverse(value);
        return new BoardDto(value);
    }

    // TODO: 리팩토링
    public static BoardDto from(Map<Position, Piece> boardValue) {
        List<List<Optional<PieceDto>>> value = new ArrayList<>();

        for (YAxis yAxis : YAxis.values()) {
            List<Optional<PieceDto>> row = new ArrayList<>();

            for (XAxis xAxis : XAxis.values()) {
                Position position = Position.of(xAxis, yAxis);
                Piece piece = boardValue.get(position);
                if (!Objects.isNull(piece)) {
                    row.add(Optional.of(PieceDto.from(piece)));
                    continue;
                }
                row.add(Optional.empty());
            }

            value.add(row);
        }

        Collections.reverse(value);
        return new BoardDto(value);
    }

    public static BoardDto from(List<List<Optional<PieceDto>>> value) {
        return new BoardDto(value);
    }

    // TODO: 리팩토링
    private static List<Optional<PieceDto>> generateRowFromBoard(Board board, YAxis yAxis) {
        List<Optional<PieceDto>> row = new ArrayList<>();

        for (XAxis xAxis : XAxis.values()) {
            Position position = Position.of(xAxis, yAxis);
            Optional<PieceDto> piece = board.find(position)
                    .map(PieceDto::from);

            row.add(piece);
        }

        return row;
    }

    public List<List<Optional<PieceDto>>> getValue() {
        return value;
    }

    // TODO: 리팩토링
    // TODO: DTO의 역할이 맞는지?
    public List<List<String>> getPieceImages() {
        return value.stream()
                .map(row -> row.stream()
                        .map(pieceDto -> pieceDto.map(PieceDto::getImageName)
                                .orElse(null)).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    // TODO: 리팩토링
    public Board toBoard() {
        Map<Position, Piece> boardValue = new HashMap<>();

        for (int i = 0; i < value.size(); i++) {
            for (int j = 0; j < value.get(i).size(); j++) {
                XAxis xAxis = XAxis.getByValue(i + 1);
                YAxis yAxis = YAxis.getByValue(8 - j);
                Position position = Position.of(xAxis, yAxis);

                value.get(j)
                        .get(i)
                        .ifPresent(pieceDto -> boardValue.put(position, pieceDto.toPiece()));
            }
        }

        return Board.from(boardValue);
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "value=" + value +
                '}';
    }
}
