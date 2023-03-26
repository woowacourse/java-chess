package chess.view;

import chess.dto.outputView.PrintBoardDto;
import chess.dto.outputView.PrintErrorMessageDto;
import chess.dto.outputView.PrintTotalScoreDto;
import chess.dto.outputView.PrintWinnerDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

public final class OutputView {

    public static final int BOARD_SIZE = 8;
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printInitialMessage() {
        println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n");
    }

    public void printBoard(final PrintBoardDto dto) {
        final List<String> pieces = dto.getPieces();
        int count = 0;
        for (String piece : pieces) {
            print(piece);
            count++;
            if (count % BOARD_SIZE == 0) {
                printLineSeparator();
            }
        }
    }

    public void printWinner(final PrintWinnerDto dto) {
        println(String.format(
                "왕이 죽었습니다. 승자는 %s팀 입니다.", dto.getWinnerTeam()));
    }

    public void printEndMessage() {
        println("체스 게임을 종료합니다.");
    }

    public void printTotalScore(final PrintTotalScoreDto dto) {
        final Optional<String> winnerOptional = findWinner(dto);
        if (winnerOptional.isEmpty()) {
            println("무승부입니다!");
            return;
        }
        final String winner = winnerOptional.get();
        println(String.format(
                "%s 승!!! 점수 : %f\n", winner, dto.getScore().get(winner)));

    }

    private Optional<String> findWinner(final PrintTotalScoreDto dto) {
        final Map<String, Double> score = dto.getScore();
        final Double whiteScore = score.get(WHITE.name());
        final Double blackScore = score.get(BLACK.name());
        if (whiteScore < blackScore) {
            return Optional.of(BLACK.name());
        }
        if (whiteScore > blackScore) {
            return Optional.of(WHITE.name());
        }
        return Optional.empty();
    }

    public void printErrorMessage(final PrintErrorMessageDto dto) {
        println(ERROR_MESSAGE_PREFIX + dto.getMessage());
    }

    private void print(final String input) {
        System.out.print(input);
    }

    private void println(final String input) {
        System.out.println(input);
    }

    private void printLineSeparator() {
        System.out.print(System.lineSeparator());
    }
}
