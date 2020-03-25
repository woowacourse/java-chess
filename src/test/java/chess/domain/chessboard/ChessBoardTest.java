package chess.domain.chessboard;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.chessPiece.Piece;
import chess.domain.chessPiece.Queen;
import chess.domain.chessPiece.Rook;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.movefactory.Straight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessBoardTest {
    @Test
    @DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : QueenBlack")
    void findPieceByPositionTestQueenBlack() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.ONE))).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : null")
    void findPieceByPositionTestNull() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.THREE))).isEqualTo(null);
    }


    @Test
    @DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : QueenWhite")
    void findPieceByPositionTestQueenWhite() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.EIGHT))).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("체스말 이동 테스트 - 정상작동")
    void movePiece() {
        ChessBoard chessBoard = new ChessBoard();
        Piece bishop = chessBoard.findPieceByPosition(Position.of("c1"));

        chessBoard.movePiece(Position.of("c1"), Position.of("a3"));

        assertThat(bishop.isEqualPosition(Position.of("a3"))).isTrue();
    }


    @Test
    @DisplayName("같은팀이 있는 위치로 체스말 이동")
    void movePieceWithError() {
        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.movePiece(Position.of("c1"), Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은팀이 있는 칸으로 이동할수 없습니다.");
    }

    @Test
    @DisplayName("움직이는 테스트")
    void movePieceTest() {
        Position source = Position.of("a1");
        Piece piece = new Rook(source, new BlackTeam());
        piece.move(new Straight(source, Position.of("a3")));

        assertThat(piece.isEqualPosition(Position.of("a3"))).isTrue();
    }
}
