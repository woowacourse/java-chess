package chess.view;

import java.util.Map;
import java.util.stream.IntStream;

import chess.dto.ChessBoardDto;
import chess.dto.PositionDto;

public class OutputView {

    private static final String NO_PIECE = ".";
    private static final int RANK_SIZE = 8;
    private static final int FILE_SIZE = 8;

    public static void printChessBoard(ChessBoardDto chessBoardDto) {
        Map<PositionDto, String> chessBoard = chessBoardDto.getChessBoard();
        printPieces(chessBoard);
    }

    private static void printPieces(Map<PositionDto, String> chessBoard) {
        for (int i = 0; i < RANK_SIZE; i++) {
            final int rank = i;
            IntStream.range(0, FILE_SIZE)
                .forEach(file -> System.out.print(chessBoard.getOrDefault(new PositionDto(rank, file), NO_PIECE)));
            System.out.println();
        }
        System.out.println();
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
