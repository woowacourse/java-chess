package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.START;
import static chess.controller.Command.STATUS;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.controller.dto.CommandDTO;
import chess.controller.dto.ScoresDTO;
import chess.domain.board.Board;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.player.score.Scores;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    private static TeamColor currentTurnTeamColor = WHITE;
    private static Board board;

    public static void main(String[] args) {
        board = new Board(new BoardDefaultSetting());
        InputView.printGameStartMessage();
        Command command = getCommandDTO().command();
        if (command == START) {
            run();
        }
    }

    private static CommandDTO getCommandDTO() {
        return InputView.getCommand();
    }

    private static void run() {
        startChessGame(board);
        if (board.isKingDead()) {
            OutputView.printBoard(board);
            OutputView.printWinnerTeamColor(currentTurnTeamColor.opposite());
        }
    }

    private static void startChessGame(Board board) {
        Command command = START;
        while (isNotGameEnd(board, command)) {
            OutputView.printBoard(board);
            CommandDTO commandDTO = getCommandDTO();
            executeCommand(commandDTO);
            command = commandDTO.command();
        }
    }

    private static boolean isNotGameEnd(Board board, Command command) {
        return command != END && !board.isKingDead();
    }

    private static void executeCommand(CommandDTO commandDTO) {
        Command command = commandDTO.command();
        if (command == MOVE) {
            move(commandDTO.moveRoute());
            return;
        }
        if (command == STATUS) {
            Scores scores = board.scores();
            OutputView.printScores(new ScoresDTO(scores));
        }
    }

    private static void move(MoveRoute moveRoute) {
        board.move(moveRoute, currentTurnTeamColor);
        currentTurnTeamColor = currentTurnTeamColor.opposite();
    }
}
