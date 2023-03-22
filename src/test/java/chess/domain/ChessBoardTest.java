package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChessBoardTest {
    
    @Nested
    @DisplayName("생성 테스트")
    class CreateChessBoard {
        
        @Test
        @DisplayName("보드 피스 생성 테스트")
        void initialize2() {
            ChessBoard board = ChessBoard.create();
            List<String> positions = List.of("a1", "b1", "g7", "h8", "d4");
            List<Piece> pieces = List.of(Rook.create(Color.WHITE), Knight.create(Color.WHITE), Pawn.create(Color.BLACK),
                    Rook.create(Color.BLACK), Empty.create());
            for (int i = 0; i < 4; i++) {
                assertThat(board).extracting("board").asInstanceOf(InstanceOfAssertFactories.MAP)
                        .containsEntry(Position.from(positions.get(i)), pieces.get(i));
            }
        }
    }
    
    @Nested
    @DisplayName("Sliding Piece 움직임 제한사항")
    class SlidingPiece {
        
        @Test
        @DisplayName("경로에 다른 피스가 있을 경우")
        void checkOtherPieceInRoute() {
            ChessBoard board = ChessBoard.create();
            board.move(Position.from("a2"), Position.from("a4"));
            board.move(Position.from("a7"), Position.from("a5"));
            assertThatThrownBy(() -> board.checkRoute(Position.from("a1"), Position.from("a5"))).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessage(ChessBoard.OTHER_PIECE_IN_ROUTE);
        }
    }
    
    @Nested
    @DisplayName("폰 움직임 제한사항")
    class PawnRestriction {
        
        @Test
        @DisplayName("위나 아래로 움직이는데, 경로에 다른 피스가 있을 경우")
        void checkOtherPieceInRoute() {
            ChessBoard board = ChessBoard.create();
            board.move(Position.from("a2"), Position.from("a4"));
            board.move(Position.from("a7"), Position.from("a5"));
            assertThatThrownBy(() -> board.checkRoute(Position.from("a4"), Position.from("a5"))).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessage(ChessBoard.OTHER_PIECE_IN_ROUTE);
        }
        
        @Test
        @DisplayName("대각선 방향으로 움직이는데, 상대편 피스가 없는 경우 - 비어있는 경우")
        void checkOtherPieceInDiagonal1() {
            ChessBoard board = ChessBoard.create();
            assertThatThrownBy(() -> board.checkRoute(Position.from("a2"), Position.from("b3"))).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessage(ChessBoard.NO_OTHER_COLOR_IN_DIAGONAL_DESTINATION);
        }
        
    }
    
    @Test
    @DisplayName("움직이는 대상이 다른 색깔인지 확인")
    void checkColor() {
        ChessBoard board = ChessBoard.create();
        assertThatThrownBy(() -> board.checkColor(Position.from("a2"), Position.from("a4"), Color.BLACK)).isInstanceOf(
                        IllegalArgumentException.class)
                .hasMessage(ChessBoard.OTHER_COLOR_IN_SOURCE);
    }
    
    @Test
    @DisplayName("목적지에 같은 색깔의 피스가 있는지 확인")
    void checkDestination() {
        ChessBoard board = ChessBoard.create();
        board.move(Position.from("b2"), Position.from("a4"));
        assertThatThrownBy(
                () -> board.checkColor(Position.from("a2"), Position.from("a4"), Color.WHITE)).isInstanceOf(
                        IllegalArgumentException.class)
                .hasMessage(ChessBoard.SAME_COLOR_IN_DESTINATION);
    }
}