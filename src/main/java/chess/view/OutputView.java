package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.dto.ChessBoardDto;
import chess.view.dto.PlayerResultDto;

import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class OutputView {
    private static final int RANK = 8;
    private static final int FILE = 8;

    private OutputView() {
    }

    public static void showExceptionMessage(final RuntimeException runtimeException) {
        System.out.println("[ERROR]: " + runtimeException.getMessage());
    }

    public static void showChessBoard(final ChessBoardDto chessBoardDto) {
        StringBuilder sb = new StringBuilder();
        for (int rank = RANK; rank > 0; rank--) {
            final int finalRank = rank;
            IntStream.rangeClosed(1, FILE)
                    .mapToObj(file -> Position.valueOf(Rank.findByValue(finalRank), File.findByValue(file)))
                    .forEach(position -> {
                        sb.append(checkBoard(chessBoardDto.getChessBoard(), position));
                    });
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void showScore(final String name, final double calculateScore) {
        System.out.printf("%s의 점수: %.1f%n", name, calculateScore);
        System.out.println();
    }

    private static String checkBoard(final Map<Position, Piece> chessBoard, final Position position) {
        if (Objects.isNull(chessBoard.get(position))) {
            return ".";
        }
        return chessBoard.get(position).getPiece();
    }

    public static void showResult(final PlayerResultDto resultDto) {
        System.out.printf("%s의 점수: %.1f%n", resultDto.getName(), resultDto.getScore());
    }
}
