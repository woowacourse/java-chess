package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@DisplayName("보드")
class BoardTest {

    @DisplayName("기물의 출발지와 목적지가 같으면 예외가 발생한다.")
    @Test
    void occurExceptionWhenSourceAndTargetAreSameSquare() {
        Board board = new Board(Map.of(
                new Square(File.b, Rank.THREE), new Piece(PieceType.ROOK, PieceColor.BLACK)));
        Square source = new Square(File.b, Rank.THREE);
        Square target = new Square(File.b, Rank.THREE);
        PieceColor turn = PieceColor.WHITE;

        assertThatThrownBy(() -> board.move(source, target, turn))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("해당 위치에 기물이 존재하지 않으면 예외가 발생한다.")
    @Test
    void occurExceptionWhenNotExistPiece() {
        Board board = new Board(Map.of());
        Square source = new Square(File.b, Rank.THREE);
        Square target = new Square(File.b, Rank.FOUR);
        PieceColor turn = PieceColor.WHITE;

        assertThatThrownBy(() -> board.move(source, target, turn))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상대방 팀의 기물을 이동하려는 경우 예외가 발생한다.")
    @Test
    void occurExceptionWhenNotMyTurn() {
        Board board = new Board(Map.of());
        Square source = new Square(File.b, Rank.SEVEN);
        Square target = new Square(File.b, Rank.SIX);
        PieceColor turn = PieceColor.WHITE;

        assertThatThrownBy(() -> board.move(source, target, turn))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("각 기물의 이동 방식으로 갈 수 없는 목적지인 경우 예외가 발생한다.")
    @Test
    void occurExceptionWhenCannotMove() {
        Board board = new Board(Map.of(
                new Square(File.b, Rank.THREE), new Piece(PieceType.ROOK, PieceColor.BLACK)));
        Square source = new Square(File.b, Rank.THREE);
        Square target = new Square(File.b, Rank.FOUR);
        PieceColor turn = PieceColor.WHITE;

        assertThatThrownBy(() -> board.move(source, target, turn))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 경로에 다른 기물이 있으면 예외가 발생한다.")
    @Test
    void occurExceptionWhenExistObstacleOnPath() {
        Board board = new Board(Map.of(
                new Square(File.b, Rank.THREE), new Piece(PieceType.ROOK, PieceColor.BLACK),
                new Square(File.b, Rank.FOUR), new Piece(PieceType.ROOK, PieceColor.BLACK)
        ));
        Square source = new Square(File.b, Rank.THREE);
        Square target = new Square(File.b, Rank.FIVE);
        PieceColor turn = PieceColor.WHITE;

        assertThatThrownBy(() -> board.move(source, target, turn))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("출발지에서 목적지로 기물을 이동한다.")
    @Test
    void movePieceFromSourceToTarget() {
        Board board = new Board(Map.of(
                new Square(File.b, Rank.TWO), new Piece(PieceType.ROOK, PieceColor.WHITE),
                new Square(File.b, Rank.THREE), new Piece(PieceType.ROOK, PieceColor.WHITE)
        ));

        Square source = new Square(File.b, Rank.TWO);
        Square target = new Square(File.b, Rank.THREE);
        PieceColor turn = PieceColor.WHITE;
        board.move(source, target, turn);

        assertThat(board.getPieces()).containsKey(new Square(File.b, Rank.THREE));
    }
}
