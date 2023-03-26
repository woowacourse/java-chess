package chess.status;

import chess.chessboard.*;
import chess.chessgame.ChessGame;
import chess.controller.Command;
import chess.controller.PrintAction;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RunningTest {

    private final PrintAction dummyAction = ignored -> {
    };
    private ChessGame chessGame;

    @Nested
    @DisplayName("playGame 메서드는")
    class playGame {

        @BeforeEach
        void 게임생성() {
            final Pawn whitePawn = Pawn.getPawnsOf(Side.WHITE)
                                       .get(0);
            final King blackKing = King.getKingOf(Side.BLACK);
            final EmptyPiece emptyPiece = EmptyPiece.getInstance();
            final Square B2 = Square.of(Rank.TWO, File.B);
            final Square B3 = Square.of(Rank.THREE, File.B);
            final Square C3 = Square.of(Rank.THREE, File.C);

            chessGame = new ChessGame(new ChessBoard(Map.of(B2, whitePawn, C3, blackKing, B3, emptyPiece)));
        }

        @ParameterizedTest(name = "커맨드 타입이 START이면 예외를 발생시킨다")
        @ValueSource(strings = {"start"})
        void it_throws_exception1(String commandString) {
            final Command command = Command.from(List.of(commandString));

            assertThatThrownBy(() -> new Running(chessGame).playGame(command, dummyAction))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("게임은 이미 진행 중입니다");
        }

        @Test
        @DisplayName("커맨드 타입이 MOVE이고 이동에 성공하면 Running을 반환한다")
        void it_returns_running1() {
            final Command command = Command.from(List.of("move", "b2", "b3"));

            assertThat(new Running(chessGame).playGame(command, dummyAction)).isInstanceOf(Running.class);
        }

        @Test
        @DisplayName("커맨드 타입이 STATUS라면 Running을 반환한다")
        void it_returns_running2() {
            final Command command = Command.from(List.of("status"));

            assertThat(new Running(chessGame).playGame(command, dummyAction)).isInstanceOf(Running.class);
        }

        @Test
        @DisplayName("왕이 죽으면 FINISHED를 반환한다")
        void it_returns_finished() {
            final Command command = Command.from(List.of("move", "b2", "c3"));

            assertThat(new Running(chessGame).playGame(command, dummyAction)).isInstanceOf(Finished.class);
        }
    }
}
