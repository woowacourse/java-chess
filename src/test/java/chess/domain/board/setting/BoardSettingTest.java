package chess.domain.board.setting;

import static chess.domain.board.type.Rank.EIGHT;
import static chess.domain.board.type.Rank.ONE;
import static chess.domain.board.type.Rank.SEVEN;
import static chess.domain.board.type.Rank.TWO;
import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.type.File;
import chess.domain.board.type.Rank;
import chess.domain.game.ChessGame;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardSettingTest {
    private Board board;
    private List<Piece> blackPiecesExceptPawns;
    private List<Piece> whitePiecesExceptPawns;

    @BeforeEach
    void setUp() {
        board = new ChessGame(new BoardDefaultSetting()).board();
        blackPiecesExceptPawns = Arrays.asList(
            new Rook(BLACK),
            new Knight(BLACK),
            new Bishop(BLACK),
            new Queen(BLACK),
            new King(BLACK),
            new Bishop(BLACK),
            new Knight(BLACK),
            new Rook(BLACK)
        );
        whitePiecesExceptPawns = Arrays.asList(
            new Rook(WHITE),
            new Knight(WHITE),
            new Bishop(WHITE),
            new Queen(WHITE),
            new King(WHITE),
            new Bishop(WHITE),
            new Knight(WHITE),
            new Rook(WHITE)
        );
    }

    @DisplayName("보드 기물 초기 세팅")
    @Test
    void initialBoardPiecesSetting() {
        for (int i = 0; i < 8; i++) {
            File file = File.values()[i];
            assertThat(board.findPiece(Position.of(file, EIGHT)))
                .isEqualTo(blackPiecesExceptPawns.get(i));

            assertThat(board.findPiece(Position.of(file, SEVEN)))
                .isEqualTo(new Pawn(BLACK));

            assertEmptyCellsByFile(file);

            assertThat(board.findPiece(Position.of(file, TWO)))
                .isEqualTo(new Pawn(WHITE));

            assertThat(board.findPiece(Position.of(file, ONE)))
                .isEqualTo(whitePiecesExceptPawns.get(i));
        }
    }

    private void assertEmptyCellsByFile(File file) {
        for (int rankOrder = 3; rankOrder < 7; rankOrder++) {
            assertThat(
                board.findCell(Position.of(file, Rank.of(String.valueOf(rankOrder)))).isEmpty())
                .isTrue();
        }
    }
}