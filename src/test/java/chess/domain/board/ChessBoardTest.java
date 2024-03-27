package chess.domain.board;

import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Rook;
import chess.domain.position.Position;
import chess.dto.BoardStatus;
import chess.dto.PieceInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ChessBoardTest {

    @DisplayName("보드 정보를 통해 체스보드를 생성한다")
    @Test
    void createChessBoard() {
        assertThatCode(() -> new ChessBoard(ChessBoardGenerator.getInstance().generate()))
                .doesNotThrowAnyException();
    }

    @DisplayName("체스 보드는 기물을 움직일 수 있다.")
    @Test
    void move() {
        // given
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.getInstance().generate());

        // when
        chessBoard.move("b2", "b3", Turn.first());

        // then
        BoardStatus boardStatus = chessBoard.status();
        List<PieceInfo> pieceInfos = boardStatus.pieceInfos();

        assertAll(
                () -> assertThat(pieceInfos.contains(new PieceInfo(1, 2, "PAWN", "WHITE"))).isTrue(),
                () -> assertThat(pieceInfos.contains(new PieceInfo(1, 1, "PAWN", "WHITE"))).isFalse()
        );
    }

    @DisplayName("Source에 기물이 존재하지 않으면 이동할 수 없다.")
    @Test
    void notExistSource() {
        // given
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.getInstance().generate());

        // when & then
        assertThatThrownBy(() -> chessBoard.move("b3", "b2", Turn.first()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source에 이동할 수 있는 기물이 존재하지 않습니다.");
    }

    @DisplayName("같은 색상의 기물을 연속해서 움직일 수 없다.")
    @Test
    void isNotTurn() {
        // given
        HashMap<Position, Piece> board = new HashMap<>();
        board.put(Position.of("b2"), new Pawn(PieceColor.WHITE));
        board.put(Position.of("a1"), new Rook(PieceColor.WHITE));

        ChessBoard chessBoard = new ChessBoard(board);
        Turn turn = Turn.first();

        // when
        chessBoard.move("b2", "b3", turn);
        turn.next();

        // then
        assertThatThrownBy(() -> chessBoard.move("a1", "h1", turn))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PieceColor.WHITE.name() + "의 차례가 아닙니다.");
    }

    @DisplayName("Source와 Target이 같으면 이동할 수 없다.")
    @Test
    void isSamePosition() {
        // given
        HashMap<Position, Piece> board = new HashMap<>();
        board.put(Position.of("b2"), new Pawn(PieceColor.WHITE));

        ChessBoard chessBoard = new ChessBoard(board);

        // when & then
        assertThatThrownBy(() -> chessBoard.move("b2", "b2", Turn.first()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source와 target이 같을 수 없습니다.");
    }

    @DisplayName("Source와 Target이 같은 색이면 이동할 수 없다.")
    @Test
    void isTargetSameColor() {
        // given
        HashMap<Position, Piece> board = new HashMap<>();
        board.put(Position.of("b2"), new Pawn(PieceColor.WHITE));
        board.put(Position.of("b3"), new Pawn(PieceColor.WHITE));

        ChessBoard chessBoard = new ChessBoard(board);

        // when & then
        assertThatThrownBy(() -> chessBoard.move("b2", "b3", Turn.first()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("target으로 이동할 수 없습니다.");
    }

    @DisplayName("기물이 이동할 수 없는 방식으로 움직이면 예외를 발생한다.")
    @Test
    void validatePieceMovement() {
        // given
        HashMap<Position, Piece> board = new HashMap<>();
        board.put(Position.of("b2"), new Pawn(PieceColor.WHITE));

        ChessBoard chessBoard = new ChessBoard(board);

        // when & then
        assertThatThrownBy(() -> chessBoard.move("b2", "b7", Turn.first()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물이 이동할 수 없는 방식입니다.");
    }

    @DisplayName("나이트는 Source와 Target 사이에 다른 기물이 존재해도 이동할 수 있다.")
    @Test
    void canKnightMove() {
        // given
        HashMap<Position, Piece> board = new HashMap<>();
        board.put(Position.of("b2"), new Knight(PieceColor.WHITE));
        board.put(Position.of("b3"), new Pawn(PieceColor.WHITE));

        ChessBoard chessBoard = new ChessBoard(board);

        // when & then
        assertThatCode(() -> chessBoard.move("b2", "c4", Turn.first())).doesNotThrowAnyException();
    }
}
