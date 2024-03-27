package model.state;

import model.chessboard.ChessBoard;
import model.piece.Color;
import model.piece.Piece;
import model.piece.role.Pawn;
import model.position.File;
import model.position.Position;
import model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ChessStateTest {

    @Test
    @DisplayName("어떤 진영의 차례인지 알 수 있다.")
    void checkSameFaction() {
        ChessState chessState = new ChessState();
        assertThatThrownBy(() -> chessState.checkTheTurn(new Piece(new Pawn(Color.BLACK))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 해당 진영의 차례가 아닙니다.");
    }

    @Test
    @DisplayName("상대방에게 차례를 넘길 수 있다.")
    void passTheTurn() {
        ChessState chessState = new ChessState();
        chessState.passTheTurn();
        assertThatThrownBy(() -> chessState.checkTheTurn(new Piece(new Pawn(Color.WHITE))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 해당 진영의 차례가 아닙니다.");
    }

    @Test
    @DisplayName("체크가 된 경우 체크를 해소할 수 있는 이동만 가능하다.")
    void validateCheck() {
        ChessBoard chessBoard = makeCheckStateChessBoard();
        assertAll(
                () -> assertThatThrownBy(() -> chessBoard.move(Position.of(File.H, Rank.SIX), Position.of(File.H, Rank.FIVE)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("해당 방향으로의 이동은 Check를 해소할 수 없습니다."),
                () -> assertThatCode(() -> chessBoard.move(Position.of(File.D, Rank.EIGHT), Position.of(File.E, Rank.SEVEN)))
                        .doesNotThrowAnyException()
        );
    }

    private ChessBoard makeCheckStateChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.move(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.FOUR));
        chessBoard.move(Position.of(File.A, Rank.SEVEN), Position.of(File.A, Rank.SIX));
        chessBoard.move(Position.of(File.A, Rank.ONE), Position.of(File.A, Rank.THREE));
        chessBoard.move(Position.of(File.A, Rank.SIX), Position.of(File.A, Rank.FIVE));
        chessBoard.move(Position.of(File.A, Rank.THREE), Position.of(File.E, Rank.THREE));
        chessBoard.move(Position.of(File.H, Rank.SEVEN), Position.of(File.H, Rank.SIX));
        chessBoard.move(Position.of(File.E, Rank.THREE), Position.of(File.E, Rank.SEVEN));
        return chessBoard;
    }
}
