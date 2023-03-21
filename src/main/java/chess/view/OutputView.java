package chess.view;

import chess.model.position.File;
import chess.model.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR]";
    private static final int MAX_FILE_INDEX = 7;

    public void guideGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source 위치 target 위치 - 예. move b2 b3");
    }

    public void printChessBoard(final ChessBoardResponse chessBoardResponse) {
        final Map<String, String> squares = chessBoardResponse.getSquares();

        final List<String> rankMessage = convertToRankMessage(squares);

        System.out.println();
        for (int fileIndex = MAX_FILE_INDEX; fileIndex >= 0; fileIndex--) {
            System.out.println(rankMessage.get(fileIndex));
        }
    }

    private List<String> convertToRankMessage(final Map<String, String> squares) {
        return Arrays.stream(Rank.values())
                .map(rank -> convertToPieceMessageByRank(squares, rank))
                .collect(Collectors.toList());
    }

    private String convertToPieceMessageByRank(final Map<String, String> squareResponses, final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> mapToPieceMessage(squareResponses, rank, file))
                .collect(Collectors.joining());
    }

    private String mapToPieceMessage(final Map<String, String> squareResponses, final Rank rank, final File file) {
        final String key = file.name() + rank.value();

        return squareResponses.get(key);
    }

    public void printExceptionMessage(final String exceptionMessage) {
        final String message = ERROR_PREFIX + exceptionMessage;

        System.out.println();
        System.out.println(message);
    }
}
