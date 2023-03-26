package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static chess.PositionFixture.*;
import static chess.PositionFixture.E_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MoveCommandTest {

    private ChessGame chessGame;

    @BeforeEach
    void init() {
        chessGame = new ChessGame(BoardFactory.createBoard());
    }

    @Test
    void MoveCommand의_타입을_확인할_수_있다() {

        Command moveCommand = new MoveCommand(chessGame);

        assertThat(moveCommand.isSameType(CommandType.MOVE)).isTrue();
    }

    @Test
    void moveCommand의_ChessGame판을_확인할_수_있다() {
        Command moveCommand = new MoveCommand(chessGame);

        assertThat(moveCommand.getChessGameBoards().get(B_2)).isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"move b2 b3", "move   b2 b3 ", " move b2 b3", "move b2   b3   ", " Move b2 b3  "})
    void move를_입력받으면_MoveCommand_객체가_반환된다(String command) {
        Command moveCommand = new MoveCommand(chessGame);
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        Command result = moveCommand.execute(input);


        assertAll(
                () -> assertThat(result.isSameType(CommandType.MOVE)).isTrue(),
                () -> assertThat(result.getChessGameBoards().get(B_3)).isInstanceOf(Pawn.class),
                () -> assertThat(result.getChessGameBoards().get(B_2)).isInstanceOf(Empty.class)
        );
    }

    @Test
    void move명령어를_했을_때_King을_잡으면_StatusCommand_객체가_반환된다() {
        chessGame.movePiece(E_2, E_4);
        chessGame.movePiece(F_7, F_5);
        chessGame.movePiece(E_4, F_5);
        chessGame.movePiece(G_7, G_5);
        chessGame.movePiece(D_1, H_5);
        chessGame.movePiece(C_7, C_6);

        Command moveCommand = new MoveCommand(chessGame);
        Command result = moveCommand.execute(List.of("move", "h5", "e8"));

        assertThat(result.isSameType(CommandType.STATUS)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"move a2", "MOVE t3"})
    void move명령어는_출발지와_도착지에_대한_정보를_입력받지_않으면_예외가_발생한다(String command) {
        Command moveCommand = new MoveCommand(chessGame);
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> moveCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("move 명령어는 '도착지'와 '출발지'에 대한 정보를 입력해야합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"move a3 t2", "move t2 y3"})
    void move명령어는_잘못된_열_정보를_입력받으면_예외가_발생한다(String command) {
        Command moveCommand = new MoveCommand(chessGame);
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> moveCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 열 번호를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"move aa a2", "move a9 a7"})
    void move명령어는_잘못된_행_정보를_입력받으면_예외가_발생한다(String command) {
        Command moveCommand = new MoveCommand(chessGame);
        List<String> input = Arrays.stream(command.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> moveCommand.execute(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 행 번호를 입력해주세요.");
    }

    @Test
    void move_상태일_때_end를_입력하면_EndCommand_객체가_반환된다() {
        Command moveCommand = new MoveCommand(chessGame);
        List<String> input = List.of("end");

        Command result = moveCommand.execute(input);

        assertThat(result.isSameType(CommandType.END)).isTrue();
    }
}
