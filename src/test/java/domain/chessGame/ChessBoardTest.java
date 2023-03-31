package domain.chessGame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

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
    @DisplayName("아래와 같은 경우 말을 이동시킬 수 있다.")
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
    @DisplayName("아래와 같은 경우 말을 이동시킬 수 없다.")
    class movePieceTest_Fail {

        Map<Position, Piece> setupBoard = Map.of(
                Position.of(2, 2), new Rook(Color.WHITE),
                Position.of(4, 2), new Rook(Color.WHITE),
                Position.of(4, 4), new Bishop(Color.WHITE));

        @Test
        @DisplayName("출발 좌표에 말이 존재하지 않는 경우 예외가 발생한다.")
        void noPieceInStartTest() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Position start = Position.of(3, 2);
            Position end = Position.of(5, 2);

            // then
            assertThatThrownBy(() -> chessBoard.movePiece(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 출발 좌표 위치에 말이 존재하지 않습니다.");
        }

        @Test
        @DisplayName("진행 경로 상에 말이 있는 경우 예외가 발생한다.")
        void blockedTest() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Position start = Position.of(2, 2);
            Position end = Position.of(5, 2);

            // then
            assertThatThrownBy(() -> chessBoard.movePiece(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 진행 경로 상에 다른 말이 존재합니다.");
        }

        @Test
        @DisplayName("목표 좌표에 자신의 말이 존재하는 경우 예외가 발생한다.")
        void existSameColorPieceInEndTest() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Position start = Position.of(4, 2);
            Position end = Position.of(4, 4);

            // then
            assertThatThrownBy(() -> chessBoard.movePiece(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 같은 색의 말이 있는 칸으로 이동이 불가능합니다.");
        }
    }

    @Nested
    @DisplayName("아래와 같은 pawn의 이동 케이스를 판단한다.")
    class movePawnTest {

        Map<Position, Piece> setupBoard = Map.of(
                Position.of(2, 4), new WhitePawn(),
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
        @DisplayName("전진할 때 목적 좌표에 상대 말이 존재해도 예외가 발생한다.")
        void pawnForwardTest_Fail() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Position start = Position.of(2, 4);
            Position end = Position.of(4, 4);

            // then
            assertThatThrownBy(() -> chessBoard.movePiece(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 진행 경로 상에 다른 말이 존재합니다.");
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

        @Test
        @DisplayName("대각선으로 이동할 때 이동 경로에 상대의 말이 존재하지 않으면 예외가 발생한다.")
        void pawnDiagonalTest_Fail() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Position start = Position.of(2, 4);
            Position end = Position.of(3, 3);

            // then
            assertThatThrownBy(() -> chessBoard.movePiece(start, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 폰은 대각선 이동 경로에 상대 말이 없으면 이동이 불가능합니다.");
        }
    }

    @Nested
    @DisplayName("색깔 별로 말들의 현황을 반환한다.")
    class getPiecesByColorTest {

        Map<Position, Piece> setupBoard = Map.of(
                Position.of(2, 3), new WhitePawn(),
                Position.of(2, 4), new WhitePawn(),
                Position.of(3, 5), new Rook(Color.BLACK),
                Position.of(4, 3), new BlackPawn(),
                Position.of(4, 4), new Bishop(Color.BLACK));

        @Test
        @DisplayName("검은 말들의 현황을 반환한다.")
        void getBlackPiecesTest() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);

            // when
            Map<Position, Piece> blackPieces = chessBoard.getBlackPieces();

            // then
            assertThat(blackPieces).containsOnlyKeys(Position.of(3, 5), Position.of(4, 3),
                    Position.of(4, 4));
        }

        @Test
        @DisplayName("흰 말들의 현황을 반환한다.")
        void getWhitePiecesTest() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);

            // when
            Map<Position, Piece> blackPieces = chessBoard.getWhitePieces();

            // then
            assertThat(blackPieces).containsOnlyKeys(Position.of(2, 3), Position.of(2, 4));
        }
    }
}
