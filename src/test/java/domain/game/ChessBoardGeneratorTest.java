package domain.game;

import domain.piece.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardGeneratorTest {
    private static Map<Position, Piece> chessBoard;

    @BeforeAll
    static void generateChessBoard() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        chessBoard = chessBoardGenerator.generate();
    }

    @DisplayName("64개의 칸이 생성되었는지 확인한다.")
    @Test
    void shouldReturn64WhenGetSizeOfChessBoard() {
        assertThat(chessBoard).hasSize(64);
    }

    @DisplayName("White진영의 폰을 제외한 말들이 제자리에 생성됐는지 확인한다.")
    @Test
    void shouldWhitePiecesExcludePawnAreOnCorrectlyPositionWhenGenerateChessBoard() {
        assertThat(chessBoard.get(Position.of("a", "1"))).isInstanceOf(Rook.class);
        assertThat(chessBoard.get(Position.of("b", "1"))).isInstanceOf(Knight.class);
        assertThat(chessBoard.get(Position.of("c", "1"))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.get(Position.of("d", "1"))).isInstanceOf(Queen.class);
        assertThat(chessBoard.get(Position.of("e", "1"))).isInstanceOf(King.class);
        assertThat(chessBoard.get(Position.of("f", "1"))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.get(Position.of("g", "1"))).isInstanceOf(Knight.class);
        assertThat(chessBoard.get(Position.of("h", "1"))).isInstanceOf(Rook.class);
    }

    @DisplayName("White진영의 폰들이 제자리에 생성됐는지 확인한다.")
    @Test
    void shouldWhitePawnsAreOnCorrectlyPositionWhenGenerateChessBoard() {
        assertThat(chessBoard.get(Position.of("a", "2"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("b", "2"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("c", "2"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("d", "2"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("e", "2"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("f", "2"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("g", "2"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("h", "2"))).isInstanceOf(Pawn.class);
    }

    @DisplayName("Black진영의 폰을 제외한 말들이 제자리에 생성됐는지 확인한다.")
    @Test
    void shouldBlackPiecesExcludePawnAreOnCorrectlyPositionWhenGenerateChessBoard() {
        assertThat(chessBoard.get(Position.of("a", "8"))).isInstanceOf(Rook.class);
        assertThat(chessBoard.get(Position.of("b", "8"))).isInstanceOf(Knight.class);
        assertThat(chessBoard.get(Position.of("c", "8"))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.get(Position.of("d", "8"))).isInstanceOf(Queen.class);
        assertThat(chessBoard.get(Position.of("e", "8"))).isInstanceOf(King.class);
        assertThat(chessBoard.get(Position.of("f", "8"))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.get(Position.of("g", "8"))).isInstanceOf(Knight.class);
        assertThat(chessBoard.get(Position.of("h", "8"))).isInstanceOf(Rook.class);
    }

    @DisplayName("Black진영의 폰들이 제자리에 생성됐는지 확인한다.")
    @Test
    void shouldBlackPawnsAreOnCorrectlyPositionWhenGenerateChessBoard() {
        assertThat(chessBoard.get(Position.of("a", "7"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("b", "7"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("c", "7"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("d", "7"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("e", "7"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("f", "7"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("g", "7"))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of("h", "7"))).isInstanceOf(Pawn.class);
    }
}