package chess.domain;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
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

    @Test
    @DisplayName("처음 시작할 때의 스코어")
    void gameResult_initialScore() {
        ChessGame chessGame = ChessGame.initialGame();
        GameResult gameResult = chessGame.gameResult();

        Assertions.assertThat(gameResult.whiteTeamScore()).isEqualTo(new Score(38));
        Assertions.assertThat(gameResult.blackTeamScore()).isEqualTo(new Score(38));
    }

    @Test
    @DisplayName("같은 행에 폰이 3개일 경우 점수 계산")
    void gameResult_pawn_sameColumn() {
        ChessGame chessGame = ChessGame.from(
            Arrays.asList(
                new Pawn(WHITE, Position.of(2, 3)),
                new Pawn(WHITE, Position.of(2, 4))
            ), WHITE
        );
        GameResult gameResult = chessGame.gameResult();

        Assertions.assertThat(gameResult.whiteTeamScore()).isEqualTo(new Score(1.0));
    }

    @Test
    @DisplayName("다른 행에 폰이 3개일 경우 점수 계산")
    void gameResult_pawn_distinctColumn() {
        ChessGame chessGame = ChessGame.from(
            Arrays.asList(
                new Pawn(WHITE, Position.of(3, 4)),
                new Pawn(WHITE, Position.of(2, 4))
            ), WHITE
        );
        GameResult gameResult = chessGame.gameResult();

        Assertions.assertThat(gameResult.whiteTeamScore()).isEqualTo(new Score(2));
        Assertions.assertThat(gameResult.winner()).isEqualTo(WHITE);
    }
}