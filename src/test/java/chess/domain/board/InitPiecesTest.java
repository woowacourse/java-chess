package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InitPiecesTest {

    private List<Rank> ranks;

    @BeforeEach
    void setUp() {
        this.ranks = InitPieces.initRanks();
    }

    @Test
    @DisplayName("InitPieces에서 반환 받은 초기 체스보드 크기 확인")
    void testInitBoardSize() {
        assertThat(this.ranks).hasSize(8);
        for (Rank rank : this.ranks) {
            assertThat(rank.squares()).hasSize(8);
        }
    }

    @Test
    @DisplayName("InitPieces에서 반환 받은 초기 체스보드 체스말 확인")
    void testInitBoard() {
        Rank rank8 = this.ranks.get(0);
        assertThat(rank8.squares()).containsExactly(
            Rook.createBlack(),
            Knight.createBlack(),
            Bishop.createBlack(),
            Queen.createBlack(),
            King.createBlack(),
            Bishop.createBlack(),
            Knight.createBlack(),
            Rook.createBlack()
        );

        Rank rank7 = this.ranks.get(1);
        assertThat(rank7.squares()).containsExactly(
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack()
        );

        Rank rank6 = this.ranks.get(2);
        assertThat(rank6.squares()).containsExactly(
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create()
        );

        Rank rank5 = this.ranks.get(3);
        assertThat(rank5.squares()).containsExactly(
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create()
        );

        Rank rank4 = this.ranks.get(4);
        assertThat(rank4.squares()).containsExactly(
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create()
        );

        Rank rank3 = this.ranks.get(5);
        assertThat(rank3.squares()).containsExactly(
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create()
        );

        Rank rank2 = this.ranks.get(6);
        assertThat(rank2.squares()).containsExactly(
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite()
        );

        Rank rank1 = this.ranks.get(7);
        assertThat(rank1.squares()).containsExactly(
            Rook.createWhite(),
            Knight.createWhite(),
            Bishop.createWhite(),
            Queen.createWhite(),
            King.createWhite(),
            Bishop.createWhite(),
            Knight.createWhite(),
            Rook.createWhite()
        );
    }


}
