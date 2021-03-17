package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OutputView {

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printBoard(Board board) {
        for (Rank rank: Rank.asListInReverseOrder()) {
            String line = Arrays.stream(File.values())
                    .map(file -> Position.of(file, rank))
                    .map(board::findByPosition)
                    .map(Piece::getNotation)
                    .collect(Collectors.joining());
            System.out.println(line);
        }
    }
}
