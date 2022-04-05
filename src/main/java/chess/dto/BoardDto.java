package chess.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class BoardDto {
    private final List<SquareDto> squares;

    public BoardDto(final Map<Position, Piece> board) {
        this.squares = new ArrayList<>();
        Arrays.stream(Rank.values())
            .forEach(rank -> addSquaresByRank(board, rank));
    }

    private void addSquaresByRank(final Map<Position, Piece> board, final Rank rank) {
        Arrays.stream(File.values())
            .forEach(file -> addSquaresByRankAndFile(board, rank, file));
    }

    private void addSquaresByRankAndFile(final Map<Position, Piece> board, final Rank rank, final File file) {
        Position position = Position.valueOf(file, rank);
        if (board.containsKey(position)) {
            squares.add(new SquareDto(position, board.get(position)));
            return;
        }
        this.squares.add(new SquareDto(position));
    }

    public List<SquareDto> getSquares() {
        return squares;
    }
}
