package chess.domain.board;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @DisplayName("체스보드의 특정위치에 기물이 없는 지 확인할 수 있다")
    @Test
    void should_CheckPositionEmptiness_When_GivenPosition() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        Position position = A1;
        positionPiece.put(position, new King(Team.BLACK));
        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThat(chessBoard.positionIsEmpty(A2)).isTrue();
    }

    @DisplayName("체스보드의 특정위치에 기물이 있는지 확인할 수 있다")
    @Test
    void should_CheckPositionNotEmptiness_When_GivenPosition() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        Position position = A1;
        positionPiece.put(position, new King(Team.BLACK));
        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThat(chessBoard.positionIsEmpty(A1)).isFalse();
    }

    @DisplayName("체스보드 특정 위치의 기물을 가져올 수 있다")
    @Test
    void should_GetPieceByPosition_When_GiveTargetPosition() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        Position position = A1;
        positionPiece.put(position, new King(Team.BLACK));
        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThat(chessBoard.findPieceByPosition(position)).isInstanceOf(King.class);
    }

    @DisplayName("체스보드 특정 위치의 기물을 가져올 때 없으면 예외를 발생시킨다")
    @Test
    void should_ThrowNoSuchElementException_When_TargetPositionIsEmpty() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        Position position = A1;
        positionPiece.put(position, new King(Team.BLACK));
        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThatThrownBy(() -> chessBoard.findPieceByPosition(A2))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @DisplayName("체스보드 특정 위치에 적대적 기물이 없는 지 확인할 수 있다")
    @Test
    void should_CheckThereIsHostilePiece_When_DestinationIsGiven() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        King hostile = new King(Team.WHITE);
        positionPiece.put(A1, hostile);
        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertAll(
                () -> assertThat(chessBoard.isNoHostilePieceAt(A2, Team.BLACK)).isTrue(),
                () -> assertThat(chessBoard.isNoHostilePieceAt(A1, Team.BLACK)).isFalse()
        );
    }
}
