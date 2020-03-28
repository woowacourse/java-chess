package chess.domain.chessboard;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.chessPiece.piece.Bishop;
import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.Queen;
import chess.domain.chessPiece.piece.Rook;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.movefactory.MoveTypeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardTest {
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
    void movePieceWithError1() {
        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.movePiece(Position.of("c1"), Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 칸에 같은 팀의 말이 존재 합니다.");
    }

    @Test
    @DisplayName("pawn이 처음 움직일때 3칸 이상 움직이면 exception을 발생시킨다.")
    void movePieceWithError2() {
        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.movePiece(Position.of("a2"), Position.of("a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다.");
    }

    @Test
    @DisplayName("pawn이 움직이고 난후 2칸을 움직이려 하면 exception을 발생시킨다.")
    void movePieceWithError3() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of("a2"), Position.of("a4"));
        assertThatThrownBy(() -> chessBoard.movePiece(Position.of("a4"), Position.of("a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다.");
    }

    @Test
    @DisplayName("Rook이 직선 이외의 움직임을 입력받으면 exception을 발생시킨다.")
    void movePieceWithError4() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of("b2"), Position.of("b4"));
        assertThatThrownBy(() -> chessBoard.movePiece(Position.of("a1"), Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다.");
    }

    @Test
    @DisplayName("Bishop 대각선 이외의 움직임을 입력받으면 exception을 발생시킨다.")
    void movePieceWithError5() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Position.of("c2"), Position.of("c4"));
        assertThatThrownBy(() -> chessBoard.movePiece(Position.of("c1"), Position.of("c2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다.");
    }

    @Test
    @DisplayName("Knight가 정해진 움직임 이외의 움직임을 입력받으면 exception을 발생시킨다.")
    void movePieceWithError6() {
        ChessBoard chessBoard = new ChessBoard();
        assertThatThrownBy(() -> chessBoard.movePiece(Position.of("b1"), Position.of("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다.");
    }

    @Test
    @DisplayName("Queen 정해진 움직임 이외의 움직임을 입력받으면 exception을 발생시킨다.")
    void movePieceWithError7() {
        ChessBoard chessBoard = new ChessBoard();
        assertThatThrownBy(() -> chessBoard.movePiece(Position.of("d1"), Position.of("c3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다.");
    }

    @Test
    @DisplayName("King 정해진 움직임 이외의 움직임을 입력받으면 exception을 발생시킨다.")
    void movePieceWithError8() {
        ChessBoard chessBoard = new ChessBoard();
        assertThatThrownBy(() -> chessBoard.movePiece(Position.of("e1"), Position.of("c3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다.");
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

    @DisplayName("왕이 살아있는경우")
    @Test
    void isSurviveKingsTest1() {
        assertThat(chessBoard.isSurviveKings()).isTrue();
    }

    @DisplayName("왕이 하나 없는 경우")
    @Test
    void isSurviveKingsTest2() {
        Piece blackKing = chessBoard.findPieceByPosition(Position.of("e1"));
        chessBoard.removePiece(blackKing);

        assertThat(chessBoard.isSurviveKings()).isFalse();
    }

    @DisplayName("해당 포지션에 말이 없으면 false을 반환 한다")
    @Test
    void isExistPieceTest() {
        assertThat(chessBoard.isExistPiece(Position.of("e5"))).isFalse();
    }

    @DisplayName("각 팀점수가 올바르계 계산되어 처음 상태에서 각팀의 점수는 동일하다")
    @Test
    void calculateTeamScore1() {
        assertThat(chessBoard.calculateTeamScore(new BlackTeam()))
                .isEqualTo(chessBoard.calculateTeamScore(new WhiteTeam()));
    }

    @DisplayName("2개 이상의 pwan이 같은 x축값을 가질때 각각 0.5의 점수로 계산이 된다")
    @Test
    void calculateTeamScore2() {
        chessBoard.movePiece(Position.of("a2"), Position.of("a4"));
        chessBoard.movePiece(Position.of("b7"), Position.of("b5"));
        chessBoard.movePiece(Position.of("a4"), Position.of("b5"));
        assertThat(chessBoard.calculateTeamScore(new BlackTeam())).isEqualTo(37.0);
    }
}
