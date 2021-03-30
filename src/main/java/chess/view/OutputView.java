package chess.view;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.dto.PlayerResultDto;

import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class OutputView {
    private OutputView() {
    }

    public static void showChessBoard(final ChessBoard chessBoard) {
        Map<Position, Piece> board = chessBoard.boards();
        StringBuilder sb = new StringBuilder();

        for (int rank = chessBoard.getRankSize(); rank > 0; rank--) {
            final int finalRank = rank;
            IntStream.rangeClosed(1, chessBoard.getFileSize())
                    .mapToObj(file -> Position.valueOf(Rank.findByValue(finalRank), File.findByValue(file)))
                    .forEach(position -> {
                        sb.append(checkBoard(board, position));
                    });
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void showScore(final String name, final double calculateScore) {
        System.out.println(String.format("%s의 점수: %.1f", name, calculateScore));
        System.out.println();
    }

    private static String checkBoard(final Map<Position, Piece> chessBoard, final Position position) {
        if ("blank".equals(chessBoard.get(position).getPiece())){
            return ".";
        }
        return chessBoard.get(position).getPiece();
    }

    public static void showResult(final PlayerResultDto resultDto) {
        System.out.println(String.format("%s의 점수: %.1f", resultDto.getName(), resultDto.getScore()));
    }
}
