package domain.game;

import static domain.game.File.A;
import static domain.game.File.B;
import static domain.game.File.C;
import static domain.game.File.D;
import static domain.game.File.E;
import static domain.game.File.F;
import static domain.game.File.G;
import static domain.game.File.H;
import static domain.game.Rank.EIGHT;
import static domain.game.Rank.ONE;
import static domain.game.Rank.SEVEN;
import static domain.game.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardGeneratorTest {
    private static Map<Position, Piece> chessBoard;

    @BeforeAll
    static void generateChessBoard() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        chessBoard = chessBoardGenerator.generate();
    }

    @DisplayName("64개의 칸이 생성되었는지 확인한다.")
    @Test
    void shouldReturnTrueWhenCreateChessBoardOf64Position() {
        assertThat(chessBoard).hasSize(64);
    }

    @DisplayName("White 진영의 폰을 제외한 말들이 알맞은 자리에 생성됐는지 확인한다.")
    @Test
    void shouldWhitePiecesExcludePawnAreOnCorrectlyPositionWhenGenerateChessBoard() {
        assertThat(chessBoard.get(Position.of(A, ONE))).isInstanceOf(Rook.class);
        assertThat(chessBoard.get(Position.of(B, ONE))).isInstanceOf(Knight.class);
        assertThat(chessBoard.get(Position.of(C, ONE))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.get(Position.of(D, ONE))).isInstanceOf(Queen.class);
        assertThat(chessBoard.get(Position.of(E, ONE))).isInstanceOf(King.class);
        assertThat(chessBoard.get(Position.of(F, ONE))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.get(Position.of(G, ONE))).isInstanceOf(Knight.class);
        assertThat(chessBoard.get(Position.of(H, ONE))).isInstanceOf(Rook.class);
    }

    @DisplayName("White 진영의 폰이 알맞은 자리에 생성됐는지 확인한다.")
    @Test
    void shouldWhitePawnsAreOnCorrectlyPositionWhenGenerateChessBoard() {
        assertThat(chessBoard.get(Position.of(A, TWO))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(B, TWO))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(C, TWO))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(D, TWO))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(E, TWO))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(F, TWO))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(G, TWO))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(H, TWO))).isInstanceOf(Pawn.class);
    }

    @DisplayName("Black 진영의 폰을 제외한 말들이 알맞은 자리에 생성됐는지 확인한다.")
    @Test
    void shouldBlackPiecesExcludePawnAreOnCorrectlyPositionWhenGenerateChessBoard() {
        assertThat(chessBoard.get(Position.of(A, EIGHT))).isInstanceOf(Rook.class);
        assertThat(chessBoard.get(Position.of(B, EIGHT))).isInstanceOf(Knight.class);
        assertThat(chessBoard.get(Position.of(C, EIGHT))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.get(Position.of(D, EIGHT))).isInstanceOf(Queen.class);
        assertThat(chessBoard.get(Position.of(E, EIGHT))).isInstanceOf(King.class);
        assertThat(chessBoard.get(Position.of(F, EIGHT))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.get(Position.of(G, EIGHT))).isInstanceOf(Knight.class);
        assertThat(chessBoard.get(Position.of(H, EIGHT))).isInstanceOf(Rook.class);
    }

    @DisplayName("Black 진영의 폰들이 알맞은 자리에 생성됐는지 확인한다.")
    @Test
    void shouldBlackPawnsAreOnCorrectlyPositionWhenGenerateChessBoard() {
        assertThat(chessBoard.get(Position.of(A, SEVEN))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(B, SEVEN))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(C, SEVEN))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(D, SEVEN))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(E, SEVEN))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(F, SEVEN))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(G, SEVEN))).isInstanceOf(Pawn.class);
        assertThat(chessBoard.get(Position.of(H, SEVEN))).isInstanceOf(Pawn.class);
    }
}
