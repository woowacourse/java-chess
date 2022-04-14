package chess.domain.piece;

import chess.domain.game.Board;
import chess.domain.game.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @ParameterizedTest
    @CsvSource(value = {"BLACK:P", "WHITE:p"}, delimiter = ':')
    @DisplayName("Pawn 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Piece pawn = new Piece(color, PieceType.PAWN, new PawnMovingPattern(), 0);
        assertThat(pawn.signature()).isEqualTo(pieceName);
    }

    @Test
    @DisplayName("검은색 Pawn 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isBlackMovable() {
        Piece pawn = new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_7);
        Position target = Position.of(PositionX.C, PositionY.RANK_6);
        assertThat(pawn.isMovable(Board.initialBoard().getBoard(), source, target)).isTrue();
    }

    @Test
    @DisplayName("검은색 Pawn 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isBlackNotMovable() {
        Piece pawn = new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.C, PositionY.RANK_6);
        assertThat(pawn.isMovable(Board.initialBoard().getBoard(), source, target)).isFalse();
    }

    @Test
    @DisplayName("흰색 Pawn 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isWhiteMovable() {
        Piece pawn = new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_2);
        Position target = Position.of(PositionX.C, PositionY.RANK_3);
        assertThat(pawn.isMovable(Board.initialBoard().getBoard(), source, target)).isTrue();
    }

    @Test
    @DisplayName("흰색 Pawn이 움직일 수 없는 위치이면 false를 반환하는지")
    void isWhiteNotMovable() {
        Piece pawn = new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.C, PositionY.RANK_4);
        assertThat(pawn.isMovable(Board.initialBoard().getBoard(), source, target)).isFalse();
    }

    @Test
    @DisplayName("Pawn 이 움직이는 경로를 얻어오는지")
    void findRoute() {
        Piece pawn = new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.C, PositionY.RANK_4);
        List<Position> route = pawn.findRoute(source, target);
        assertThat(route).isEmpty();
    }
}
