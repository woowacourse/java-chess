package chess.controller;

import static chess.controller.type.Command.END;
import static chess.controller.type.Command.MOVE;
import static chess.controller.type.Command.START;
import static chess.controller.type.Command.STATUS;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.controller.dto.request.CommandRequestDTO;
import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.type.Command;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.game.ChessGame;
import chess.domain.player.score.Scores;
import chess.domain.player.type.TeamColor;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    private static TeamColor currentTurnTeamColor = WHITE;
    private static ChessGame chessGame;

    public static void main(String[] args) {
        chessGame = new ChessGame(new BoardDefaultSetting());
        InputView.printGameStartMessage();
        Command command = Command.of(getCommandRequestDTO().getCommandInput());
        if (command == START) {
            run();
        }
    }

    private static CommandRequestDTO getCommandRequestDTO() {
        return InputView.getCommandRequest();
    }

    private static void run() {
        startChessGame();
        if (chessGame.isKingDead()) {
            OutputView.printBoard(chessGame.board());
            OutputView.printWinnerTeamColor(currentTurnTeamColor.opposite());
        }
    }

    private static void startChessGame() {
        Command command = START;
        while (isNotGameEnd(command)) {
            OutputView.printBoard(chessGame.board());
            CommandRequestDTO commandRequestDTO = getCommandRequestDTO();
            command = Command.of(commandRequestDTO.getCommandInput());
            executeCommand(command, commandRequestDTO);
        }
    }

    private static boolean isNotGameEnd(Command command) {
        return command != END && !chessGame.isKingDead();
    }

    private static void executeCommand(Command command, CommandRequestDTO commandRequestDTO) {
        if (command == MOVE) {
            move(commandRequestDTO);
            return;
        }
        if (command == STATUS) {
            Scores scores = chessGame.scores();
            OutputView.printScores(scores);
        }
    }

    private static void move(CommandRequestDTO commandRequestDTO) {
        chessGame.move(new MoveRequestDTO(currentTurnTeamColor, commandRequestDTO));
        currentTurnTeamColor = currentTurnTeamColor.opposite();
    }
}
