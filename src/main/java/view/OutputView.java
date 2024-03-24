package view;

import domain.piece.Piece;
import domain.square.File;
import domain.square.Rank;
import domain.square.Square;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printStartHeader() {
        System.out.printf("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3%n""");
    }

    public void printChessBoard(final Map<Square, Piece> squarePieces) {
        final String board = Arrays.stream(Rank.values())
                .map(rank -> createOneRank(squarePieces, rank))
                .collect(Collectors.joining("\n"));

        System.out.println(board);
    }

    private String createOneRank(final Map<Square, Piece> squarePieces, final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> createOnePiece(squarePieces, rank, file))
                .collect(Collectors.joining());
    }

    private String createOnePiece(final Map<Square, Piece> squarePieces, final Rank rank, final File file) {
        final Square square = new Square(file, rank);

        if (squarePieces.containsKey(square)) {
            final Piece piece = squarePieces.get(square);
            return PieceFormat.findFormat(piece);
        }

        return PieceFormat.EMPTY_PIECE;
    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
