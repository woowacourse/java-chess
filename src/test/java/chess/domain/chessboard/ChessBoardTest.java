package chess.domain.chessboard;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.chessPiece.piece.Bishop;
import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.Queen;
import chess.domain.chessPiece.piece.Rook;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.movefactory.MoveTypeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessBoardTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

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
    @DisplayName("같은팀이 있는 위치로 체스말 이동")
    void movePieceWithError() {
        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.movePiece(Position.of("c1"), Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 칸에 같은 팀의 말이 존재 합니다.");
    }

    @Test
    @DisplayName("움직이는 테스트 UP")
    void movePieceTest() {
        Position source = Position.of("a3");
        Position target = Position.of("a5");
        Piece piece = new Rook(source, new BlackTeam());
        piece.move(MoveTypeFactory.of(source, target), chessBoard);

        assertThat(piece.isEqualPosition(Position.of("a5"))).isTrue();
    }

    @Test
    @DisplayName("움직이는 테스트 DOWN")
    void movePieceTest2() {
        Position source = Position.of("a5");
        Position target = Position.of("a3");
        Piece piece = new Rook(source, new BlackTeam());
        piece.move(MoveTypeFactory.of(source, target), chessBoard);

        assertThat(piece.isEqualPosition(Position.of("a3"))).isTrue();
    }

    @Test
    @DisplayName("움직이는 테스트 RIGHT")
    void movePieceTest3() {
        Position source = Position.of("a3");
        Position target = Position.of("h3");
        Piece piece = new Rook(source, new BlackTeam());
        piece.move(MoveTypeFactory.of(source, target), chessBoard);

        assertThat(piece.isEqualPosition(Position.of("h3"))).isTrue();
    }

    @Test
    @DisplayName("움직이는 테스트 LEFT")
    void movePieceTest4() {
        Position source = Position.of("g3");
        Position target = Position.of("b3");
        Piece piece = new Rook(source, new BlackTeam());
        piece.move(MoveTypeFactory.of(source, target), chessBoard);
        assertThat(piece.isEqualPosition(Position.of("b3"))).isTrue();
    }

    @Test
    @DisplayName("Bishop 움직임 테스트 UP_RIGHT")
    void bishopMoveTest1() {
        Position source = Position.of("c3");
        Position target = Position.of("e5");
        Piece piece = new Bishop(source, new BlackTeam());
        piece.move(MoveTypeFactory.of(source, target), chessBoard);

        assertThat(piece.isEqualPosition(Position.of("e5"))).isTrue();
    }

    @Test
    @DisplayName("Bishop 움직임 테스트 DOWN_RIGHT")
    void bishopMoveTest2() {
        Position source = Position.of("d4");
        Position target = Position.of("e3");
        Piece piece = new Bishop(source, new BlackTeam());
        piece.move(MoveTypeFactory.of(source, target), chessBoard);

        assertThat(piece.isEqualPosition(Position.of("e3"))).isTrue();
    }

    @Test
    @DisplayName("Bishop 움직임 테스트 DOWN_LEFT")
    void bishopMoveTest3() {
        Position source = Position.of("d4");
        Position target = Position.of("c3");
        Piece piece = new Bishop(source, new BlackTeam());
        piece.move(MoveTypeFactory.of(source, target), chessBoard);

        assertThat(piece.isEqualPosition(Position.of("c3"))).isTrue();
    }

    @Test
    @DisplayName("Bishop 움직임 테스트 UP_LEFT")
    void bishopMoveTest4() {
        Position source = Position.of("d4");
        Position target = Position.of("c5");
        Piece piece = new Bishop(source, new BlackTeam());
        piece.move(MoveTypeFactory.of(source, target), chessBoard);

        assertThat(piece.isEqualPosition(Position.of("c5"))).isTrue();
    }

}
