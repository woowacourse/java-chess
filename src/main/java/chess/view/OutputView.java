package chess.view;

import chess.domain.piece.Team;
import chess.dto.outputView.PrintBoardDto;
import chess.dto.outputView.PrintEndMessageDto;
import chess.dto.outputView.PrintErrorMessageDto;
import chess.dto.outputView.PrintInitialMessageDto;
import chess.dto.outputView.PrintTotalScoreDto;
import chess.dto.outputView.PrintWinnerDto;

import java.util.List;
import java.util.Optional;

public final class OutputView {


    public void printInitialMessage(final PrintInitialMessageDto dto) {
        System.out.println(dto.getMessage());
    }

    public void printBoard(final PrintBoardDto dto) {
        final List<String> pieces = RenderUtil.renderBoard(dto.getBoard());
        int count = 0;
        for (String piece : pieces) {
            System.out.print(piece);
            count++;
            if (count % 8 == 0) {
                System.out.println();
            }
        }
    }

    public void printWinner(final PrintWinnerDto dto) {
        System.out.printf("왕이 죽었습니다. 승자는 %s팀 입니다.", dto.getWinnerTeam());
    }

    public void printEndMessage(final PrintEndMessageDto dto) {
        System.out.println(dto.getMessage());
    }

    public void printTotalScore(final PrintTotalScoreDto dto) {
        final Optional<Team> winnerOptional = dto.whosWinner();
        if (winnerOptional.isEmpty()) {
            System.out.println("무승부입니다!");
            return;
        }
        final Team winner = winnerOptional.get();
        System.out.printf("%s 승!!! 점수 : %f\n", winner.name(), dto.getScore().get(winner));
    }

    public void printErrorMessage(final PrintErrorMessageDto dto) {
        System.out.println(dto.getMessage());
    }
}
