package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.NoPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private static final Map<Position, Piece> BOARD_MAP = new HashMap<>();

    @BeforeEach
    void beforeEach() {
        for (int file = 1; file <= 8; file++) {
            for (int rank = 1; rank <= 8; rank++) {
                BOARD_MAP.put(Position.of(file, rank), new NoPiece(Color.NO_COLOR));
            }
        }
    }

    @Test
    @DisplayName("실패: 출발점에 말이 없으면 이동 불가")
    void move_NoPieceAtSourcePosition() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position sourcePosition = Position.of(4, 4);
        Position targetPosition = Position.of(4, 5);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("출발점에 말이 없습니다.");
    }

    @Test
    @DisplayName("실패: 도착점에 본인의 말이 있으면 이동 불가")
    void move_OwnPieceExistAtTargetPosition() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position sourcePosition = Position.of(1, 1);
        Position targetPosition = Position.of(1, 2);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("한 칸에 말이 2개 존재할 수 없습니다.");
    }

    @Test
    @DisplayName("실패: 출발 위치와 도착 위치가 같으면 이동 불가")
    void move_SourcePositionIsSameAsTargetPosition() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position sourcePosition = Position.of(1, 1);
        Position targetPosition = Position.of(1, 1);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("출발점과 도착점은 같을 수 없습니다.");
    }

    @Test
    @DisplayName("성공: Knight가 규칙에 맞는 위치로 이동")
    void move_LegalMoveKnight() {
        Position sourcePosition = Position.of(2, 1);
        Position targetPosition = Position.of(1, 3);
        Knight knight = new Knight(Color.WHITE);
        Board board = Board.generatedBy(new InitialBoardGenerator());

        board.move(sourcePosition, targetPosition, Color.WHITE);
        assertAll(
            () -> assertThat(board.findPieceAt(sourcePosition)).isEqualTo(new NoPiece(Color.NO_COLOR)),
            () -> assertThat(board.findPieceAt(targetPosition)).isEqualTo(knight)
        );
    }

    @Test
    @DisplayName("성공: King이 규칙에 맞는 위치로 이동")
    void move_LegalMoveKing() {

        Position sourcePosition = Position.of(2, 2);
        Position targetPosition = Position.of(1, 3);
        King king = new King(Color.WHITE);
        BOARD_MAP.put(sourcePosition, king);
        Board board = Board.generatedBy(() -> BOARD_MAP);

        board.move(sourcePosition, targetPosition, Color.WHITE);
        assertAll(
            () -> assertThat(board.findPieceAt(sourcePosition)).isEqualTo(new NoPiece(Color.NO_COLOR)),
            () -> assertThat(board.findPieceAt(targetPosition)).isEqualTo(king)
        );
    }

    @Test
    @DisplayName("실패: 말의 규칙에 맞지 않는 위치로 이동")
    void move_IllegalMove() {
        Position sourcePosition = Position.of(2, 1);
        Position targetPosition = Position.of(3, 4);
        Board board = Board.generatedBy(new InitialBoardGenerator());

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("말의 규칙에 맞지 않는 이동입니다.");
    }

    @Test
    @DisplayName("실패: rook 경로에 말이 있는 경우 이동 불가")
    void move_Rook_PieceExistsOnRoute() {
        Position sourcePosition = Position.of(1, 1);
        Position middlePosition = Position.of(1, 4);
        Position targetPosition = Position.of(1, 8);

        Rook rook = new Rook(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);

        BOARD_MAP.put(sourcePosition, rook);
        BOARD_MAP.put(middlePosition, pawn);

        Board board = Board.generatedBy(() -> BOARD_MAP);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: bishop 경로에 말이 있는 경우 이동 불가")
    void move_Bishop_PieceExistsOnRoute() {
        Position sourcePosition = Position.of(1, 1);
        Position middlePosition = Position.of(4, 4);
        Position targetPosition = Position.of(8, 8);

        Bishop bishop = new Bishop(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);

        BOARD_MAP.put(sourcePosition, bishop);
        BOARD_MAP.put(middlePosition, pawn);

        Board board = Board.generatedBy(() -> BOARD_MAP);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: queen 경로에 말이 있는 경우 이동 불가")
    void move_Queen_PieceExistsOnRoute() {
        Position sourcePosition = Position.of(2, 2);
        Position middlePosition = Position.of(2, 6);
        Position targetPosition = Position.of(2, 8);

        Queen queen = new Queen(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);

        BOARD_MAP.put(sourcePosition, queen);
        BOARD_MAP.put(middlePosition, pawn);

        Board board = Board.generatedBy(() -> BOARD_MAP);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: pawn 2칸 전진 시 경로에 말이 있는 경우 이동 불가")
    void move_Pawn_PieceExistsOnRouteWhenMoveForwardTwo() {
        Position sourcePosition = Position.of(2, 2);
        Position middlePosition = Position.of(2, 3);
        Position targetPosition = Position.of(2, 4);

        Pawn pawn = new Pawn(Color.WHITE);
        Queen queen = new Queen(Color.WHITE);

        BOARD_MAP.put(sourcePosition, pawn);
        BOARD_MAP.put(middlePosition, queen);

        Board board = Board.generatedBy(() -> BOARD_MAP);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경로에 말이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("실패: pawn 전진 시 상대 말이 있으면 이동 불가")
    void move_Pawn_PieceExistsAtTargetPosition() {
        Position sourcePosition = Position.of(2, 2);
        Position targetPosition = Position.of(2, 3);

        Pawn pawn = new Pawn(Color.WHITE);
        Queen queen = new Queen(Color.BLACK);

        BOARD_MAP.put(sourcePosition, pawn);
        BOARD_MAP.put(targetPosition, queen);

        Board board = Board.generatedBy(() -> BOARD_MAP);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("직진으로 잡을 수 없습니다.");
    }

    @Test
    @DisplayName("성공: pawn은 대각선 앞에 상대 기물이 있으면 이동 가능")
    void move_Pawn_PieceExistsAtTargetPositionWhenDiagonalMove() {
        Position sourcePosition = Position.of(2, 2);
        Position targetPosition = Position.of(3, 3);

        Pawn pawn = new Pawn(Color.WHITE);
        Queen queen = new Queen(Color.BLACK);

        BOARD_MAP.put(sourcePosition, pawn);
        BOARD_MAP.put(targetPosition, queen);

        Board board = Board.generatedBy(() -> BOARD_MAP);

        board.move(sourcePosition, targetPosition, Color.WHITE);

        assertAll(
            () -> assertThat(board.findPieceAt(sourcePosition)).isEqualTo(new NoPiece(Color.NO_COLOR)),
            () -> assertThat(board.findPieceAt(targetPosition)).isEqualTo(pawn)
        );
    }

    @Test
    @DisplayName("실패: pawn은 대각선 앞에 상대 기물이 없으면 이동 블가능")
    void move_Pawn_PieceNotExistsAtTargetPositionWhenDiagonalMove() {
        Position sourcePosition = Position.of(2, 2);
        Position targetPosition = Position.of(3, 3);

        Pawn pawn = new Pawn(Color.WHITE);
        BOARD_MAP.put(sourcePosition, pawn);
        Board board = Board.generatedBy(() -> BOARD_MAP);

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Color.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("대각선 방향에 상대방 말이 없으면 움직일 수 없습니다.");
    }
}
