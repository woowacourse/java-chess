package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("실패: 출발점에 말이 없으면 이동 불가")
    void move_NoPieceAtSourcePosition() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position sourcePosition = new Position(new File(4), new Rank(4));
        Position targetPosition = new Position(new File(4), new Rank(5));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("source 위치에 말이 없습니다.");
    }

    @Test
    @DisplayName("실패: 도착점에 본인의 말이 있으면 이동 불가")
    void move_OwnPieceExistAtTargetPosition() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position sourcePosition = new Position(new File(1), new Rank(1));
        Position targetPosition = new Position(new File(1), new Rank(2));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("한 칸에 말이 2개 존재할 수 없습니다.");
    }

    @Test
    @DisplayName("실패: 출발 위치와 도착 위치가 같으면 이동 불가")
    void move_SourcePositionIsSameAsTargetPosition() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position sourcePosition = new Position(new File(1), new Rank(1));
        Position targetPosition = new Position(new File(1), new Rank(1));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("source 위치와 target 위치가 같을 수 없습니다.");
    }

    @Test
    @DisplayName("성공: Knight가 규칙에 맞는 위치로 이동")
    void move_LegalMoveKnight() {
        Position sourcePosition = new Position(new File(2), new Rank(1));
        Position targetPosition = new Position(new File(3), new Rank(3));
        Knight knight = new Knight(Color.WHITE);
        Board board = Board.generatedBy(() -> new HashMap<>(
            Map.of(sourcePosition, knight)
        ));

        board.move(sourcePosition, targetPosition);
        assertAll(
            () -> assertThatThrownBy(() -> board.findPieceAt(sourcePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 없습니다."),
            () -> assertThat(board.findPieceAt(targetPosition)).isEqualTo(knight)
        );
    }

    @Test
    @DisplayName("성공: King이 규칙에 맞는 위치로 이동")
    void move_LegalMoveKing() {
        Position sourcePosition = new Position(new File(2), new Rank(2));
        Position targetPosition = new Position(new File(1), new Rank(3));
        King king = new King(Color.WHITE);
        Board board = Board.generatedBy(() -> new HashMap<>(
            Map.of(sourcePosition, king)
        ));

        board.move(sourcePosition, targetPosition);
        assertAll(
            () -> assertThatThrownBy(() -> board.findPieceAt(sourcePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 없습니다."),
            () -> assertThat(board.findPieceAt(targetPosition)).isEqualTo(king)
        );
    }

    @Test
    @DisplayName("실패: 말의 규칙에 맞지 않는 위치로 이동")
    void move_IllegalMove() {
        Position sourcePosition = new Position(new File(2), new Rank(1));
        Position targetPosition = new Position(new File(3), new Rank(4));
        Knight knight = new Knight(Color.WHITE);
        Board board = Board.generatedBy(() -> new HashMap<>(
            Map.of(sourcePosition, knight)
        ));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("말의 규칙에 맞지 않는 이동입니다.");
    }

    @Test
    @DisplayName("실패: rook 경로에 말이 있는 경우 이동 불가")
    void move_Rook_PieceExistsOnRoute() {
        Position sourcePosition = new Position(new File(1), new Rank(1));
        Position middlePosition = new Position(new File(1), new Rank(4));
        Position targetPosition = new Position(new File(1), new Rank(8));

        Rook rook = new Rook(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);

        Board board = Board.generatedBy(() -> new HashMap<>(
            Map.of(
                sourcePosition, rook,
                middlePosition, pawn
            )
        ));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: bishop 경로에 말이 있는 경우 이동 불가")
    void move_Bishop_PieceExistsOnRoute() {
        Position sourcePosition = new Position(new File(1), new Rank(1));
        Position middlePosition = new Position(new File(4), new Rank(4));
        Position targetPosition = new Position(new File(8), new Rank(8));

        Bishop bishop = new Bishop(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);

        Board board = Board.generatedBy(() -> new HashMap<>(
            Map.of(
                sourcePosition, bishop,
                middlePosition, pawn
            )
        ));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: queen 경로에 말이 있는 경우 이동 불가")
    void move_Queen_PieceExistsOnRoute() {
        Position sourcePosition = new Position(new File(2), new Rank(2));
        Position middlePosition = new Position(new File(2), new Rank(6));
        Position targetPosition = new Position(new File(2), new Rank(8));

        Queen queen = new Queen(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);

        Board board = Board.generatedBy(() -> new HashMap<>(
            Map.of(
                sourcePosition, queen,
                middlePosition, pawn
            )
        ));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: pawn 2칸 전진 시 경로에 말이 있는 경우 이동 불가")
    void move_Pawn_PieceExistsOnRouteWhenMoveForwardTwo() {
        Position sourcePosition = new Position(new File(2), new Rank(2));
        Position middlePosition = new Position(new File(2), new Rank(3));
        Position targetPosition = new Position(new File(2), new Rank(4));

        Pawn pawn = new Pawn(Color.WHITE);
        Queen queen = new Queen(Color.WHITE);

        Board board = Board.generatedBy(() -> new HashMap<>(
            Map.of(
                sourcePosition, pawn,
                middlePosition, queen
            )
        ));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: pawn 전진 시 상대 말이 있으면 이동 불가")
    void move_Pawn_PieceExistsAtTargetPosition() {
        Position sourcePosition = new Position(new File(2), new Rank(2));
        Position targetPosition = new Position(new File(2), new Rank(3));

        Pawn pawn = new Pawn(Color.WHITE);
        Queen queen = new Queen(Color.BLACK);

        Board board = Board.generatedBy(() -> new HashMap<>(
            Map.of(
                sourcePosition, pawn,
                targetPosition, queen
            )
        ));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("직진으로 잡을 수 없습니다.");
    }

    @Test
    @DisplayName("성공: pawn은 대각선 앞에 상대 기물이 있으면 이동 가능")
    void move_Pawn_PieceExistsAtTargetPositionWhenDiagonalMove() {
        Position sourcePosition = new Position(new File(2), new Rank(2));
        Position targetPosition = new Position(new File(3), new Rank(3));

        Pawn pawn = new Pawn(Color.WHITE);
        Queen queen = new Queen(Color.BLACK);

        Board board = Board.generatedBy(() -> new HashMap<>(
            Map.of(
                sourcePosition, pawn,
                targetPosition, queen
            )
        ));

        board.move(sourcePosition, targetPosition);

        assertAll(
            () -> assertThatThrownBy(() -> board.findPieceAt(sourcePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 없습니다."),
            () -> assertThat(board.findPieceAt(targetPosition)).isEqualTo(pawn)
        );
    }

    @Test
    @DisplayName("실패: pawn은 대각선 앞에 상대 기물이 없으면 이동 블가능")
    void move_Pawn_PieceNotExistsAtTargetPositionWhenDiagonalMove() {
        Position sourcePosition = new Position(new File(2), new Rank(2));
        Position targetPosition = new Position(new File(3), new Rank(3));

        Pawn pawn = new Pawn(Color.WHITE);

        Board board = Board.generatedBy(() -> new HashMap<>(
            Map.of(
                sourcePosition, pawn
            )
        ));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("대각선 방향에 상대방 말이 없으면 움직일 수 없습니다.");
    }
}
