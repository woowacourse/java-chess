package chess.dto.board;

import chess.domain.board.piece.Piece;
import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RowDto {

    private final List<SquareDto> squares;

    public RowDto(Map<Position, Piece> board, Rank rank) {
        this.squares = File.allFilesAscending()
                .stream()
                .map(file -> Position.of(file, rank))
                .map(position -> initSquare(board, position))
                .collect(Collectors.toUnmodifiableList());
    }

    private SquareDto initSquare(Map<Position, Piece> board, Position position) {
        if (!board.containsKey(position)) {
            return SquareDto.ofEmpty();
        }
        Piece piece = board.get(position);
        return SquareDto.ofOccupied(piece);
    }

    public List<SquareDto> getSquares() {
        return squares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RowDto rowDto = (RowDto) o;
        return Objects.equals(squares, rowDto.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares);
    }

    @Override
    public String toString() {
        return "RowDto{" + "squares=" + squares + '}';
    }
}
