package chess.domain;

import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    @DisplayName("생성 테스트")
    void create() {
        Assertions.assertThatCode(() -> new ChessBoard())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("대상 위치에 존재하는 기물을 타켓 위치로 옮긴다.")
    void movePiece() {
        ChessBoard chessBoard = new ChessBoard();
        Position source = Position.of('b', 2);
        Position target = Position.of('b', 3);
        Piece pieceBeforeMove = chessBoard.findPieceByPosition(source);

        chessBoard.move(source, target);

        Piece pieceAfterMove = chessBoard.findPieceByPosition(target);
        Assertions.assertThat(pieceBeforeMove).isEqualTo(pieceAfterMove);
    }

    @Test
    @DisplayName("기물이 이동하면 기존 위치에는 기물이 존재하지 않게 된다.")
    void noPieceOnSourcePositionWhenPieceMoves() {
        ChessBoard chessBoard = new ChessBoard();
        Position source = Position.of('b', 2);
        Position target = Position.of('b', 3);

        chessBoard.move(source, target);

        Piece pieceAtSourcePositionAfterMove = chessBoard.findPieceByPosition(source);
        Assertions.assertThat(pieceAtSourcePositionAfterMove).isNull();
    }
}
