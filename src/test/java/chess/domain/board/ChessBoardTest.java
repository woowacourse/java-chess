package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChessBoardTest {
    @DisplayName("체스보드가 생성되면 32개의 말이 셋팅된다")
    @Test
    void initialBoard() {
        // when
        ChessBoard chessBoard = new ChessBoard();

        // then
        assertThat(chessBoard.getChessBoard()).hasSize(32);
    }

    @DisplayName("게임이 시작되면 White팀부터 게임을 시작한다")
    @Test
    void validateTurn() {
        // given
        ChessBoard chessBoard = new ChessBoard();
        Position blackSource = Position.of(File.B, Rank.SEVEN);
        Position blackTarget = Position.of(File.B, Rank.SIX);

        // when, then
        assertThatThrownBy(() -> chessBoard.move(blackSource, blackTarget))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source에 위치한 piece가 움직일 수 있는지 판단한다")
    @Test
    public void move() throws NoSuchFieldException, IllegalAccessException {
        // given
        ChessBoard chessBoard = new ChessBoard();

        Position source = Position.of(File.B, Rank.ONE);
        Position target = Position.of(File.C, Rank.THREE);

        // when
        chessBoard.move(source, target);

        // then
        Map<Position, Piece> board = chessBoard.getChessBoard();
        Piece piece = board.get(target);

        assertAll(
                () -> assertThat(board).containsKey(target),
                () -> assertThat(piece).isInstanceOf(Knight.class)
        );
    }

    @DisplayName("source에 위치한 piece가 움직일 수 있는지 판단한다")
    @Test
    void moveInvalidTarget() {
        // given
        ChessBoard chessBoard = new ChessBoard();

        Position source = Position.of(File.B, Rank.ONE);
        Position target = Position.of(File.C, Rank.EIGHT);

        // when, then
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source에 piece가 없으면 예외를 반환한다")
    @Test
    void moveInvalidSource() {
        // given
        ChessBoard chessBoard = new ChessBoard();

        Position source = Position.of(File.B, Rank.THREE);
        Position target = Position.of(File.B, Rank.FOUR);

        // when, then
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("해당되는 팀의 왕이 잡혔는지 확인한다")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,true", "WHITE,false"})
    void isKingCaptured(Color color, boolean expected) {
        // given
        ChessBoard chessBoard = new ChessBoard();
        Position blackKingPosition = Position.of(File.E, Rank.EIGHT);
        chessBoard.getChessBoard().remove(blackKingPosition);

        // when
        boolean result = chessBoard.isKingCaptured(color);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
