package chess.controller;

import chess.domains.board.Board;
import chess.domains.piece.PieceColor;
import chess.domains.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class GameController {
    public static final String DELIMITER = " ";
    public static final int SOUCE_POSITION = 1;
    public static final int TARGET_POSITION = 2;

    public static void start() {
        Board board = new Board();
        OutputView.printBoard(board.showBoard());

        PieceColor teamColor = PieceColor.WHITE;

        while (!board.isGameOver()) {
            OutputView.printTeamColor(teamColor);
            String command = InputView.inputCommand();
            if (Command.isStatus(command)) {
                OutputView.printScore(board.calculateScore(teamColor));
            }
            if (Command.isMove(command)) {
                String[] moveCommand = command.split(DELIMITER);
                Position source = Position.ofPositionName(moveCommand[SOUCE_POSITION]);
                Position target = Position.ofPositionName(moveCommand[TARGET_POSITION]);

                board.move(source, target, teamColor);

                OutputView.printBoard(board.showBoard());

                teamColor = teamColor.changeTeam();
            }
        }

    }
}
