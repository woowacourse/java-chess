package chess.domain;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.exception.ImpossibleMoveException;
import chess.exception.InvalidTurnException;
import chess.exception.PieceNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("존재하지 않는 말을 선택할 때")
    void movePiece_pieceNotFoundException() {
        ChessGame chessGame = ChessGame.from(new ArrayList<>(), WHITE);
        Assertions.assertThatThrownBy(() ->
            chessGame.movePiece(Position.of(1,1), Position.of(2,2))
        ).isInstanceOf(PieceNotFoundException.class);
    }

    @Test
    @DisplayName("현재 턴의 해당하는 팀이 아닌 다른 팀의 말을 움직일 때")
    void movePiece_invalidTurnException() {
        Queen queen = new Queen(BLACK, Position.of(1, 1));
        ChessGame chessGame = ChessGame.from(Collections.singletonList(queen), WHITE);
        Assertions.assertThatThrownBy(() ->
            chessGame.movePiece(queen.currentPosition(), Position.of(2,2))
        ).isInstanceOf(InvalidTurnException.class);
    }

    @Test
    @DisplayName("움직일 수 없는 곳으로 이동할 때")
    void movePiece_impossibleMoveException() {
        Queen queen = new Queen(WHITE, Position.of(1, 1));
        ChessGame chessGame = ChessGame.from(Collections.singletonList(queen), WHITE);
        Assertions.assertThatThrownBy(() ->
            chessGame.movePiece(queen.currentPosition(), Position.of(3,2))
        ).isInstanceOf(ImpossibleMoveException.class);
    }

    @Test
    @DisplayName("정상적으로 움직일 때")
    void movePiece() {
        Queen queen = new Queen(WHITE, Position.of(1, 1));
        Knight knight = new Knight(BLACK, Position.of(4,4));
        ChessGame chessGame = ChessGame.from(Arrays.asList(queen, knight), WHITE);
        chessGame.movePiece(queen.currentPosition(), knight.currentPosition());

        Assertions.assertThat(chessGame.pieceByPosition(knight.currentPosition())).contains(queen);
        Assertions.assertThat(chessGame.pieceByPosition(Position.of(1,1))).isEmpty();
    }
}