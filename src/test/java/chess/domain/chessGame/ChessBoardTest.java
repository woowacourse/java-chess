package chess.domain.chessGame;

import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ChessBoard는 ")
class ChessBoardTest {

    @Test
    @DisplayName("초기화 시 32개의 체스말이 세팅된다.")
    void getChessBoardTest() {
        // given
        ChessBoardGenerator generator = new ChessBoardGenerator();
        ChessBoard chessBoard = new ChessBoard(generator.generate());

        // when
        Map<Position, Piece> setResult = chessBoard.getChessBoard();

        // then
        assertThat(setResult).hasSize(32);
    }

    @Nested
    @DisplayName("King이 죽었는지 확인할 수 있다.")
    class isKingDeadTest {

        @Test
        @DisplayName("King이 1개라도 죽어있으면 true를 반환한다.")
        void isKingDead_True() {
            // given
            ChessBoard chessBoard = new ChessBoard(Map.of(
                    Position.of(1, 4), new Queen(Color.WHITE),
                    Position.of(8, 4), new Queen(Color.BLACK)
            ));

            // when
            boolean result = chessBoard.isKingDead();

            // then
            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("King이 2개 다 살아있으면 false를 반환한다.")
        void isKingDead_False() {
            // given
            ChessBoard chessBoard = new ChessBoard(Map.of(
                    Position.of(1, 5), new King(Color.WHITE),
                    Position.of(8, 5), new King(Color.BLACK)
            ));

            // when
            boolean result = chessBoard.isKingDead();

            // then
            assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("말을 이동시킬 수 있다.")
    class movePieceTest_Success {

        Map<Position, Piece> setupBoard = Map.of(
                Position.of(2, 2), new Rook(Color.WHITE),
                Position.of(5, 2), new Rook(Color.BLACK));

        @Test
        @DisplayName("목표 좌표에 말이 없고 경로 상에 다른 말이 없으면 말을 이동시킬 수 있다.")
        void moveToBlankTest() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Position start = Position.of(2, 2);
            Position end = Position.of(4, 2);

            // when
            chessBoard.movePiece(start, end);
            Map<Position, Piece> resultBoard = chessBoard.getChessBoard();
            Piece movedPiece = resultBoard.get(end);

            // then
            assertThat(movedPiece).isInstanceOf(Rook.class);
        }

        @Test
        @DisplayName("목표 좌표에 상대 말이 있고 경로 상에 다른 말이 없으면 상대 말을 잡고 이동시킬 수 있다.")
        void catchAndMoveTest() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Position start = Position.of(2, 2);
            Position end = Position.of(5, 2);

            // when
            chessBoard.movePiece(start, end);
            Map<Position, Piece> resultBoard = chessBoard.getChessBoard();
            Piece movedPiece = resultBoard.get(end);

            // then
            assertThat(movedPiece.isWhite()).isTrue();

        }
    }

    @Nested
    @DisplayName("pawn을 이동시킬 수 있다.")
    class movePawnTest {

        Map<Position, Piece> setupBoard = Map.of(
                Position.of(2, 4), new Pawn(Color.WHITE),
                Position.of(3, 5), new Rook(Color.BLACK),
                Position.of(4, 4), new Rook(Color.BLACK));

        @Test
        @DisplayName("전진할 때 이동 경로에 말이 없으면 이동할 수 있다.")
        void pawnForwardTest_Success() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Position start = Position.of(2, 4);
            Position end = Position.of(3, 4);

            // when
            chessBoard.movePiece(start, end);
            Map<Position, Piece> resultBoard = chessBoard.getChessBoard();
            Piece movedPiece = resultBoard.get(end);

            // then
            assertThat(movedPiece).isInstanceOf(Pawn.class);
        }

        @Test
        @DisplayName("대각선으로 이동할 때 이동 경로에 상대의 말이 존재하면 잡고 이동할 수 있다.")
        void pawnDiagonalTest_Success() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Position start = Position.of(2, 4);
            Position end = Position.of(3, 5);

            // when
            chessBoard.movePiece(start, end);
            Map<Position, Piece> resultBoard = chessBoard.getChessBoard();
            Piece movedPiece = resultBoard.get(end);

            // then
            assertThat(movedPiece).isInstanceOf(Pawn.class);
        }
    }
}
