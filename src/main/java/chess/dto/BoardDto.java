package chess.dto;

import chess.game.Board;
import chess.piece.Piece;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {

    private List<List<PieceDto>> value;

    private BoardDto(final List<List<PieceDto>> value) {
        this.value = value;
    }

    public static BoardDto toDto(Board board) {
        final List<List<PieceDto>> boardPieces = new ArrayList<>();

        final Map<Position, Piece> pieces = board.getValue();
        System.out.println(board.toString());
        for (int i = 8; i >= 1; i--) {
            final List<PieceDto> line = new ArrayList<>();
            for (int j = 1; j <= 8; j++) {
                final Position position = Position.of(Column.of(j), Row.of(i));
                line.add(createPieceDto(pieces, position));
            }
            boardPieces.add(line);
        }

        return new BoardDto(boardPieces);
    }

    private static PieceDto createPieceDto(final Map<Position, Piece> pieces, final Position position) {
        if (pieces.containsKey(position)) {
            return PieceDto.toDto(pieces.get(position));
        }
        return PieceDto.toEmptyDto();
    }

    public List<List<PieceDto>> getValue() {
        return value;
    }
}
