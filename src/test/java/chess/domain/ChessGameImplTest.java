package chess.domain;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.exception.ImpossibleMoveException;
import chess.exception.InvalidTurnException;
import chess.exception.PieceNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameImplTest {

    @Test
    @DisplayName("존재하지 않는 말을 선택할 때")
    void movePiece_pieceNotFoundException() {
        ChessGameImpl chessGame = ChessGameImpl.from(Pieces.emptyPieces(), WHITE);
        Assertions.assertThatThrownBy(() ->
            chessGame.movePiece(Position.of(1, 1), Position.of(2, 2))
        ).isInstanceOf(PieceNotFoundException.class);
    }

    @Test
    @DisplayName("현재 턴의 해당하는 팀이 아닌 다른 팀의 말을 움직일 때")
    void movePiece_invalidTurnException() {
        Queen queen = new Queen(BLACK, Position.of(1, 1));
        ChessGameImpl chessGame = ChessGameImpl
            .from(Pieces.from(Collections.singletonList(queen)), WHITE);
        Assertions.assertThatThrownBy(() ->
            chessGame.movePiece(queen.currentPosition(), Position.of(2, 2))
        ).isInstanceOf(InvalidTurnException.class);
    }

    @Test
    @DisplayName("움직일 수 없는 곳으로 이동할 때")
    void movePiece_impossibleMoveException() {
        Queen queen = new Queen(WHITE, Position.of(1, 1));
        ChessGameImpl chessGame = ChessGameImpl
            .from(Pieces.from(Collections.singletonList(queen)), WHITE);
        Assertions.assertThatThrownBy(() ->
            chessGame.movePiece(queen.currentPosition(), Position.of(3, 2))
        ).isInstanceOf(ImpossibleMoveException.class);
    }

    @Test
    @DisplayName("정상적으로 움직일 때")
    void movePiece() {
        Queen queen = new Queen(WHITE, Position.of(1, 1));
        Knight knight = new Knight(BLACK, Position.of(4, 4));
        Pieces pieces = Pieces.from(Arrays.asList(queen, knight));
        ChessGameImpl chessGame = ChessGameImpl.from(pieces, WHITE);
        chessGame.movePiece(queen.currentPosition(), knight.currentPosition());

        Assertions.assertThat(pieces.pieceByPosition(knight.currentPosition())).contains(queen);
        Assertions.assertThat(pieces.pieceByPosition(Position.of(1, 1))).isEmpty();
    }

    @Test
    @DisplayName("킹이 죽었는 지 확인")
    void kingDead() {
        Queen whiteQueen = new Queen(WHITE, Position.of(2, 2));
        King blackKing = new King(BLACK, Position.of(3, 3));
        Pieces pieces = Pieces.from(Arrays.asList(whiteQueen, blackKing));
        ChessGameImpl chessGame = ChessGameImpl.from(pieces, WHITE);

        chessGame.movePiece(whiteQueen.currentPosition(), blackKing.currentPosition());

        Assertions.assertThat(chessGame.isKingDead()).isTrue();
    }

    @Test
    @DisplayName("체크할 때")
    void kingCheck() {
        Queen whiteQueen = new Queen(WHITE, Position.of(2, 0));
        King blackKing = new King(BLACK, Position.of(3, 3));
        ChessGameImpl chessGame = ChessGameImpl.from(Pieces.from(whiteQueen, blackKing), WHITE);

        chessGame.movePiece(whiteQueen.currentPosition(), Position.of(2, 2));
        Assertions.assertThat(chessGame.isChecked()).isTrue();
    }

    @Test
    @DisplayName("체크메이트 시")
    void checkmate() {
        Queen whiteQueen1 = new Queen(WHITE, Position.of(6, 0));
        Queen whiteQueen2 = new Queen(WHITE, Position.of(5, 0));
        King blackKing = new King(BLACK, Position.of(0, 0));
        ChessGameImpl chessGame = ChessGameImpl.from(
            Pieces.from(whiteQueen1, whiteQueen2, blackKing), WHITE
        );
        chessGame.movePiece(whiteQueen1.currentPosition(), Position.of(6, 1));
        Assertions.assertThat(chessGame.isCheckmate()).isTrue();
    }
}