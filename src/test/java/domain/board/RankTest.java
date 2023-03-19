package domain.board;

import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.square.Camp;
import domain.square.ConcreteSquare;
import domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("0행은 정상 생성이 된다")
    void constructorTestZero() {
        Rank rank = new Rank(0, 8);

        List<Square> expected = List.of(
                new ConcreteSquare(new Rook(), Camp.WHITE),
                new ConcreteSquare(new Knight(), Camp.WHITE),
                new ConcreteSquare(new Bishop(), Camp.WHITE),
                new ConcreteSquare(new Queen(), Camp.WHITE),
                new ConcreteSquare(new King(), Camp.WHITE),
                new ConcreteSquare(new Bishop(), Camp.WHITE),
                new ConcreteSquare(new Knight(), Camp.WHITE),
                new ConcreteSquare(new Rook(), Camp.WHITE)
        );

        assertThat(rank.getSquares()).containsAll(expected);
    }

    @Test
    @DisplayName("1행은 정상 생성이 된다")
    void constructorTestOne() {
        Rank rank = new Rank(1, 8);

        List<Square> expected = IntStream.range(0, 8)
                .mapToObj(i -> new WhitePawn())
                .map(pawn -> new ConcreteSquare(pawn, Camp.WHITE))
                .collect(Collectors.toList());

        assertThat(rank.getSquares()).containsAll(expected);
    }

    @Test
    @DisplayName("빈 행은 정상 생성이 된다")
    void constructorTestThreeToFive() {
        Rank rankTwo = new Rank(2, 8);
        Rank rankThree = new Rank(3, 8);
        Rank rankFour = new Rank(4, 8);
        Rank rankFive = new Rank(5, 8);

        assertThat(rankTwo.getSquares())
                .map(Square::getPieceType)
                .allMatch(Objects::isNull);
        assertThat(rankThree.getSquares())
                .map(Square::getPieceType)
                .allMatch(Objects::isNull);
        assertThat(rankFour.getSquares())
                .map(Square::getPieceType)
                .allMatch(Objects::isNull);
        assertThat(rankFive.getSquares())
                .map(Square::getPieceType)
                .allMatch(Objects::isNull);
    }

    @Test
    @DisplayName("6행은 정상 생성이 된다")
    void constructorTestSix() {
        Rank rank = new Rank(6, 8);

        List<Square> expected = IntStream.range(0, 8)
                .mapToObj(i -> new BlackPawn())
                .map(pawn -> new ConcreteSquare(pawn, Camp.BLACK))
                .collect(Collectors.toList());

        assertThat(rank.getSquares()).containsAll(expected);
    }

    @Test
    @DisplayName("7행은 정상 생성이 된다")
    void constructorTestSeven() {
        Rank rank = new Rank(7, 8);

        List<Square> expected = List.of(
                new ConcreteSquare(new Rook(), Camp.BLACK),
                new ConcreteSquare(new Knight(), Camp.BLACK),
                new ConcreteSquare(new Bishop(), Camp.BLACK),
                new ConcreteSquare(new Queen(), Camp.BLACK),
                new ConcreteSquare(new King(), Camp.BLACK),
                new ConcreteSquare(new Bishop(), Camp.BLACK),
                new ConcreteSquare(new Knight(), Camp.BLACK),
                new ConcreteSquare(new Rook(), Camp.BLACK)
        );

        assertThat(rank.getSquares()).containsAll(expected);
    }


}
