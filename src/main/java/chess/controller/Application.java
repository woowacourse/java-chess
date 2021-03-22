package chess.controller;

import static chess.controller.type.Command.END;
import static chess.controller.type.Command.MOVE;
import static chess.controller.type.Command.START;
import static chess.controller.type.Command.STATUS;

import chess.controller.dto.request.CommandRequestDTO;
import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.type.Command;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.game.ChessGame;
import chess.domain.player.score.Scores;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static final String WHITE_TEAM_COLOR = "white";
    public static final String BLACK_TEAM_COLOR = "black";

    private static String currentTurnTeamColor = WHITE_TEAM_COLOR;
    private static ChessGame chessGame;

    public static void main(String[] args) {
        chessGame = new ChessGame(new BoardDefaultSetting());
        InputView.printGameStartMessage();
        String commandInput = getCommandRequestDTO().getCommandInput();
        if (Command.of(commandInput) == START) {
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
            changeCurrentTurnTeamColorToNextTurn();
            OutputView.printWinnerTeamColor(currentTurnTeamColor);
        }
    }

    private static void startChessGame() {
        Command command = START;
        while (!isGameEnd(command)) {
            OutputView.printBoard(chessGame.board());
            CommandRequestDTO commandRequestDTO = getCommandRequestDTO();
            command = Command.of(commandRequestDTO.getCommandInput());
            executeCommand(command, commandRequestDTO);
        }
    }

    private static boolean isGameEnd(Command command) {
        return command == END || chessGame.isKingDead();
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
        changeCurrentTurnTeamColorToNextTurn();
    }

    private static void changeCurrentTurnTeamColorToNextTurn() {
        if (currentTurnTeamColor.equals(WHITE_TEAM_COLOR)) {
            currentTurnTeamColor = BLACK_TEAM_COLOR;
            return;
        }
        currentTurnTeamColor = WHITE_TEAM_COLOR;
    }
}
