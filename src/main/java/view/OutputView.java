package view;

import domain.piece.Piece;
import domain.point.File;
import domain.point.Rank;

import java.util.*;
import java.util.stream.Collectors;

public class OutputView {
    public void printStatus(List<List<Piece>> status) {
        Collections.reverse(status);

        Iterator<Rank> ranks = getRankIterator();

        for (List<Piece> pieces : status) {
            printRankInformation(ranks, pieces);
        }

        printFileInformation();
    }

    private static void printRankInformation(Iterator<Rank> ranks, List<Piece> pieces) {
        StringBuffer stringBuffer = new StringBuffer();
        assignPieceInformationToStringBuffer(pieces, stringBuffer);
        assignRankInformationToStringBuffer(ranks, stringBuffer);
        System.out.println(stringBuffer);
    }

    private static void printFileInformation() {
        StringBuffer stringBuffer = new StringBuffer();
        assignFileInformationToStringBuffer(stringBuffer);
        System.out.println();
        System.out.println(stringBuffer);
    }

    private static void assignFileInformationToStringBuffer(StringBuffer stringBuffer) {
        List<File> files = Arrays.stream(File.values()).collect(Collectors.toList());
        files.forEach(file -> stringBuffer.append(file.getSymbol()));
    }

    private static void assignRankInformationToStringBuffer(Iterator<Rank> iterator, StringBuffer stringBuffer) {
        Rank rank = iterator.next();
        stringBuffer.append(String.format("  %s", rank.getSymbol()));

        if (rank == Rank.EIGHT) {
            stringBuffer.append("  (rank 8)");
        }

        if (rank == Rank.ONE) {
            stringBuffer.append("  (rank 1)");
        }
    }

    private static void assignPieceInformationToStringBuffer(List<Piece> pieces, StringBuffer stringBuffer) {
        pieces.forEach(piece -> stringBuffer.append(piece.getSymbol()));
    }

    private static Iterator<Rank> getRankIterator() {
        List<Rank> ranks = Arrays.stream(Rank.values()).collect(Collectors.toList());
        Collections.reverse(ranks);
        return ranks.iterator();
    }

    public void printAskingBootingCommandMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printExceptionMessage(String message) {
        System.out.printf("[ERROR] %s%n", message);
    }

    public void printScoreStatus(float blackScore, float whiteScore) {
        System.out.println("> 현재 체스 점수");
        System.out.printf("> BLACK 점수 : %f%n", blackScore);
        System.out.printf("> WHITE 점수 : %f%n", whiteScore);

        Optional<Winner> winnerOptional = Winner.of(blackScore, whiteScore);
        winnerOptional.ifPresentOrElse(winner ->
                System.out.printf("> %f점 차이로 %s가 앞서가는 중!%n", winner.getScore(), winner.getName()),
                () -> System.out.println("> 한치 앞도 알 수 없는 치열한 접점 중 !"));

    }
}
