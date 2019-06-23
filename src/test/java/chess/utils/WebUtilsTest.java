package chess.utils;

import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.piece.King;
import chess.domain.piece.Knight;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WebUtilsTest {
    @Test
    void 화이트팀_Piece에_대하여_확인() {
        assertThat(WebUtils.caseChanger(new King(Team.WHITE))).isEqualTo("k");
        assertThat(WebUtils.caseChanger(new Knight(Team.WHITE))).isEqualTo("n");
    }


    @Test
    void 블랙팀_Piece에_대하여_확인() {
        assertThat(WebUtils.caseChanger(new King(Team.BLACK))).isEqualTo("k".toUpperCase());
        assertThat(WebUtils.caseChanger(new Knight(Team.BLACK))).isEqualTo("n".toUpperCase());
    }
}