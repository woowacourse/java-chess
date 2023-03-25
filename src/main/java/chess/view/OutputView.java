package chess.view;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.mapper.KindMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {

    public void startMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 불러오기 : load game번호 - 예. load 2");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Map<Position, Piece> board) {
        List<Position> allPositions = generateAllPositions();
        List<Rank> ranks = getRanks();
        printSingleRank(board, allPositions, ranks);
    }

    private List<Position> generateAllPositions() {
        List<Position> allPositions = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Position(file, rank)))
                .collect(Collectors.toList());
        return allPositions;
    }

    private List<Rank> getRanks() {
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        return ranks;
    }

    private void printSingleRank(final Map<Position, Piece> board, final List<Position> allPositions, final List<Rank> ranks) {
        for (Rank value : ranks) {
            allPositions.stream()
                    .filter(position -> position.getRank() == value)
                    .map(position -> board.getOrDefault(position, new Empty()))
                    .map(KindMapper::mapping)
                    .forEach(System.out::print);
            System.out.println();
        }
    }

    public void printGameId(final Long gameId) {
        System.out.printf("%d번 게임이 생성되었습니다.", gameId);
        System.out.println();
    }

    public void printScore(final double whiteScore, final double blackScore) {
        System.out.printf("white의 점수는 %.1f 입니다.", whiteScore);
        System.out.println();
        System.out.printf("black의 점수는 %.1f 입니다.", blackScore);
        System.out.println();
    }

    public void printErrorMessage(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public void printGuideMessage() {
        System.out.println("명령어를 다시 입력하세요");
    }
}
