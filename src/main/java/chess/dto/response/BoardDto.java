package chess.dto.response;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class BoardDto {
    private final Map<PositionDto, PieceDto> board;

    private BoardDto(Map<PositionDto, PieceDto> board) {
        this.board = board;
    }

    public static BoardDto from(Board board) {
        Map<PositionDto, PieceDto> value = new HashMap<>();

        for (Position position : Position.getAllPositions()) {
            PositionDto positionDto = PositionDto.from(position);
            board.find(position).ifPresent(piece -> value.put(positionDto, PieceDto.from(piece)));
        }

        return new BoardDto(value);
    }

    public static BoardDto from(Map<Position, Piece> boardValue) {
        Map<PositionDto, PieceDto> dtoValue = boardValue.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entrySet -> PositionDto.from(entrySet.getKey()),
                        entrySet -> PieceDto.from(entrySet.getValue())
                ));

        return new BoardDto(dtoValue);
    }

    public Board toBoard() {
        Map<Position, Piece> boardValue = new HashMap<>();

        for (Entry<PositionDto, PieceDto> entrySet : this.board.entrySet()) {
            Piece piece = entrySet.getValue().toPiece();
            Position position = entrySet.getKey().toPosition();
            boardValue.put(position, piece);
        }

        return Board.from(boardValue);
    }

    public Map<PositionDto, PieceDto> getValue() {
        return Map.copyOf(board);
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "board=" + board +
                '}';
    }
}
