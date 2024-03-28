package view;

import domain.ChessGameResult;
import domain.piece.Piece;
import domain.square.File;
import domain.square.Rank;
import domain.square.Square;
import dto.PlayerGameRecordDto;
import view.format.PieceFormat;
import view.format.WinStatusFormat;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printGameOption(final int gameId, final String blackPlayerName, final String whitePlayerName) {
        System.out.printf("""
                > %d번 게임을 시작합니다.
                > 게임 점수 보기 : status
                > 게임 끝내기 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3
                > 프로그램 종료 : quit
                흑 : %s VS 백 : %s%n%n""", gameId, blackPlayerName, whitePlayerName);
    }

    public static void printChessBoard(final Map<Square, Piece> squarePieces) {
        final String board = Arrays.stream(Rank.values())
                .map(rank -> createOneRank(squarePieces, rank))
                .collect(Collectors.joining("\n"));

        System.out.println(board);
    }

    private static String createOneRank(final Map<Square, Piece> squarePieces, final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> createOnePiece(squarePieces, rank, file))
                .collect(Collectors.joining());
    }

    private static String createOnePiece(final Map<Square, Piece> squarePieces, final Rank rank, final File file) {
        final Square square = new Square(file, rank);

        if (squarePieces.containsKey(square)) {
            final Piece piece = squarePieces.get(square);
            return PieceFormat.formatOf(piece);
        }

        return PieceFormat.EMPTY_PIECE;
    }

    public static void printStatus(final ChessGameResult chessGameResult) {
        System.out.printf("%n블랙 : %.1f%n화이트 : %.1f%n승패 : %s%n%n",
                chessGameResult.getBlackScore(), chessGameResult.getWhiteScore(),
                WinStatusFormat.formatOf(chessGameResult.getWinStatus()));
    }

    public static void printGameRecord(final PlayerGameRecordDto gameRecord) {
        System.out.printf("전체 전적 : %d승 %d패 %d무%n%n",
                gameRecord.winCount(), gameRecord.loseCount(), gameRecord.drawCount());
    }

    public static void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
