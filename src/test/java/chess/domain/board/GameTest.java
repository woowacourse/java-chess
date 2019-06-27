package chess.domain.board;

import chess.domain.path.PathFactory;
import chess.domain.piece.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    @Test
    void 실제_보드_초기화() {
        Player whitePlayer = new DefaultPlayer(PlayerFactory.init(new WhitePieceInit()));
        Player blackPlayer = new DefaultPlayer(PlayerFactory.init(new BlackPieceInit()));

        Game game = new Game(whitePlayer, blackPlayer);

        assertThat(game.whitePiecesCount()).isEqualTo(16);
        assertThat(game.blackPiecesCount()).isEqualTo(16);
    }

    @Test
    void 해당_위치_말_확인() {
        Piece rook = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(1), new Position(8)), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(1), new Position(2)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        assertThat(game.getPiece(new Square(new Position(1), new Position(8)))).isEqualTo(rook);
    }

    @Test
    void 해당_위치_말_확인_예외() {
        Piece rook = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(1), new Position(8)), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(1), new Position(2)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        assertThrows(IllegalArgumentException.class, () -> {
            game.getPiece(new Square(new Position(1), new Position(7)));
        });
    }

    @Test
    void 룩_움직임_경우의수() {
        Piece rook = Rook.blackCreate();
        Piece rook2 = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(2), new Position(7)), rook);
        black.put(new Square(new Position(6), new Position(7)), rook2);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(2), new Position(2)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        Vectors moveList = game.movableArea(new Square(new Position(2), new Position(7)));
        List<Vector> expectedVectorList = Arrays.asList(
                new Vector(new Square(new Position(1), new Position(7)), Direction.LEFT),
                new Vector(new Square(new Position(3), new Position(7)), Direction.RIGHT),
                new Vector(new Square(new Position(4), new Position(7)), Direction.RIGHT),
                new Vector(new Square(new Position(5), new Position(7)), Direction.RIGHT),
                new Vector(new Square(new Position(2), new Position(8)), Direction.UP),
                new Vector(new Square(new Position(2), new Position(6)), Direction.DOWN),
                new Vector(new Square(new Position(2), new Position(5)), Direction.DOWN),
                new Vector(new Square(new Position(2), new Position(4)), Direction.DOWN),
                new Vector(new Square(new Position(2), new Position(3)), Direction.DOWN),
                new Vector(new Square(new Position(2), new Position(2)), Direction.DOWN));

        Vectors expectedVector = new Vectors(new HashSet<>(expectedVectorList));
        assertThat(moveList).isEqualTo(expectedVector);
    }

    @Test
    void 나이트_움직임_경우의수() {
        Piece knight = Knight.blackCreate();
        Piece rook = Rook.blackCreate();
        Piece rook2 = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(2), new Position(8)), knight);
        black.put(new Square(new Position(1), new Position(6)), rook);
        // path
        black.put(new Square(new Position(2), new Position(7)), rook2);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(3), new Position(6)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        Vectors moveList = game.movableArea(new Square(new Position(2), new Position(8)));
        List<Vector> expectedVectorList = Arrays.asList(
                new Vector(new Square(new Position(3), new Position(6)), Direction.NONE),
                new Vector(new Square(new Position(4), new Position(7)), Direction.NONE));

        Vectors expectedVector = new Vectors(new HashSet<>(expectedVectorList));
        assertThat(moveList).isEqualTo(expectedVector);
    }

    @Test
    void 비숍_움직임_경우의수() {
        Piece bishop = Bishop.blackCreate();
        Piece rook2 = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(6), new Position(6)), bishop);
        black.put(new Square(new Position(7), new Position(7)), rook2);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(4), new Position(4)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        Vectors moveList = game.movableArea(new Square(new Position(6), new Position(6)));
        Vectors expected = new Vectors(new HashSet<>());

        expected.add(new Vector(new Square(new Position(7), new Position(5)), Direction.DOWN_RIGHT));
        expected.add(new Vector(new Square(new Position(8), new Position(4)), Direction.DOWN_RIGHT));

        expected.add(new Vector(new Square(new Position(5), new Position(5)), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new Position(4), new Position(4)), Direction.DOWN_LEFT));

        expected.add(new Vector(new Square(new Position(5), new Position(7)), Direction.UP_LEFT));
        expected.add(new Vector(new Square(new Position(4), new Position(8)), Direction.UP_LEFT));

        assertThat(moveList).isEqualTo(expected);
    }

    @Test
    void 퀸_움직임_경우의수() {
        Piece queen = Queen.blackCreate();
        Piece rook2 = Rook.blackCreate();
        Piece rook3 = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(6), new Position(6)), queen);
        black.put(new Square(new Position(7), new Position(7)), rook2);
        black.put(new Square(new Position(6), new Position(2)), rook3);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Piece pawn2 = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(4), new Position(4)), pawn);
        white.put(new Square(new Position(2), new Position(6)), pawn2);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        Vectors moveList = game.movableArea(new Square(new Position(6), new Position(6)));
        Vectors expected = new Vectors(new HashSet<>());

        expected.add(new Vector(new Square(new Position(7), new Position(5)), Direction.DOWN_RIGHT));
        expected.add(new Vector(new Square(new Position(8), new Position(4)), Direction.DOWN_RIGHT));

        expected.add(new Vector(new Square(new Position(5), new Position(5)), Direction.DOWN_LEFT));
        expected.add(new Vector(new Square(new Position(4), new Position(4)), Direction.DOWN_LEFT));

        expected.add(new Vector(new Square(new Position(5), new Position(7)), Direction.UP_LEFT));
        expected.add(new Vector(new Square(new Position(4), new Position(8)), Direction.UP_LEFT));

        expected.add(new Vector(new Square(new Position(5), new Position(7)), Direction.UP_LEFT));
        expected.add(new Vector(new Square(new Position(4), new Position(8)), Direction.UP_LEFT));

        expected.add(new Vector(new Square(new Position(6), new Position(7)), Direction.UP));
        expected.add(new Vector(new Square(new Position(6), new Position(8)), Direction.UP));

        expected.add(new Vector(new Square(new Position(7), new Position(6)), Direction.RIGHT));
        expected.add(new Vector(new Square(new Position(8), new Position(6)), Direction.RIGHT));

        expected.add(new Vector(new Square(new Position(6), new Position(5)), Direction.DOWN));
        expected.add(new Vector(new Square(new Position(6), new Position(4)), Direction.DOWN));
        expected.add(new Vector(new Square(new Position(6), new Position(3)), Direction.DOWN));

        expected.add(new Vector(new Square(new Position(2), new Position(6)), Direction.LEFT));
        expected.add(new Vector(new Square(new Position(3), new Position(6)), Direction.LEFT));
        expected.add(new Vector(new Square(new Position(4), new Position(6)), Direction.LEFT));
        expected.add(new Vector(new Square(new Position(5), new Position(6)), Direction.LEFT));

        assertThat(moveList).isEqualTo(expected);
    }

    @Test
    void 킹_움직임_경우의수() {
        Piece king = King.blackCreate();
        Piece rook2 = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(2), new Position(2)), king);
        black.put(new Square(new Position(2), new Position(1)), rook2);
        Player blackPlayer = new DefaultPlayer(black);

        Piece king2 = King.whiteCreate();
        Piece pawn2 = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(4), new Position(2)), king2);
        white.put(new Square(new Position(2), new Position(3)), pawn2);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        Vectors moveList = game.movableArea(new Square(new Position(2), new Position(2)));
        Vectors expected = new Vectors(new HashSet<>());

        expected.add(new Vector(new Square(new Position(1), new Position(1)), Direction.DOWN_LEFT));

        expected.add(new Vector(new Square(new Position(1), new Position(3)), Direction.UP_LEFT));

        expected.add(new Vector(new Square(new Position(2), new Position(3)), Direction.UP));

        expected.add(new Vector(new Square(new Position(1), new Position(2)), Direction.LEFT));

        assertThat(moveList).isEqualTo(expected);
    }

    @Test
    void 룩_움직임() {
        Piece rook = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(2), new Position(7)), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(2), new Position(2)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        game.move(new Square(new Position(2), new Position(7)),
                new Square(new Position(2), new Position(3)));

        Piece expected = game.getPiece(new Square(new Position(2), new Position(3)));
        assertThat(rook).isEqualTo(expected);
    }

    @Test
    void 보드_룩이_폰을잡는다() {
        Piece rook = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(1), new Position(8)), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(1), new Position(2)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);
        game.move(new Square(new Position(1), new Position(8)),
                new Square(new Position(1), new Position(2)));

        assertThat(game.whitePiecesCount()).isEqualTo(0);
    }

    @Test
    void 보드_룩이_폰을잡는다_유효하지않은_타겟() {
        Piece rook = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(1), new Position(8)), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(1), new Position(2)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        assertThrows(RuntimeException.class, () -> game.move(new Square(new Position(1), new Position(8)),
                new Square(new Position(2), new Position(2))));
    }

    @Test
    void 점수계산() {
        Piece king = King.blackCreate();
        Piece rook = Rook.blackCreate();
        Piece pawn = Pawn.blackCreate();
        Piece knight = Knight.blackCreate();

        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(1), new Position(8)), king);
        black.put(new Square(new Position(1), new Position(7)), rook);
        black.put(new Square(new Position(1), new Position(6)), pawn);
        black.put(new Square(new Position(1), new Position(5)), knight);

        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn2 = Pawn.whiteCreate();
        Piece king2 = King.whiteCreate();
        Piece queen = Queen.whiteCreate();
        Piece bishop = Bishop.whiteCreate();

        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(1), new Position(2)), king2);
        white.put(new Square(new Position(2), new Position(3)), pawn2);
        white.put(new Square(new Position(3), new Position(4)), queen);
        white.put(new Square(new Position(4), new Position(5)), bishop);

        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        assertThat(game.score().getBlackScore()).isEqualTo(8.5);
        assertThat(game.score().getWhiteScore()).isEqualTo(13);
    }

    @Test
    void 폰_공격() {
        Piece rook = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(2), new Position(4)), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(1), new Position(2)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        game.move(new Square(new Position(2), new Position(4)),
                new Square(new Position(2), new Position(3)));

        boolean isValid = game.move(new Square(new Position(1), new Position(2)),
                new Square(new Position(2), new Position(3)));

        assertTrue(isValid);
    }

    @Test
    void 폰_공격_불가() {
        Piece rook = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(2), new Position(5)), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(1), new Position(2)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        game.move(new Square(new Position(2), new Position(5)),
                new Square(new Position(2), new Position(4)));

        assertThrows(RuntimeException.class, () -> game.move(new Square(new Position(1), new Position(2)),
                new Square(new Position(2), new Position(3))));
    }

    @Test
    void 폰_이동_불가() {
        Piece rook = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(1), new Position(5)), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece pawn = Pawn.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(1), new Position(2)), pawn);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        game.move(new Square(new Position(1), new Position(5)),
                new Square(new Position(1), new Position(3)));

        assertThrows(RuntimeException.class, () -> game.move(new Square(new Position(1), new Position(2)),
                new Square(new Position(1), new Position(3))));
    }

    @Test
    void 킹_사망() {
        Piece rook = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(2), new Position(4)), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece king = King.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(2), new Position(2)), king);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);
        boolean isContinue = game.move(new Square(new Position(2), new Position(4)),
                new Square(new Position(2), new Position(2)));

        assertFalse(isContinue);
    }

    @Test
    void 킹_사망하지_않음() {
        Piece rook = Rook.blackCreate();
        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(2), new Position(4)), rook);
        Player blackPlayer = new DefaultPlayer(black);

        Piece king = King.whiteCreate();
        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(2), new Position(2)), king);
        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);
        boolean isContinue = game.move(new Square(new Position(2), new Position(4)),
                new Square(new Position(2), new Position(3)));

        assertTrue(isContinue);
    }

    @Test
    void 폰_세로점수확인() {
        Piece pawn1 = Pawn.blackCreate();
        Piece pawn2 = Pawn.blackCreate();
        Piece pawn3 = Pawn.blackCreate();
        Piece pawn4 = Pawn.blackCreate();

        Map<Square, Piece> black = new HashMap<>();
        black.put(new Square(new Position(1), new Position(8)), pawn1);
        black.put(new Square(new Position(1), new Position(7)), pawn2);
        black.put(new Square(new Position(1), new Position(6)), pawn3);
        black.put(new Square(new Position(2), new Position(5)), pawn4);

        Player blackPlayer = new DefaultPlayer(black);

        Piece wPawn1 = Pawn.whiteCreate();
        Piece wPawn2 = Pawn.whiteCreate();
        Piece wPawn3 = Pawn.whiteCreate();
        Piece wPawn4 = Pawn.whiteCreate();

        Map<Square, Piece> white = new HashMap<>();
        white.put(new Square(new Position(1), new Position(2)), wPawn1);
        white.put(new Square(new Position(2), new Position(3)), wPawn2);
        white.put(new Square(new Position(3), new Position(4)), wPawn3);
        white.put(new Square(new Position(4), new Position(5)), wPawn4);

        Player whitePlayer = new DefaultPlayer(white);

        Game game = new Game(whitePlayer, blackPlayer);

        assertThat(game.score().getBlackScore()).isEqualTo(2.5);
        assertThat(game.score().getWhiteScore()).isEqualTo(4);
    }

    // TODO :: 체크 확인
    @Test
    void 체크_확인() {

    }

    // TODO :: 체크메이트 확인
    @Test
    void 체크메이트_확인() {

    }
}
