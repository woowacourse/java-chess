package domain.board;

import domain.game.Turn;
import domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {

    @Test
    @DisplayName("실패: 출발점에 말이 없으면 이동 불가")
    void move_NoPieceAtsource() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position source = Position.of(4, 4);
        Position target = Position.of(4, 5);

        assertThatThrownBy(() -> board.move(source, target, Turn.makeInitialTurn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source 위치에 말이 없습니다.");
    }

    @Test
    @DisplayName("실패: 도착점에 본인의 말이 있으면 이동 불가")
    void move_OwnPieceExistAttarget() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position source = Position.of(1, 1);
        Position target = Position.of(1, 2);

        assertThatThrownBy(() -> board.move(source, target, Turn.makeInitialTurn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 칸에 말이 2개 존재할 수 없습니다.");
    }

    @Test
    @DisplayName("실패: 출발 위치와 도착 위치가 같으면 이동 불가")
    void move_sourceIsSameAstarget() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position source = Position.of(1, 1);
        Position target = Position.of(1, 1);

        assertThatThrownBy(() -> board.move(source, target, Turn.makeInitialTurn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source 위치와 target 위치가 같을 수 없습니다.");
    }

    @Test
    @DisplayName("성공: Knight가 규칙에 맞는 위치로 이동")
    void move_LegalMoveKnight() {
        Position source = Position.of(2, 1);
        Position target = Position.of(3, 3);
        Knight knight = new Knight(Color.WHITE);
        Empty empty = new Empty(Color.EMPTY);
        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, knight,
                        target, empty
                )
        ));

        board.move(source, target, Turn.makeInitialTurn());
        assertAll(
                () -> assertThat(board.findPieceAt(source)).isNull(),
                () -> assertThat(board.findPieceAt(target)).isEqualTo(knight)
        );
    }

    @Test
    @DisplayName("성공: King이 규칙에 맞는 위치로 이동")
    void move_LegalMoveKing() {
        Position source = Position.of(2, 2);
        Position target = Position.of(1, 3);
        King king = new King(Color.WHITE);
        Empty empty = new Empty(Color.EMPTY);
        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, king,
                        target, empty
                )
        ));

        board.move(source, target, Turn.makeInitialTurn());
        assertAll(
                () -> assertThat(board.findPieceAt(source)).isNull(),
                () -> assertThat(board.findPieceAt(target)).isEqualTo(king)
        );
    }

    @Test
    @DisplayName("실패: 말의 규칙에 맞지 않는 위치로 이동")
    void move_IllegalMove() {
        Position source = Position.of(2, 1);
        Position target = Position.of(3, 4);
        Knight knight = new Knight(Color.WHITE);
        Empty empty = new Empty(Color.EMPTY);
        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, knight,
                        target, empty
                )
        ));

        assertThatThrownBy(() -> board.move(source, target, Turn.makeInitialTurn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("말의 규칙에 맞지 않는 이동입니다.");
    }

    @Test
    @DisplayName("실패: rook 경로에 말이 있는 경우 이동 불가")
    void move_Rook_PieceExistsOnRoute() {
        Position source = Position.of(1, 1);
        Position middlePosition = Position.of(1, 4);
        Position target = Position.of(1, 8);

        Rook rook = new Rook(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);
        Empty empty = new Empty(Color.EMPTY);

        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, rook,
                        middlePosition, pawn,
                        target, empty
                )
        ));

        assertThatThrownBy(() -> board.move(source, target, Turn.makeInitialTurn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: bishop 경로에 말이 있는 경우 이동 불가")
    void move_Bishop_PieceExistsOnRoute() {
        Position source = Position.of(1, 1);
        Position middlePosition = Position.of(4, 4);
        Position target = Position.of(8, 8);

        Bishop bishop = new Bishop(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);
        Empty empty = new Empty(Color.EMPTY);

        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, bishop,
                        middlePosition, pawn,
                        target, empty
                )
        ));

        assertThatThrownBy(() -> board.move(source, target, Turn.makeInitialTurn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: queen 경로에 말이 있는 경우 이동 불가")
    void move_Queen_PieceExistsOnRoute() {
        Position source = Position.of(2, 2);
        Position middlePosition = Position.of(2, 6);
        Position target = Position.of(2, 8);

        Queen queen = new Queen(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);
        Empty empty = new Empty(Color.EMPTY);

        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, queen,
                        middlePosition, pawn,
                        target, empty
                )
        ));

        assertThatThrownBy(() -> board.move(source, target, Turn.makeInitialTurn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: pawn 2칸 전진 시 경로에 말이 있는 경우 이동 불가")
    void move_Pawn_PieceExistsOnRouteWhenMoveForwardTwo() {
        Position source = Position.of(2, 2);
        Position middlePosition = Position.of(2, 3);
        Position target = Position.of(2, 4);

        Pawn pawn = new Pawn(Color.WHITE);
        Queen queen = new Queen(Color.WHITE);
        Empty empty = new Empty(Color.EMPTY);

        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, pawn,
                        middlePosition, queen,
                        target, empty
                )
        ));

        assertThatThrownBy(() -> board.move(source, target, Turn.makeInitialTurn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: pawn 전진 시 상대 말이 있으면 이동 불가")
    void move_Pawn_PieceExistsAttarget() {
        Position source = Position.of(2, 2);
        Position target = Position.of(2, 3);

        Pawn pawn = new Pawn(Color.WHITE);
        Queen queen = new Queen(Color.BLACK);

        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, pawn,
                        target, queen
                )
        ));

        assertThatThrownBy(() -> board.move(source, target, Turn.makeInitialTurn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("직진으로 잡을 수 없습니다.");
    }

    @Test
    @DisplayName("성공: pawn은 대각선 앞에 상대 기물이 있으면 이동 가능")
    void move_Pawn_PieceExistsAttargetWhenDiagonalMove() {
        Position source = Position.of(2, 2);
        Position target = Position.of(3, 3);

        Pawn pawn = new Pawn(Color.WHITE);
        Queen queen = new Queen(Color.BLACK);

        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, pawn,
                        target, queen
                )
        ));

        board.move(source, target, Turn.makeInitialTurn());

        assertAll(
                () -> assertThat(board.findPieceAt(source)).isNull(),
                () -> assertThat(board.findPieceAt(target)).isEqualTo(pawn)
        );
    }

    @Test
    @DisplayName("실패: pawn은 대각선 앞에 상대 기물이 없으면 이동 블가능")
    void move_Pawn_PieceNotExistsAttargetWhenDiagonalMove() {
        Position source = Position.of(2, 2);
        Position target = Position.of(3, 3);

        Pawn pawn = new Pawn(Color.WHITE);
        Empty empty = new Empty(Color.EMPTY);

        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, pawn,
                        target, empty
                )
        ));

        assertThatThrownBy(() -> board.move(source, target, Turn.makeInitialTurn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("대각선 방향에 상대방 말이 없으면 움직일 수 없습니다.");
    }
}
