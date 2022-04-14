package chess.console.view;

import java.util.Map;
import java.util.stream.IntStream;

import chess.console.dto.ChessBoardDto;
import chess.console.dto.PositionDto;
import chess.domain.piece.property.Color;

public class OutputView {

    private static final String NO_PIECE = ".";
    private static final int RANK_SIZE = 8;
    private static final int FILE_SIZE = 8;

    public static void printChessBoard(ChessBoardDto chessBoardDto) {
        Map<PositionDto, String> chessBoard = chessBoardDto.getChessBoard();
        printPieces(chessBoard);
    }

    private static void printPieces(Map<PositionDto, String> chessBoard) {
        System.out.println("abcdefgh");
        System.out.println();
        for (int i = 0; i < RANK_SIZE; i++) {
            final int rank = i;
            IntStream.range(0, FILE_SIZE)
                .forEach(file -> System.out.print(chessBoard.getOrDefault(new PositionDto(rank, file), NO_PIECE)));
            System.out.print("   ");
            System.out.println(RANK_SIZE - i);
        }
        System.out.println();
        System.out.println("abcdefgh");
    }

    public static void printScore(Map<Color, Double> score) {
        for (Color color : score.keySet()) {
            System.out.println(color.name() + " " + score.get(color) + "점");
        }
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
