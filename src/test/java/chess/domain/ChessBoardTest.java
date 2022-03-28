package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.fixedmovablepiece.King;
import chess.domain.piece.fixedmovablepiece.Knight;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.piece.straightmovablepiece.Bishop;
import chess.domain.piece.straightmovablepiece.Queen;
import chess.domain.piece.straightmovablepiece.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void construct() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new WhitePawn()),
                Map.entry(Position.of("a2"), new BlackPawn())
        ));
        ChessBoard chessBoard = new ChessBoard(() -> pieces);
        assertThat(chessBoard.getPieces()).containsAllEntriesOf(pieces);
    }

    @Test
    @DisplayName("체스판을 생성할 때 빈 칸은 EmptyPiece를 삽입한다.")
    void constructEmptyPieces() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new WhitePawn()),
                Map.entry(Position.of("a2"), new BlackPawn())
        ));
        ChessBoard chessBoard = new ChessBoard(() -> pieces);
        assertThat(chessBoard.getPieces()).contains(Map.entry(Position.of("a3"), EmptyPiece.getInstance()));
    }

    @Test
    @DisplayName("위치가 들어왔을 때 해당 위치의 말이 어떤 말인지 확인한다.")
    void selectPiece() {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        ChessBoard chessBoard = new ChessBoard(piecesGenerator);
        Piece piece = chessBoard.selectPiece(Position.of("a1"));
        assertThat(piece).isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a2, true", "a3, false", "h7, true", "h8, false"})
    @DisplayName("해당 위치에 움직이지 않은 폰이 있는지 확인한다.")
    void isFirstMovePawn(String position, boolean expected) {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        ChessBoard chessBoard = new ChessBoard(piecesGenerator);
        assertThat(chessBoard.isFirstMovePawn(Position.of(position))).isEqualTo(expected);
    }

    @Nested
    @DisplayName("장애물 처리를 한 움직일 수 있는 포지션들을 반환한다.")
    class GenerateMovablePositionsWithBlock {

        @Test
        @DisplayName("킹일때")
        void king() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(Position.of("d4"), new King(Color.WHITE));
            testBoard.put(Position.of("d5"), new WhitePawn());
            testBoard.put(Position.of("e3"), new BlackPawn());

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(Position.of("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(Position.of("d4"), piece,
                    piece.getMovablePositions(Position.of("d4")));
            assertThat(positions).contains(
                    Position.of("e3"), Position.of("c5"), Position.of("e5"),
                    Position.of("c4"), Position.of("e4"), Position.of("c3"),
                    Position.of("d3"));
        }

        @Test
        @DisplayName("퀸일때")
        void queen() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(Position.of("d4"), new Queen(Color.WHITE));
            testBoard.put(Position.of("g4"), new WhitePawn());
            testBoard.put(Position.of("d2"), new BlackPawn());

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(Position.of("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(Position.of("d4"), piece,
                    piece.getMovablePositions(Position.of("d4")));
            assertThat(positions).contains(
                    Position.of("c4"), Position.of("b4"), Position.of("a4"),
                    Position.of("d5"), Position.of("d6"), Position.of("d7"), Position.of("d8"),
                    Position.of("e4"), Position.of("f4"),
                    Position.of("d3"), Position.of("d2"),
                    Position.of("e5"), Position.of("f6"), Position.of("g7"), Position.of("h8"),
                    Position.of("c3"), Position.of("b2"), Position.of("a1"),
                    Position.of("c5"), Position.of("b6"), Position.of("a7"),
                    Position.of("e3"), Position.of("f2"), Position.of("g1"));
        }

        @Test
        @DisplayName("룩일 때")
        void rook() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(Position.of("d4"), new Rook(Color.WHITE));
            testBoard.put(Position.of("g4"), new WhitePawn());
            testBoard.put(Position.of("d2"), new BlackPawn());

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(Position.of("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(Position.of("d4"), piece,
                    piece.getMovablePositions(Position.of("d4")));
            assertThat(positions).contains(
                    Position.of("c4"), Position.of("b4"), Position.of("a4"),
                    Position.of("d5"), Position.of("d6"), Position.of("d7"), Position.of("d8"),
                    Position.of("e4"), Position.of("f4"),
                    Position.of("d3"), Position.of("d2"));
        }

        @Test
        @DisplayName("비숍일때")
        void bishop() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(Position.of("d4"), new Bishop(Color.WHITE));
            testBoard.put(Position.of("g7"), new WhitePawn());
            testBoard.put(Position.of("f2"), new BlackPawn());

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(Position.of("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(Position.of("d4"), piece,
                    piece.getMovablePositions(Position.of("d4")));
            assertThat(positions).contains(
                    Position.of("e5"), Position.of("f6"),
                    Position.of("c3"), Position.of("b2"), Position.of("a1"),
                    Position.of("c5"), Position.of("b6"), Position.of("a7"),
                    Position.of("e3"), Position.of("f2"));
        }

        @Test
        @DisplayName("나이트일때")
        void knight() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(Position.of("d4"), new Knight(Color.WHITE));
            testBoard.put(Position.of("f3"), new WhitePawn());
            testBoard.put(Position.of("c2"), new BlackPawn());

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(Position.of("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(Position.of("d4"), piece,
                    piece.getMovablePositions(Position.of("d4")));
            assertThat(positions).contains(
                    Position.of("f5"), Position.of("e2"), Position.of("c2"),
                    Position.of("b3"), Position.of("b5"), Position.of("c6"),
                    Position.of("e6"));
        }

        @Test
        @DisplayName("폰일때")
        void pawn() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(Position.of("d4"), new WhitePawn());
            testBoard.put(Position.of("e5"), new BlackPawn()); // 갈 수 있음

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(Position.of("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(Position.of("d4"), piece,
                    piece.getMovablePositions(Position.of("d4")));
            assertThat(positions).contains(
                    Position.of("d5"), Position.of("e5"));
        }

        @Test
        @DisplayName("처음 움직이는 폰일때")
        void pawnFirstMove() {
            Map<Position, Piece> testBoard = new HashMap<>();
            Position pawnPosition = Position.of("d2");

            testBoard.put(pawnPosition, new WhitePawn());
            testBoard.put(Position.of("e3"), new BlackPawn());

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(pawnPosition);
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(pawnPosition, piece,
                    piece.getMovablePositions(pawnPosition));
            assertThat(positions).contains(
                    Position.of("d3"), Position.of("d4"), Position.of("e3"));
        }

        @Test
        @DisplayName("폰일 때 앞이 가로막혀있으면 앞으로 갈 수 없다")
        void pawnFirstMoveButSameTeamBlock() {
            Map<Position, Piece> testBoard = new HashMap<>();
            Position pawnPosition = Position.of("d2");

            testBoard.put(pawnPosition, new WhitePawn());
            testBoard.put(Position.of("d3"), new BlackPawn());
            testBoard.put(Position.of("e3"), new BlackPawn());

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(pawnPosition);
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(pawnPosition, piece,
                    piece.getMovablePositions(pawnPosition));
            assertThat(positions).containsExactly(Position.of("e3"));
        }
    }

    @Nested
    class Move {

        @Test
        @DisplayName("킹을 이동시킬 수 있다.")
        void king() {
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(Position.of("d4"), new King(Color.WHITE)),
                    Map.entry(Position.of("d3"), new WhitePawn()),
                    Map.entry(Position.of("d5"), new BlackPawn())
            ));
            ChessBoard chessBoard = new ChessBoard(() -> pieces);
            GameCommand gameCommand = new GameCommand("move", "d4", "d5");
            chessBoard.move(gameCommand);
            Piece fromPiece = chessBoard.selectPiece(gameCommand.getFromPosition());
            Piece toPiece = chessBoard.selectPiece(gameCommand.getToPosition());
            assertAll(
                    () -> assertThat(fromPiece.isSamePieceName(PieceName.EMPTY)).isTrue(),
                    () -> assertThat(toPiece.isSamePieceName(PieceName.KING)).isTrue()
            );
        }

        @Test
        @DisplayName("퀸을 이동시킬 수 있다.")
        void queen() {
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(Position.of("d4"), new Queen(Color.WHITE)),
                    Map.entry(Position.of("g4"), new WhitePawn()),
                    Map.entry(Position.of("d2"), new BlackPawn())
            ));
            ChessBoard chessBoard = new ChessBoard(() -> pieces);
            GameCommand gameCommand = new GameCommand("move", "d4", "d2");
            chessBoard.move(gameCommand);
            Piece fromPiece = chessBoard.selectPiece(gameCommand.getFromPosition());
            Piece toPiece = chessBoard.selectPiece(gameCommand.getToPosition());
            assertAll(
                    () -> assertThat(fromPiece.isSamePieceName(PieceName.EMPTY)).isTrue(),
                    () -> assertThat(toPiece.isSamePieceName(PieceName.QUEEN)).isTrue()
            );
        }

        @Test
        @DisplayName("룩을 이동시킬 수 있다.")
        void rook() {
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(Position.of("d4"), new Rook(Color.WHITE)),
                    Map.entry(Position.of("g4"), new WhitePawn()),
                    Map.entry(Position.of("d2"), new BlackPawn())
            ));
            ChessBoard chessBoard = new ChessBoard(() -> pieces);
            GameCommand gameCommand = new GameCommand("move", "d4", "d2");
            chessBoard.move(gameCommand);
            Piece fromPiece = chessBoard.selectPiece(gameCommand.getFromPosition());
            Piece toPiece = chessBoard.selectPiece(gameCommand.getToPosition());
            assertAll(
                    () -> assertThat(fromPiece.isSamePieceName(PieceName.EMPTY)).isTrue(),
                    () -> assertThat(toPiece.isSamePieceName(PieceName.ROOK)).isTrue()
            );
        }

        @Test
        @DisplayName("비숍을 이동시킬 수 있다.")
        void bishop() {
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(Position.of("d4"), new Bishop(Color.WHITE)),
                    Map.entry(Position.of("g7"), new WhitePawn()),
                    Map.entry(Position.of("f2"), new BlackPawn())
            ));
            ChessBoard chessBoard = new ChessBoard(() -> pieces);
            GameCommand gameCommand = new GameCommand("move", "d4", "f2");
            chessBoard.move(gameCommand);
            Piece fromPiece = chessBoard.selectPiece(gameCommand.getFromPosition());
            Piece toPiece = chessBoard.selectPiece(gameCommand.getToPosition());
            assertAll(
                    () -> assertThat(fromPiece.isSamePieceName(PieceName.EMPTY)).isTrue(),
                    () -> assertThat(toPiece.isSamePieceName(PieceName.BISHOP)).isTrue()
            );
        }

        @ParameterizedTest
        @CsvSource(value = {"b1,c3", "b8,c6"})
        @DisplayName("나이트를 이동시킬 수 있다.")
        void knight(String from, String to) {
            PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
            ChessBoard chessBoard = new ChessBoard(piecesGenerator);

            GameCommand gameCommand = new GameCommand("move", from, to);
            chessBoard.move(gameCommand);
            Piece fromPiece = chessBoard.selectPiece(gameCommand.getFromPosition());
            Piece toPiece = chessBoard.selectPiece(gameCommand.getToPosition());
            assertAll(
                    () -> assertThat(fromPiece.isSamePieceName(PieceName.EMPTY)).isTrue(),
                    () -> assertThat(toPiece.isSamePieceName(PieceName.KNIGHT)).isTrue()
            );
        }

        @Test
        @DisplayName("폰을 이동시킬 수 있다.")
        void pawn() {
            PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
            ChessBoard chessBoard = new ChessBoard(piecesGenerator);

            GameCommand gameCommand = new GameCommand("move", "b2", "b4");
            chessBoard.move(gameCommand);
            Piece fromPiece = chessBoard.selectPiece(gameCommand.getFromPosition());
            Piece toPiece = chessBoard.selectPiece(gameCommand.getToPosition());
            assertAll(
                    () -> assertThat(fromPiece.isSamePieceName(PieceName.EMPTY)).isTrue(),
                    () -> assertThat(toPiece.isSamePieceName(PieceName.PAWN)).isTrue()
            );
        }

        @Test
        @DisplayName("이동할 수 없는 곳으로 이동명령을 내렸을 때 예외가 발생한다.")
        void pawnCannotMoveThrowException() {
            PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
            ChessBoard chessBoard = new ChessBoard(piecesGenerator);

            GameCommand gameCommand = new GameCommand("move", "b2", "c3");
            assertThatThrownBy(() -> chessBoard.move(gameCommand))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 말은 입력한 위치로 이동할 수 없습니다.");
        }
    }

    @Test
    @DisplayName("한 컬럼의 흑팀 말들을 반환한다.")
    void getPiecesOnColumnByColor() {
        ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());
        Piece rook = chessBoard.selectPiece(Position.of("a8"));
        Piece pawn = chessBoard.selectPiece(Position.of("a7"));
        List<Piece> pieces = chessBoard.getPiecesOnColumn(Column.A, Color.BLACK);
        assertThat(pieces).containsExactly(pawn, rook);
    }

    @Test
    @DisplayName("여러 컬럼의 흑팀 말들을 반환한다.")
    void getPiecesOnColumnsByColor() {

        Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new Queen(Color.WHITE)),
                Map.entry(Position.of("b3"), new WhitePawn()),
                Map.entry(Position.of("c4"), new WhitePawn()),
                Map.entry(Position.of("a4"), new BlackPawn()),
                Map.entry(Position.of("a7"), new BlackPawn()),
                Map.entry(Position.of("c5"), new BlackPawn()),
                Map.entry(Position.of("b8"), new BlackPawn())

        ));
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        Piece a = chessBoard.selectPiece(Position.of("a4"));
        Piece a2 = chessBoard.selectPiece(Position.of("a7"));
        Piece b = chessBoard.selectPiece(Position.of("b8"));
        Piece c = chessBoard.selectPiece(Position.of("c5"));

        List<List<Piece>> pieces = chessBoard.getPiecesOnColumns(Color.BLACK);
        assertThat(pieces).contains(
                List.of(a, a2),
                List.of(b),
                List.of(c)
        );
    }

    @Test
    @DisplayName("킹이 1개일 때, 게임은 끝난다.")
    void isEndTrue() {
        Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new King(Color.WHITE)),
                Map.entry(Position.of("b3"), new WhitePawn()),
                Map.entry(Position.of("c4"), new WhitePawn()),
                Map.entry(Position.of("a4"), new BlackPawn()),
                Map.entry(Position.of("a7"), new BlackPawn()),
                Map.entry(Position.of("c5"), new BlackPawn()),
                Map.entry(Position.of("b8"), new BlackPawn())
        ));
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        assertThat(chessBoard.isEnd()).isTrue();
    }

    @Test
    @DisplayName("킹이 2개일 때, 게임은 끝나지 않는다.")
    void isEndFalse() {
        Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new King(Color.WHITE)),
                Map.entry(Position.of("b3"), new WhitePawn()),
                Map.entry(Position.of("c4"), new WhitePawn()),
                Map.entry(Position.of("a4"), new King(Color.BLACK)),
                Map.entry(Position.of("a7"), new BlackPawn()),
                Map.entry(Position.of("c5"), new BlackPawn()),
                Map.entry(Position.of("b8"), new BlackPawn())
        ));
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        assertThat(chessBoard.isEnd()).isFalse();
    }
}
