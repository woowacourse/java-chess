package chess.domain;

import chess.domain.command.Command;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameTest {

    @DisplayName("현재 기물 확인하기")
    @Test
    void 현재_기물_확인_테스트() {
        ChessGame chessGame = new ChessGame();

        assertThat(chessGame.getCurrentPieces()).isInstanceOf(Pieces.class);
    }

    @DisplayName("비숍 이동 - target에 상대 말이 있는 경우")
    @Test
    void 상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Bishop(Position.of('c', '8'), Color.WHITE),
                new Pawn(Position.of('f', '5'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        ChessGame chessGame = new ChessGame(pieces);
        Command command = new Command("move c8 f5");
        chessGame.movePieceFromSourceToTarget(command);

        assertThat(pieces.getPieces().size()).isEqualTo(1);
    }

    @DisplayName("킹 - 대각선 위치인 target에 상대 말이 있는 경우")
    @Test
    void 상대편_말을_공격한다_대각선() {
        List<Piece> current = Arrays.asList(
                new King(Position.of('d', '8'), Color.WHITE),
                new Pawn(Position.of('e', '7'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        ChessGame chessGame = new ChessGame(pieces);
        Command command = new Command("move d8 e7");
        chessGame.movePieceFromSourceToTarget(command);

        assertThat(pieces.getPieces().size()).isEqualTo(1);
    }

    @DisplayName("킹 - 십자 위치인 target에 상대 말이 있는 경우")
    @Test
    void 상대편_말을_공격한다_십자() {
        List<Piece> current = Arrays.asList(
                new King(Position.of('d', '8'), Color.WHITE),
                new Pawn(Position.of('d', '7'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        ChessGame chessGame = new ChessGame(pieces);
        Command command = new Command("move d8 d7");
        chessGame.movePieceFromSourceToTarget(command);

        assertThat(pieces.getPieces().size()).isEqualTo(1);
    }

    @DisplayName("나이트 - target에 상대 말이 있는 경우")
    @Test
    void 나이트_상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Knight(Position.of('b', '8'), Color.WHITE),
                new Pawn(Position.of('d', '7'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        ChessGame chessGame = new ChessGame(pieces);
        Command command = new Command("move b8 d7");
        chessGame.movePieceFromSourceToTarget(command);

        assertThat(pieces.getPieces().size()).isEqualTo(1);
    }

    @DisplayName("퀸 - 십자 위치인 target에 상대 말이 있는 경우=")
    @Test
    void 퀸_상대편_말을_공격한다_십자() {
        List<Piece> current = Arrays.asList(
                new Queen(Position.of('d', '8'), Color.WHITE),
                new Pawn(Position.of('d', '1'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        ChessGame chessGame = new ChessGame(pieces);
        Command command = new Command("move d8 d1");
        chessGame.movePieceFromSourceToTarget(command);

        assertThat(pieces.getPieces().size()).isEqualTo(1);
    }

    @DisplayName("퀸 - 대각선 위치인 target에 상대 말이 있는 경우")
    @Test
    void 퀸_상대편_말을_공격한다_대각선() {
        List<Piece> current = Arrays.asList(
                new Queen(Position.of('d', '8'), Color.WHITE),
                new Pawn(Position.of('d', '1'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        ChessGame chessGame = new ChessGame(pieces);
        Command command = new Command("move d8 d1");
        chessGame.movePieceFromSourceToTarget(command);

        assertThat(pieces.getPieces().size()).isEqualTo(1);
    }

    @DisplayName("룩 - target에 상대 말이 있는 경우")
    @Test
    void 룩_상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Rook(Position.of('a', '8'), Color.WHITE),
                new Pawn(Position.of('a', '5'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        ChessGame chessGame = new ChessGame(pieces);
        Command command = new Command("move a8 a5");
        chessGame.movePieceFromSourceToTarget(command);

        assertThat(pieces.getPieces().size()).isEqualTo(1);
    }
}
