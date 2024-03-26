package domain;

import static domain.Position.A1;
import static domain.Position.A3;
import static domain.Position.A4;
import static domain.Position.A6;
import static domain.Position.B1;
import static domain.Position.B2;
import static domain.Position.B6;
import static domain.Position.C1;
import static domain.Position.C2;
import static domain.Position.C3;
import static domain.Position.C6;
import static domain.Position.D1;
import static domain.Position.D2;
import static domain.Position.D3;
import static domain.Position.D4;
import static domain.Position.D5;
import static domain.Position.D7;
import static domain.Position.D8;
import static domain.Position.E1;
import static domain.Position.E3;
import static domain.Position.F2;
import static domain.Position.H1;
import static domain.Team.BLACK;
import static domain.Team.WHITE;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardTest {

    public static Stream<Arguments> moveSuccessParameters() {
        return Stream.of(
                Arguments.of(D2, D4), Arguments.of(D2, D3), Arguments.of(A1, A6), Arguments.of(B1, C3),
                Arguments.of(C1, A3), Arguments.of(D1, A4), Arguments.of(E1, F2)
        );
    }

    public static Stream<Arguments> moveFailureCauseInvalidMoveParameters() {
        return Stream.of(
                Arguments.of(D2, D5), Arguments.of(D2, C3), Arguments.of(A1, H1), Arguments.of(A1, B6),
                Arguments.of(B1, B2), Arguments.of(C1, C6), Arguments.of(D1, D3), Arguments.of(D1, C3),
                Arguments.of(E1, E3)
        );
    }

    @ParameterizedTest
    @MethodSource("moveSuccessParameters")
    @DisplayName("정상적인 경우에 이동이 성공하는지 검증")
    void moveSuccess(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(
                new Pawn(D2, WHITE), new Rook(A1, WHITE), new King(E1, WHITE),
                new Knight(B1, WHITE), new Bishop(C1, WHITE), new Queen(D1, WHITE)));
        boolean moveSuccess = chessBoard.move(from, to);
        Assertions.assertThat(moveSuccess).isTrue();
    }

    @ParameterizedTest
    @MethodSource("moveFailureCauseInvalidMoveParameters")
    @DisplayName("해당 위치가 이동이 불가능한 위치라서 이동이 실패하는지 검증")
    void moveFailureCauseInvalidMove(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(
                new Pawn(D2, WHITE), new Rook(A1, WHITE), new King(E1, WHITE),
                new Knight(B1, WHITE), new Bishop(C1, WHITE), new Queen(D1, WHITE)));
        boolean moveSuccess = chessBoard.move(from, to);
        Assertions.assertThat(moveSuccess).isFalse();
    }

    @Test
    @DisplayName("해당 위치에 말이 없어서 이동이 실패하는지 검증")
    void moveFailureCausePieceNotFound() {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(D2, WHITE)));
        boolean moveSuccess = chessBoard.move(D3, D4);
        Assertions.assertThat(moveSuccess).isFalse();
    }

    @Test
    @DisplayName("팀의 이동 차례가 아니라서 이동이 실패하는지 검증")
    void moveFailureCauseInvalidTurn() {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(D2, WHITE), new Pawn(D7, BLACK)));
        chessBoard.move(D2, D4);

        boolean moveSuccess = chessBoard.move(D4, D5);
        Assertions.assertThat(moveSuccess).isFalse();
    }

    @Test
    @DisplayName("상대 말을 잡으면 그 말이 보드에서 사라지는지 검증")
    void catchSuccess() {
        Pawn survivor = new Pawn(D2, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of(survivor, new Pawn(C3, BLACK)));
        chessBoard.move(D2, C3);
        List<Piece> piecesOnBoard = chessBoard.getPiecesOnBoard();
        Assertions.assertThat(piecesOnBoard)
                .containsExactly(survivor);
    }

    @Test
    @DisplayName("잘못된 이동을 시도했을 때 차례가 넘어가지 않는지 검증")
    void validateTurnNotChangeWhenInvalidMove() {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(D2, WHITE), new Pawn(C3, BLACK)));
        chessBoard.move(D2, D8);
        boolean blackMoveSuccess = chessBoard.move(C3, C2);
        Assertions.assertThat(blackMoveSuccess).isFalse();
    }

    @Test
    @DisplayName("이동이 성공했을 때 차례가 넘어가는지 검증")
    void validateTurnChangeWhenValidMove() {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(D2, WHITE), new Pawn(C3, BLACK)));
        chessBoard.move(D2, D3);
        boolean blackMoveSuccess = chessBoard.move(C3, C2);
        Assertions.assertThat(blackMoveSuccess).isTrue();
    }
}
