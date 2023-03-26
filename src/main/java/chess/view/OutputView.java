package chess.view;

import java.util.List;
import java.util.stream.Collectors;

import chess.dto.ChessBoardDto;
import chess.dto.PieceDto;
import chess.piece.Team;

public class OutputView {

    public void printInitGame() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(final ChessBoardDto chessBoardDto) {
        final List<PieceDto> pieceDtos = chessBoardDto.getPieceDtos();

        for (int i = 0; i < 64; i += 8) {
            final List<String> origin = pieceDtos.stream()
                    .map(PieceDto::getView)
                    .collect(Collectors.toList());
            final List<String> strings = origin.subList(i, Math.min(i + 8, origin.size()));
            strings.forEach(System.out::print);
            System.out.println();
        }
        System.out.println();
    }

    public void printScore(final double scoreOfWhite, final double scoreOfBlack) {
        System.out.println("백팀의 점수 : " + scoreOfWhite + " 흑팀의 점수: " + scoreOfBlack);
    }

    public void printWinner(final Team team) {
        System.out.println("현재 우승팀은!!! " + parseTeam(team));
    }

    private String parseTeam(final Team team) {
        if (team == Team.WHITE) {
            return "백팀";
        }
        if (team == Team.BLACK) {
            return "흑팀";
        }
        return "무승부";
    }
}
