package chess;

import java.sql.SQLException;
import java.util.List;

import chess.domain.command.Command;
import chess.domain.player.User;
import chess.dto.LineDto;
import chess.dto.RowsDtoConverter;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) throws SQLException {
        ChessService service = new ChessService();
        OutputView.printBoard(service.getEmptyRowsDto());
        User blackUser = createBlackUser();
        User whiteUser = createWhiteUser();
        Command command;

        OutputView.printStart();
        do {
            command = receiveCommand();
            if (command.isStart()) {
                OutputView.printBoard(service.getRowsDto(blackUser, whiteUser));
            }
            if (command.isMove()) {
                executeMovement(service, command, blackUser);
            }
            if (command.isStatus()) {
                OutputView.printScore(service.calculateResult(blackUser));
            }
        } while (command.isNotEnd() && service.checkGameNotFinished(blackUser));
    }

    private static User createBlackUser() {
        try {
            return new User(InputView.receiveBlackUser());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return createBlackUser();
        }
    }

    private static User createWhiteUser() {
        try {
            return new User(InputView.receiveBlackUser());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return createBlackUser();
        }
    }

    private static Command receiveCommand() {
        try {
            return Command.from(InputView.receiveCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return receiveCommand();
        }
    }

    private static void executeMovement(ChessService service, Command command, User blackUser) {
        try {
            List<LineDto> rows = RowsDtoConverter.convertFrom(
                    service.move(blackUser, command.getSource(), command.getTarget()).getBoard());
            OutputView.printBoard(rows);
        } catch (RuntimeException | SQLException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }
}
