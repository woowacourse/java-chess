package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.King;
import chess.domain.piece.Queen;
import chess.domain.piece.WhitePawn;
import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    @DisplayName("입력대로 보드를 움직인다.")
    @Test
    void movePiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        Team team = Team.WHITE;
        ChessGame chessGame = new ChessGame(board, team);

        chessGame.movePiece(new Movement(Position.of(2, 2), Position.of(4, 2)));
        Team actualCurrentTeam = chessGame.getCurrentTeam();

        assertAll(
                () -> assertThat(board.getPieces()).containsEntry(Position.of(4, 2), new WhitePawn()),
                () -> assertThat(actualCurrentTeam).isEqualTo(Team.BLACK)
        );
    }

    @DisplayName("차례가 아닌 움직임을 하면 예외가 발생한다.")
    @Test
    void movePieceTurnException() {
        Board board = new Board(BoardFactory.generateStartBoard());
        Team team = Team.WHITE;
        ChessGame chessGame = new ChessGame(board, team);

        assertThatThrownBy(() ->
                chessGame.movePiece(new Movement(Position.of(7, 2), Position.of(5, 2))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("백팀이 움직일 차례입니다.");
    }

    @DisplayName("체크를 벗어나지 않으면 예외가 발생한다.")
    @Test
    void movePieceCheckException() {
        Map<Position, Piece> boardMap = new HashMap<>();
        boardMap.put(Position.of(1, 1), new King(Team.WHITE));
        boardMap.put(Position.of(8, 8), new King(Team.BLACK));
        boardMap.put(Position.of(2, 2), new Queen(Team.BLACK));
        Board board = new Board(boardMap);
        Team team = Team.WHITE;
        ChessGame chessGame = new ChessGame(board, team);

        assertThatThrownBy(() ->
                chessGame.movePiece(new Movement(Position.of(1, 1), Position.of(1, 2))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("체크 상태를 벗어나지 않았습니다.");
    }
}
