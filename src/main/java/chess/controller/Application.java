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
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static final String WHITE_TEAM_COLOR = "white";
    private static final String BLACK_TEAM_COLOR = "black";

    private static String currentTurnTeamColor = WHITE_TEAM_COLOR;

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame(new BoardDefaultSetting());
        InputView.printGameStartMessage();
        String commandInput = getCommandRequestDTO().getCommandInput();
        if (Command.of(commandInput) == START) {
            run(chessGame);
        }
    }

    private static CommandRequestDTO getCommandRequestDTO() {
        return InputView.getCommandRequest();
    }

    private static void run(ChessGame chessGame) {
        startChessGame(chessGame);
        if (chessGame.isKingDead()) {
            OutputView.printBoard(chessGame.boardStatus());
            changeCurrentTurnTeamColorToNextTurn();
            OutputView.printWinnerTeamColor(currentTurnTeamColor);
        }
    }

    private static void startChessGame(ChessGame chessGame) {
        Command command = START;
        while (!isGameEnd(command, chessGame)) {
            OutputView.printBoard(chessGame.boardStatus());
            CommandRequestDTO commandRequestDTO = getCommandRequestDTO();
            command = Command.of(commandRequestDTO.getCommandInput());
            executeCommand(chessGame, new CommandToExecute(command, commandRequestDTO));
        }
    }

    private static boolean isGameEnd(Command command, ChessGame chessGame) {
        return command == END || chessGame.isKingDead();
    }

    private static void executeCommand(ChessGame chessGame, CommandToExecute commandToExecute) {
        Command command = commandToExecute.getCommand();
        if (command == MOVE) {
            move(chessGame, commandToExecute.getCommandRequestDTO());
            return;
        }
        if (command == STATUS) {
            OutputView.printScores(chessGame.getScores());
        }
    }

    private static void move(ChessGame chessGame, CommandRequestDTO commandRequestDTO) {
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
