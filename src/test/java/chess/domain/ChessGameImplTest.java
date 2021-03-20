package chess.domain;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

import chess.domain.piece.King;
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

class ChessGameImplTest {

    @Test
    @DisplayName("존재하지 않는 말을 선택할 때")
    void movePiece_pieceNotFoundException() {
        ChessGameImpl chessGame = ChessGameImpl.from(new ArrayList<>(), WHITE);
        Assertions.assertThatThrownBy(() ->
            chessGame.movePiece(Position.of(1,1), Position.of(2,2))
        ).isInstanceOf(PieceNotFoundException.class);
    }

    @Test
    @DisplayName("현재 턴의 해당하는 팀이 아닌 다른 팀의 말을 움직일 때")
    void movePiece_invalidTurnException() {
        Queen queen = new Queen(BLACK, Position.of(1, 1));
        ChessGameImpl chessGame = ChessGameImpl.from(Collections.singletonList(queen), WHITE);
        Assertions.assertThatThrownBy(() ->
            chessGame.movePiece(queen.currentPosition(), Position.of(2,2))
        ).isInstanceOf(InvalidTurnException.class);
    }

    @Test
    @DisplayName("움직일 수 없는 곳으로 이동할 때")
    void movePiece_impossibleMoveException() {
        Queen queen = new Queen(WHITE, Position.of(1, 1));
        ChessGameImpl chessGame = ChessGameImpl.from(Collections.singletonList(queen), WHITE);
        Assertions.assertThatThrownBy(() ->
            chessGame.movePiece(queen.currentPosition(), Position.of(3,2))
        ).isInstanceOf(ImpossibleMoveException.class);
    }

    @Test
    @DisplayName("정상적으로 움직일 때")
    void movePiece() {
        Queen queen = new Queen(WHITE, Position.of(1, 1));
        Knight knight = new Knight(BLACK, Position.of(4,4));
        ChessGameImpl chessGame = ChessGameImpl.from(Arrays.asList(queen, knight), WHITE);
        chessGame.movePiece(queen.currentPosition(), knight.currentPosition());

        Assertions.assertThat(chessGame.pieceByPosition(knight.currentPosition())).contains(queen);
        Assertions.assertThat(chessGame.pieceByPosition(Position.of(1,1))).isEmpty();
    }

    @Test
    @DisplayName("처음 시작할 때의 스코어")
    void gameResult_initialScore() {
        ChessGameImpl chessGame = ChessGameImpl.initialGame();
        GameResult gameResult = chessGame.gameResult();

        Assertions.assertThat(gameResult.whiteTeamScore()).isEqualTo(new Score(38));
        Assertions.assertThat(gameResult.blackTeamScore()).isEqualTo(new Score(38));
    }

    @Test
    @DisplayName("같은 행에 폰이 3개일 경우 점수 계산")
    void gameResult_pawn_sameColumn() {
        ChessGameImpl chessGame = ChessGameImpl.from(
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
        ChessGameImpl chessGame = ChessGameImpl.from(
            Arrays.asList(
                new Pawn(WHITE, Position.of(3, 4)),
                new Pawn(WHITE, Position.of(2, 4))
            ), WHITE
        );
        GameResult gameResult = chessGame.gameResult();

        Assertions.assertThat(gameResult.whiteTeamScore()).isEqualTo(new Score(2));
        Assertions.assertThat(gameResult.winner()).isEqualTo(WHITE);
    }

    @Test
    @DisplayName("킹이 죽었는 지 확인")
    void kingDead() {
        Queen whiteQueen = new Queen(WHITE, Position.of(2, 2));
        King blackKing = new King(BLACK, Position.of(3, 3));
        ChessGameImpl chessGame = ChessGameImpl.from(
            Arrays.asList(whiteQueen, blackKing), WHITE
        );

        chessGame.movePiece(whiteQueen.currentPosition(), blackKing.currentPosition());

        Assertions.assertThat(chessGame.isKingDead()).isTrue();
    }

    @Test
    @DisplayName("체크할 때")
    void kingCheck() {
        Queen whiteQueen = new Queen(WHITE, Position.of(2, 0));
        King blackKing = new King(BLACK, Position.of(3, 3));
        ChessGameImpl chessGame = ChessGameImpl.from(
            Arrays.asList(whiteQueen, blackKing), WHITE
        );

        chessGame.movePiece(whiteQueen.currentPosition(), Position.of(2,2));
        Assertions.assertThat(chessGame.isChecked()).isTrue();
    }

    @Test
    @DisplayName("체크메이트 시")
    void checkmate() {
        Queen whiteQueen1 = new Queen(WHITE, Position.of(6, 0));
        Queen whiteQueen2 = new Queen(WHITE, Position.of(5, 0));
        King blackKing = new King(BLACK, Position.of(0, 0));
        ChessGameImpl chessGame = ChessGameImpl.from(
            Arrays.asList(whiteQueen1, whiteQueen2, blackKing), WHITE
        );
        chessGame.movePiece(whiteQueen1.currentPosition(), Position.of(6,1));
        Assertions.assertThat(chessGame.isCheckmate()).isTrue();
    }
}