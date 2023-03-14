package view;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import domain.piece.Piece;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class BoardView {

    private static final String EMPTY_SPACE = ".";

    public void printBoard(Map<Position, Piece> board) {
        Arrays.stream(Rank.values())
                .map(rank -> makeAnRankView(board, rank))
                .forEach(System.out::println);
        System.out.println();
    }

    private String makeAnRankView(final Map<Position, Piece> board, final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> file.getName() + rank.getName())
                .map(position -> makePieceView(board, Position.from(position)))
                .collect(Collectors.joining());
    }

    private String makePieceView(final Map<Position, Piece> board, final Position position) {
        if (board.containsKey(position)) {
            return board.get(position).getName();
        }

        return EMPTY_SPACE;
    }
}
