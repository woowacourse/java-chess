package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.ChessBoard.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ChessBoardTest {

    ChessBoard chessBoard = ChessBoard.GenerateChessBoard();

    @Test
    @DisplayName("체스 보드를 생성했을 때 64개의 piece가 생성된다.")
    void shouldSucceedGeneratingChessBoard() {
        int expectedNumberOfPieces = 64;

        assertThat(chessBoard.getChessBoard().size()).isEqualTo(expectedNumberOfPieces);
    }

    @Test
    @DisplayName("체스 보드 생성 후 룩은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingRook() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a1"))).isInstanceOf(Rook.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h1"))).isInstanceOf(Rook.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a8"))).isInstanceOf(Rook.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a8"))).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 나이트는 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingKnight() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b1"))).isInstanceOf(Knight.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g1"))).isInstanceOf(Knight.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b8"))).isInstanceOf(Knight.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g8"))).isInstanceOf(Knight.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 비숍은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingBishop() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c1"))).isInstanceOf(Bishop.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f1"))).isInstanceOf(Bishop.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c8"))).isInstanceOf(Bishop.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f8"))).isInstanceOf(Bishop.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 퀸은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingQueen() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d1"))).isInstanceOf(Queen.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d8"))).isInstanceOf(Queen.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 킹은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingKing() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e1"))).isInstanceOf(King.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e8"))).isInstanceOf(King.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 폰은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingPawn() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h2"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g7"))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h7"))).isInstanceOf(Pawn.class)
        );
    }

    @Test
    @DisplayName("체스 보드 생성 후 공백은 올바른 위치에 만들어진다.")
    void shouldSucceedGeneratingEmpty() {

        assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h3"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h4"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h5"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("a6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("b6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("c6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("d6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("e6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("f6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("g6"))).isInstanceOf(Empty.class),
                () -> assertThat(chessBoard.getChessBoard().get(Position.findPosition("h6"))).isInstanceOf(Empty.class)
        );
    }

    @Test
    @DisplayName("체스보드에 Position 객체를 입력하면 해당 위치에 존재하는 기물이 반환된다")
    void ShouldSucceedFindChessPiece() {

        Position sourcePosition = Position.findPosition("a2");
        Color color = Color.WHITE;
        ChessPiece expectedChessPiece = new Pawn(Color.WHITE);

        ChessPiece chessPiece = chessBoard.findChessPiece(sourcePosition, color);

        assertThat(chessPiece).isEqualTo(expectedChessPiece);
    }

    @Test
    @DisplayName("체스보드에서 자신의 색깔이 아닌 기물의 Position을 입력하면 예외가 발생한다.")
    void shouldFailFindChessPiece() {

        Position sourcePosition = Position.findPosition("a2");
        Color color = Color.BLACK;

        assertThatThrownBy(() -> chessBoard.findChessPiece(sourcePosition, color))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 색상의 기물을 선택할 수 없습니다.");
    }

    @Test
    @DisplayName("체스 기물과 포지션이 주어졌을 때 체스 기물을 목표 지점을 이동시킨다.")
    void shouldSucceedMovePiece() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position targetPosition = Position.findPosition("a3");

        chessBoard.movePiece(pawn, targetPosition);

        assertThat(chessBoard.getChessBoard().get(Position.findPosition("a3"))).isEqualTo(new Pawn(Color.WHITE));
    }

    @Test
    @DisplayName("체스 기물을 이동 시킬 때, 기존 위치에 기물이 공백 객체로 대체된다.")
    void shouldSucceedRemovingChessPiece() {
        Position sourcePosition = Position.findPosition("a2");

        chessBoard.removePiece(sourcePosition);

        assertThat(chessBoard.getChessBoard().get(Position.findPosition("a2"))).isEqualTo(new Empty());
    }

    @Test
    @DisplayName("체스 보드에서 흰색 기물만 필터링하여 반환한다.")
    void shouldSucceedToFindWhitePlayerPieces() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();

        Map<Position, ChessPiece> whitePieces = new HashMap<>();
        whitePieces.put(Position.findPosition(WHITE_ROOK_LEFT), new Rook(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_ROOK_RIGHT), new Rook(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KNIGHT_LEFT), new Knight(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KNIGHT_RIGHT), new Knight(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_BISHOP_LEFT), new Bishop(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_BISHOP_RIGHT), new Bishop(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_QUEEN), new Queen(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KING), new King(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_FIRST), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SECOND), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_THIRD), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_FOURTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_FIFTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SIXTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SEVENTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_EIGHT), new Pawn(Color.WHITE));

        assertThat(chessBoard.getChessPiecesByColor(Color.WHITE)).containsAllEntriesOf(whitePieces);
    }
}
