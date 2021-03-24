package chess.controller;

import chess.domain.board.Cell;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.board.Coordinate;
import chess.domain.command.Command;
import chess.domain.command.CommandRequest;
import chess.domain.command.CommandTokens;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CommandTest {

    private static Stream<Arguments> getCommandInputAndEnum() {
        return Stream.of(Arguments.of("start", Command.START),
                Arguments.of("move", Command.MOVE),
                Arguments.of("end", Command.END),
                Arguments.of("status", Command.STATUS));
    }

    @DisplayName("명령어에 해당하는 Enum을 탐색한다")
    @ParameterizedTest
    @MethodSource("getCommandInputAndEnum")
    void findCommandEnum(String commandInput, Command expectCommand) {
        Command command = Command.findCommand(commandInput);

        assertThat(command).isEqualTo(expectCommand);
    }

    @DisplayName("정의되지 않은 명령어 입력은 예외를 발생시킨다")
    @Test
    void throwExceptionWhenInvalidCommand() {
        assertThatCode(() -> Command.findCommand("invalid"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("명령어를 잘못 입력했습니다.");
    }

    @DisplayName("Start와 End 명령어는 별도의 execute 로직이 없으며 호출시 예외가 발생한다.")
    @ParameterizedTest
    @EnumSource(value = Command.class, names = {"START", "END"})
    void cannotExecuteLogic(Command command) {
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
        CommandRequest commandRequest = new CommandRequest(TeamType.WHITE, CommandTokens.from(Arrays.asList("start")));

        assertThatCode(() -> command.execute(chessBoard, commandRequest))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Status 명령어는 명령 실행 후 계산 결과를 반환한다.")
    @Test
    void status() {
        Command command = Command.STATUS;
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
        CommandRequest commandRequest = new CommandRequest(TeamType.WHITE, CommandTokens.from(Arrays.asList("status")));

        Result result = command.execute(chessBoard, commandRequest);
        double blackTeamScore = result.getBlackTeamScore();
        double whiteTeamScore = result.getWhiteTeamScore();

        assertThat(blackTeamScore).isEqualTo(38.0);
        assertThat(whiteTeamScore).isEqualTo(38.0);
    }

    @DisplayName("Move 명령어는 명령대로 기물의 위치를 변경시킨다.")
    @Test
    void move() {
        Command command = Command.MOVE;
        Map<Coordinate, Cell> cells = ChessBoardGenerator.generateDefaultChessBoard();
        ChessBoard chessBoard = new ChessBoard(cells);
        CommandRequest commandRequest = new CommandRequest(TeamType.WHITE, CommandTokens.from(Arrays.asList("move", "b2", "b4")));

        command.execute(chessBoard, commandRequest);
        boolean isEmptyOnB2 = cells.get(Coordinate.from("b2")).isEmpty();
        boolean isEmptyOnB4 = cells.get(Coordinate.from("b4")).isEmpty();

        assertThat(isEmptyOnB2).isTrue();
        assertThat(isEmptyOnB4).isFalse();
    }
}
