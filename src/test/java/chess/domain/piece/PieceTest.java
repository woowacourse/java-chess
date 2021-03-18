package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.position.Source;
import chess.domain.position.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PieceTest {
    private static ChessBoard chessBoard;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());
        chessBoard.initializeBoard();
    }

    @DisplayName("Piece 정상 생성 테스트.")
    @Test
    void createPiece() {
        String blackK = "K";
        Piece blackKingPiece = King.from(blackK, A1);
        assertThat(blackKingPiece).isInstanceOf(King.class);

        String blackP = "P";
        Piece blackPawnPiece = Pawn.from(blackP, A2);
        assertThat(blackPawnPiece).isInstanceOf(Pawn.class);

        String blackQ = "Q";
        Piece blackQueenPiece = Queen.from(blackQ, B1);
        assertThat(blackQueenPiece).isInstanceOf(Queen.class);

        String blackN = "N";
        Piece blackKnightPiece = Knight.from(blackN, B2);
        assertThat(blackKnightPiece).isInstanceOf(Knight.class);

        String blackB = "B";
        Piece blackBishopPiece = Bishop.from(blackB, C1);
        assertThat(blackBishopPiece).isInstanceOf(Bishop.class);

        String blackR = "R";
        Piece blackRookPiece = Rook.from(blackR, C2);
        assertThat(blackRookPiece).isInstanceOf(Rook.class);
    }

    @DisplayName("Piece 생성 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", "C", "D", "e", "검프", "다니"})
    void createPieceException(String piece) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Bishop.from(piece, A1);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            King.from(piece, A2);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Knight.from(piece, B1);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Pawn.from(piece, B2);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Queen.from(piece, C1);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Rook.from(piece, C2);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);
    }

    @DisplayName("기물이 생성될 때, 위치값도 함께 초기화된다.")
    @Test
    void piecePositionTest() {
        assertThat(Bishop.from("b", A2).isSamePosition(A2)).isEqualTo(true);
        assertThat(King.from("k", A2).isSamePosition(A2)).isEqualTo(true);
        assertThat(Knight.from("n", A2).isSamePosition(A2)).isEqualTo(true);
        assertThat(Pawn.from("p", A2).isSamePosition(A2)).isEqualTo(true);
        assertThat(Queen.from("q", A2).isSamePosition(A2)).isEqualTo(true);
        assertThat(Rook.from("r", A2).isSamePosition(A2)).isEqualTo(true);
    }

    @DisplayName("source에서 선택한 말과 target의 말이 같은 색깔이면 예외가 발생한다.")
    @Test
    void samePieceChoiceException() {
        Source source = Source.valueOf(A2, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            source.move(Target.valueOf(source, B2, chessBoard), chessBoard);
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", B2);
    }

    @DisplayName("source에서 선택한 위치와 target의 위치가 같으면 에러가 발생한다.")
    @Test
    void samePositionException() {
        Source source = Source.valueOf(A2, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            source.move(Target.valueOf(source, A2, chessBoard), chessBoard);
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", A2);
    }
}
