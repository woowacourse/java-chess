package chess.domain;

import chess.domain.piece.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.ChessBoard.*;
import static chess.domain.ChessBoard.WHITE_PAWN_EIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerTest {

    @Test
    @DisplayName("흰색 Color 객체를 입력하면 플레이어가 정상적으로 생성된다.")
    void shouldSucceedToGeneratePlayer() {
        Color color = Color.WHITE;

        assertDoesNotThrow(() -> new Player(color));
    }

    @Test
    @DisplayName("Blank 컬러 객체를 입력하여 플레이어 객체 생성 시 예외가 발생한다.")
    void ShouldFailToGeneratePlayer() {
        Color color = Color.BLANK;

        Assertions.assertThatThrownBy(() -> new Player(color))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] Blank로는 Player를 만들 수 없습니다.");
    }

    @Test
    @DisplayName("출발 위치를 입력할 때 출발 위치에 존재하는 Piece를 반환한다.")
    void shouldSucceedToFindChessPiece() {
        Player player = new Player(Color.WHITE);
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a2");

        assertThat(player.findChessPiece(chessBoard, sourcePosition)).isEqualTo(new Pawn(Color.WHITE));
    }

    @Test
    @DisplayName("상대편 기물이 존재하는 Position으로 기물을 찾을 때 예외가 발생한다.")
    void shouldFailToFindChessPiece() {
        Player player = new Player(Color.BLACK);
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a2");

        assertThatThrownBy(() -> player.findChessPiece(chessBoard, sourcePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR 상대편 기물은 이동할 수 없습니다.]");
    }

    @Test
    @DisplayName("기물의 현재 위치와 목표 위치를 입력했을 때 해당 기물의 경로 객체(Movement)가 반환된다")
    void shouldSucceedToFindMovement() {
        Player player = new Player(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);
        Position sourcePosition = Position.findPosition("a2");
        Position targetPosition = Position.findPosition("a3");

        Movement expectedMovement = new Movement(sourcePosition, Direction.NORTH, 1);

        assertThat(player.findMovement(pawn, sourcePosition, targetPosition)).isEqualTo(expectedMovement);
    }

    @Test
    @DisplayName("기물이 도착 위치로 이동 가능할 때 true를 반환한다.")
    void shouldSucceedToMovePiece() {
        Player player = new Player(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a2");
        Movement movement = new Movement(sourcePosition, Direction.NORTH, 1);

        assertThat(player.canMoveSourcePiece(chessBoard, pawn, movement)).isTrue();
    }

    @Test
    @DisplayName("포지션과 기물의 맵을 입력할 경우 각 기물에 해당하는 점수가 합산되어 반환된다.")
    void shouldSucceedToGetScoreOfPieces() {
        Player player = new Player(Color.WHITE);

        Map<Position, ChessPiece> whitePieces = new HashMap<>();
        whitePieces.put(Position.findPosition(WHITE_ROOK_LEFT), new Rook(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_ROOK_RIGHT), new Rook(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KNIGHT_LEFT), new Knight(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KNIGHT_RIGHT), new Knight(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_BISHOP_LEFT), new Bishop(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_BISHOP_RIGHT), new Bishop(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_QUEEN), new Queen(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_KING), new King(Color.WHITE));
        whitePieces.put(Position.findPosition("b3"), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SECOND), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_THIRD), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_FOURTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_FIFTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SIXTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_SEVENTH), new Pawn(Color.WHITE));
        whitePieces.put(Position.findPosition(WHITE_PAWN_EIGHT), new Pawn(Color.WHITE));

        Result result = player.calculateResult(whitePieces);
        double expectedScore = 34.0;

        assertThat(result.getScore()).isEqualTo(expectedScore);
    }
}
