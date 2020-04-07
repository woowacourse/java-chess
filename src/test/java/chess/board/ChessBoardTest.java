package chess.board;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.game.ChessSet;
import chess.location.Col;
import chess.location.Location;
import chess.location.Row;
import chess.piece.type.*;
import chess.player.Player;
import chess.score.Score;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {
    @DisplayName("생성자 테스트")
    @Test
    void name() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        assertThat(chessBoard).isInstanceOf(ChessBoard.class);
    }

    @DisplayName("팀에 따른 모든 말들을 가져온다.")
    @Test
    void giveMyPiece() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Map<Location, Piece> givenPieces = chessBoard.giveMyPiece(Team.BLACK);

        Map<Location, Piece> actual = new HashMap<>();
        putNoble(actual, 8);
        putPawns(actual, 7);

        assertThat(givenPieces).isEqualTo(actual);
    }

    private void putNoble(Map<Location, Piece> board, int row) {
        board.put(new Location(row, 'a'), new Rook(Team.BLACK));
        board.put(new Location(row, 'b'), new Knight(Team.BLACK));
        board.put(new Location(row, 'c'), new Bishop(Team.BLACK));
        board.put(new Location(row, 'd'), new Queen(Team.BLACK));
        board.put(new Location(row, 'e'), new King(Team.BLACK));
        board.put(new Location(row, 'f'), new Bishop(Team.BLACK));
        board.put(new Location(row, 'g'), new Knight(Team.BLACK));
        board.put(new Location(row, 'h'), new Rook(Team.BLACK));
    }

    private void putPawns(Map<Location, Piece> board, int row) {
        for (int i = 0; i < 8; i++) {
            board.put(new Location(row, (char) (i + 'a')), new Pawn(Team.BLACK));
        }
    }
}