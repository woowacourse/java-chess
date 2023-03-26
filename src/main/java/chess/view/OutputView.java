package chess.view;

import chess.dto.outputView.PrintBoardDto;
import chess.dto.outputView.PrintEndMessageDto;
import chess.dto.outputView.PrintErrorMessageDto;
import chess.dto.outputView.PrintInitialMessageDto;
import chess.dto.outputView.PrintTotalScoreDto;
import chess.dto.outputView.PrintWinnerDto;

import java.util.List;
import java.util.Optional;

public final class OutputView {

    public static final int BOARD_SIZE = 8;

    public void printInitialMessage(final PrintInitialMessageDto dto) {
        print(dto.getMessage());
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
        print(dto.getWinnerTeam());
    }

    public void printEndMessage(final PrintEndMessageDto dto) {
        print(dto.getMessage());
    }

    public void printTotalScore(final PrintTotalScoreDto dto) {
        final Optional<String> winnerOptional = dto.whosWinner();
        if (winnerOptional.isEmpty()) {
            print("무승부입니다!");
            return;
        }
        final String winner = winnerOptional.get();
        print(String.format(
                "%s 승!!! 점수 : %f\n", winner, dto.getScore().get(winner)));

    }

    public void printErrorMessage(final PrintErrorMessageDto dto) {
        print(dto.getMessage());
    }

    private void print(final String input) {
        System.out.println(input);
    }

    private void printLineSeparator() {
        System.out.print(System.lineSeparator());
    }
}
