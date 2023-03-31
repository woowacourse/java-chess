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

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinishedTest {
    private final PrintAction dummyAction = ignored -> {
    };

    @Nested
    @DisplayName("playGame 메서드는")
    class playGame {

        ChessGame chessGame;

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

        @Test
        @DisplayName("커맨드가 move이면 예외를 발생시킨다")
        void it_throws_exception() {
            final Command command = Command.from(List.of("move", "b2", "b3"));

            assertThatThrownBy(() -> new Finished(chessGame).playGame(command, dummyAction))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("게임이 종료되었습니다");
        }

        @Test
        @DisplayName("커맨드가 start이면 예외를 발생시킨다")
        void it_throws_exception2() {
            final Command command = Command.from(List.of("start"));

            assertThatThrownBy(() -> new Finished(chessGame).playGame(command, dummyAction))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("게임이 종료되었습니다");
        }

        @Test
        @DisplayName("커맨드가 status라면 finished를 반환한다")
        void it_shows_winner() {
            final Command command = Command.from(List.of("status"));

            assertThat(new Finished(chessGame).playGame(command, dummyAction)).isInstanceOf(Finished.class);
        }
    }
}