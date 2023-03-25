package chess.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.board.Board;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.controller.GameStatus;
import chess.piece.AllPiecesGenerator;
import chess.piece.Pieces;
import chess.piece.Side;
import chess.piece.type.King;
import chess.piece.type.Knight;
import chess.piece.type.Pawn;
import chess.piece.type.Queen;
import chess.piece.type.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("흰색 진영이 기물을 옮길 차례일 때, 검은색 진영의 기물을 옮기면 예외가 발생한다.")
    void checkTurnToMove_whiteTurn_throws() {
        // given
        final ChessGame chessGame = new ChessGame(new Board(new Pieces(new AllPiecesGenerator())),
                Side.WHITE,
                GameStatus.START);
        final Position blackPiecePosition = new Position(File.A, Rank.SEVEN);
        final Position targetPosition = new Position(File.A, Rank.SIX);

        // when, then
        assertThatThrownBy(() -> chessGame.movePiece(blackPiecePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대방의 말은 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("검은색 진영이 기물을 옮길 차례일 때, 흰색 진영의 기물을 옮기면 예외가 발생한다.")
    void checkTurnToMove_blackTurn_throws() {
        // given
        final ChessGame chessGame = new ChessGame(new Board(new Pieces(new AllPiecesGenerator())),
                Side.BLACK,
                GameStatus.START);
        final Position whitePiecePosition = new Position(File.A, Rank.TWO);
        final Position targetPosition = new Position(File.A, Rank.THREE);

        // when, then
        assertThatThrownBy(() -> chessGame.movePiece(whitePiecePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대방의 말은 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("한 파일 위에 폰이 여러개 존재한다면, 해당 폰은 개당 0.5점으로 계산한다.")
    void calculateScoreBySide() {
        // given
        Board fixedBoard = new Board(new Pieces(() -> List.of(
                new Pawn(new Position(File.A, Rank.ONE), Side.WHITE),
                new Pawn(new Position(File.A, Rank.THREE), Side.WHITE),

                new Pawn(new Position(File.B, Rank.TWO), Side.WHITE),
                new Pawn(new Position(File.B, Rank.THREE), Side.WHITE),
                new Pawn(new Position(File.B, Rank.SEVEN), Side.WHITE),

                new Knight(new Position(File.B, Rank.ONE), Side.WHITE)
        )));
        final ChessGame chessGame = new ChessGame(fixedBoard, Side.WHITE, GameStatus.START);

        // when, then
        assertThat(chessGame.calculateScoreBySide(Side.WHITE)).isEqualTo(5);
    }

    @Test
    @DisplayName("킹이 죽으면 게임이 끝난다.")
    void gameEnd_kingDead() {
        // given
        Board fixedBoard = new Board(new Pieces(() -> List.of(
                new King(new Position(File.E, Rank.ONE), Side.WHITE),
                new Rook(new Position(File.A, Rank.ONE), Side.WHITE),
                new Knight(new Position(File.B, Rank.ONE), Side.WHITE),

                new King(new Position(File.A, Rank.EIGHT), Side.BLACK),
                new Queen(new Position(File.D, Rank.EIGHT), Side.BLACK),
                new Pawn(new Position(File.B, Rank.SEVEN), Side.BLACK)
        )));
        final ChessGame chessGame = new ChessGame(fixedBoard, Side.WHITE, GameStatus.START);
        final GameStatus blackKingAliveStatus = chessGame.status();

        // when
        final Position whiteRookPosition = new Position(File.A, Rank.ONE);
        final Position blackKingPosition = new Position(File.A, Rank.EIGHT);
        chessGame.movePiece(whiteRookPosition, blackKingPosition);
        final GameStatus blackKingDeadStatus = chessGame.status();

        // then
        assertThat(blackKingAliveStatus).isSameAs(GameStatus.START);
        assertThat(blackKingDeadStatus).isSameAs(GameStatus.END);
    }
}
