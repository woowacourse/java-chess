package chess.controller;

import static chess.domain.game.type.Command.END;
import static chess.domain.game.type.Command.MOVE;
import static chess.domain.game.type.Command.START;
import static chess.domain.game.type.Command.STATUS;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.controller.dto.CommandDTO;
import chess.controller.dto.ScoresDTO;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.game.ChessGame;
import chess.domain.game.MoveCommand;
import chess.domain.game.type.Command;
import chess.domain.player.score.Scores;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    private static TeamColor currentTurnTeamColor = WHITE;
    private static ChessGame chessGame;

    public static void main(String[] args) {
        chessGame = new ChessGame(new BoardDefaultSetting());
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
            CommandDTO commandDTO = getCommandDTO();
            executeCommand(commandDTO);
            command = commandDTO.command();
        }
    }

    private static boolean isNotGameEnd(Command command) {
        return command != END && !chessGame.isKingDead();
    }

    private static void executeCommand(CommandDTO commandDTO) {
        Command command = commandDTO.command();
        if (command == MOVE) {
            move(commandDTO.moveRoute());
            return;
        }
        if (command == STATUS) {
            Scores scores = chessGame.scores();
            OutputView.printScores(new ScoresDTO(scores));
        }
    }

    private static void move(MoveRoute moveRoute) {
        chessGame.move(new MoveCommand(currentTurnTeamColor, moveRoute));
        currentTurnTeamColor = currentTurnTeamColor.opposite();
    }
}
