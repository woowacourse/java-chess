package chess.dto.response;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class BoardDto {
    private final Map<PositionDto, PieceDto> value;

    private BoardDto(Map<PositionDto, PieceDto> value) {
        this.value = value;
    }

    public static BoardDto from(Board board) {
        Map<PositionDto, PieceDto> value = new HashMap<>();

        for (YAxis yAxis : YAxis.values()) {
            for (XAxis xAxis : XAxis.values()) {
                Position position = Position.of(xAxis, yAxis);
                PositionDto positionDto = PositionDto.from(position);
                board.find(position).ifPresent(piece -> value.put(positionDto, PieceDto.from(piece)));
            }
        }

        return new BoardDto(value);
    }

    public static BoardDto from(Map<Position, Piece> boardValue) {
        Map<PositionDto, PieceDto> value = new HashMap<>();

        for (YAxis yAxis : YAxis.values()) {
            for (XAxis xAxis : XAxis.values()) {
                Position position = Position.of(xAxis, yAxis);
                Piece piece = boardValue.get(position);

                if (!Objects.isNull(piece)) {
                    value.put(PositionDto.from(position), PieceDto.from(piece));
                }
            }
        }

        return new BoardDto(value);
    }

    public Board toBoard() {
        Map<Position, Piece> boardValue = new HashMap<>();

        for (Entry<PositionDto, PieceDto> entrySet : value.entrySet()) {
            Piece piece = entrySet.getValue().toPiece();
            Position position = entrySet.getKey().toPosition();
            boardValue.put(position, piece);
        }

        return Board.from(boardValue);
    }

    public Map<PositionDto, PieceDto> getValue() {
        return Map.copyOf(value);
    }

    @Override
    public String toString() {
        return "AltBoardDto{" +
                "value=" + value +
                '}';
    }
}
