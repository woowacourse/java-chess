package chess.domain.board;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.Pieces;
import chess.domain.piece.Position;
import chess.domain.piece.black.*;
import chess.domain.piece.white.*;
import chess.exception.NoSuchPermittedChessPieceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @DisplayName("포지션에 있는 체스 말을 가져오고 옮긴다. 만일 자신의 말이 아니거나 말이 없다면 예외")
    @Test
    void move() {
        Pieces<BlackPiece> blackPiece = new Pieces<>(Collections.singletonList(
                BlackPawn.createWithCoordinate(0, 1)
        ));

        Pieces<WhitePiece> whitePiece = new Pieces<>(Collections.singletonList(
                WhitePawn.createWithCoordinate(0, 0)
        ));

        Board board = new Board(blackPiece, whitePiece);


        assertThatThrownBy(() -> board.moveBlackPiece(new Position(0, 0), new Position(0, 1)))
                .isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);

        assertThatThrownBy(() -> board.moveWhitePiece(new Position(0, 1), new Position(0, 0)))
                .isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);
    }

    @DisplayName("포지션의 상대편의 말이 있다면 상대 말을 없앤다")
    @Test
    void catchPiece() {
        List<ChessPiece> pieces = Arrays.asList(
                WhitePawn.createWithCoordinate(0, 0),
                BlackPawn.createWithCoordinate(0, 0)
        );

        Board board = new Board(pieces);

        board.catchBlackPiece();
        assertThat(board.getAllPieces()).containsExactly(WhitePawn.createWithCoordinate(0, 0));
    }

    @DisplayName("블랙팀의 점수를 반환한다.")
    @Test
    void getBlackScore() {
        List<ChessPiece> pieces = Arrays.asList(
                BlackPawn.createWithCoordinate(0, 1),
                BlackPawn.createWithCoordinate(1, 1),
                BlackPawn.createWithCoordinate(0, 2),
                BlackQueen.createWithCoordinate(2, 1),
                BlackRook.createWithCoordinate(3, 1),
                BlackKing.createWithCoordinate(4, 1),
                BlackKnight.createWithCoordinate(5, 1),
                BlackBishop.createWithCoordinate(6, 1)
        );

        Board board = new Board(pieces);

        assertThat(board.getBlackScore()).isEqualTo(21.0);
    }

    @DisplayName("하얀팀의 점수를 반환한다.")
    @Test
    void getWhiteScore() {
        List<ChessPiece> pieces = Arrays.asList(
                WhitePawn.createWithCoordinate(0, 1),
                WhitePawn.createWithCoordinate(1, 1),
                WhitePawn.createWithCoordinate(0, 2),
                WhiteQueen.createWithCoordinate(2, 1),
                WhiteRook.createWithCoordinate(3, 1),
                WhiteKing.createWithCoordinate(4, 1),
                WhiteKnight.createWithCoordinate(5, 1),
                WhiteBishop.createWithCoordinate(6, 1)
        );

        Board board = new Board(pieces);

        assertThat(board.getWhiteScore()).isEqualTo(21.0);
    }

    @DisplayName("체스판에 블랙, 화이트 킹이 둘 다 존재하는지 확인한다.")
    @Test
    void isKingsExist() {
        List<ChessPiece> pieces = Arrays.asList(
                WhiteKing.createWithCoordinate(0, 0),
                BlackKing.createWithCoordinate(0, 0)
        );

        Board board = new Board(pieces);
        assertThat(board.isKingCaught()).isFalse();

        pieces = Collections.singletonList(
                WhiteKing.createWithCoordinate(0, 0)
        );

        board = new Board(pieces);
        assertThat(board.isKingCaught()).isTrue();
    }
}