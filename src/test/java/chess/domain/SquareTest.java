package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.NoPiece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.info.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Nested
    class isSamePosition {

        @Test
        void 같은_위치일_때_true_반환() {
            //given
            Square square = new Square(Position.from("a1"), new Bishop(Team.WHITE));
            Position position = Position.from("a1");

            //when
            boolean actual = square.isSamePosition(position);

            //then
            assertThat(actual).isTrue();
        }

        @Test
        void 다른_위치일_때_false_반환() {
            //given
            Square square = new Square(Position.from("a1"), new Bishop(Team.WHITE));
            Position position = Position.from("a2");

            //when
            boolean actual = square.isSamePosition(position);

            //then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    class isEmpty {

        @Test
        void NoPiece_기물의_경우_true를_반환한다() {
            //given
            Square square = new Square(Position.from("a1"), NoPiece.getInstance());

            //when
            boolean actual = square.isEmpty();

            //then
            assertThat(actual).isTrue();
        }

        @Test
        void NoPiece_기물이_아닌_경우_false를_반환한다() {
            //given
            Square square = new Square(Position.from("a1"), new Bishop(Team.WHITE));

            //when
            boolean actual = square.isEmpty();

            //then
            assertThat(actual).isFalse();
        }
    }


    @Nested
    class isSameTeam {

        @Test
        void 같은_팀인_경우_true_반환() {
            //given
            Square square = new Square(Position.from("a1"), new Bishop(Team.WHITE));

            //when
            boolean actual = square.isSameTeam(Team.WHITE);

            //then
            assertThat(actual).isTrue();
        }

        @Test
        void 같은_팀이_아닌_경우_false_반환() {
            //given
            Square square = new Square(Position.from("a1"), new Bishop(Team.WHITE));

            //when
            boolean actual = square.isSameTeam(Team.BLACK);

            //then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    class isKing {

        @Test
        void King_기물인_경우_true_반환() {
            //given
            Square square = new Square(Position.from("a1"), new King(Team.WHITE));

            //when
            boolean actual = square.isKing();

            //then
            assertThat(actual).isTrue();
        }

        @Test
        void King_기물이_아닌_경우_false_반환() {
            //given
            Square square = new Square(Position.from("a1"), new Bishop(Team.WHITE));

            //when
            boolean actual = square.isKing();

            //then
            assertThat(actual).isFalse();
        }
    }

    @Test
    void moveTo가_호출되면_원래_칸은_빈_기물이_되고_목적_칸은_원래_칸의_기물이_된다() {
        //given
        Square source = new Square(Position.from("a1"), new Queen(Team.WHITE));
        Square destination = new Square(Position.from("d4"), NoPiece.getInstance());

        //when
        source.moveTo(new Turn(), destination);
        PieceType sourceActual = PieceType.NOPIECE;
        PieceType destinationActual = PieceType.QUEEN;

        //then
        assertAll(
            () -> assertThat(source.findPieceType()).isEqualTo(sourceActual),
            () -> assertThat(destination.findPieceType()).isEqualTo(destinationActual)
        );
    }

    @Nested
    class isSameFileAndTeam {

        @Test
        void 같은_File과_팀인_경우_true_반환() {
            //given
            Square square = new Square(Position.from("a1"), new Bishop(Team.WHITE));

            //when
            boolean actual = square.isSameFileAndTeam(File.A, Team.WHITE);

            //then
            assertThat(actual).isTrue();
        }

        @Test
        void 같은_팀이_아닌_경우_false_반환() {
            //given
            Square square = new Square(Position.from("a1"), new Bishop(Team.WHITE));

            //when
            boolean actual = square.isSameFileAndTeam(File.A, Team.BLACK);

            //then
            assertThat(actual).isFalse();
        }

        @Test
        void 같은_File이_아닌_경우_false_반환() {
            //given
            Square square = new Square(Position.from("a1"), new Bishop(Team.WHITE));

            //when
            boolean actual = square.isSameFileAndTeam(File.B, Team.WHITE);

            //then
            assertThat(actual).isFalse();
        }
    }

    @Test
    void findPieceType을_호출하면_적절한_PieceType이_반환된다() {
        //given
        Square square = new Square(Position.from("a1"), new Bishop(Team.WHITE));
        PieceType expected = PieceType.BISHOP;
        //when
        PieceType actual = square.findPieceType();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}