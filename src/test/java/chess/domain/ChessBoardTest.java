package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.NormalPiecesGenerator;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import chess.domain.piece.PiecesGenerator;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
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
                Map.entry(new Position("a1"), new Pawn(Color.WHITE)),
                Map.entry(new Position("a2"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(() -> pieces);
        assertThat(chessBoard.getPieces()).containsAllEntriesOf(pieces);
    }

    @Test
    @DisplayName("체스판을 생성할 때 빈 칸은 EmptyPiece를 삽입한다.")
    void constructEmptyPieces() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(new Position("a1"), new Pawn(Color.WHITE)),
                Map.entry(new Position("a2"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(() -> pieces);
        assertThat(chessBoard.getPieces()).contains(Map.entry(new Position("a3"), EmptyPiece.getInstance()));
    }

    @Test
    @DisplayName("위치가 들어왔을 때 해당 위치의 말이 어떤 말인지 확인한다.")
    void selectPiece() {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        ChessBoard chessBoard = new ChessBoard(piecesGenerator);
        Piece piece = chessBoard.selectPiece(new Position("a1"));
        assertThat(piece).isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a2, true", "a3, false", "h7, true", "h8, false"})
    @DisplayName("해당 위치에 움직이지 않은 폰이 있는지 확인한다.")
    void isFirstMovePawn(String position, boolean expected) {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        ChessBoard chessBoard = new ChessBoard(piecesGenerator);
        assertThat(chessBoard.isFirstMovePawn(new Position(position))).isEqualTo(expected);
    }

    @Nested
    @DisplayName("장애물 처리를 한 움직일 수 있는 포지션들을 반환한다.")
    class GenerateMovablePositionsWithBlock {

        @Test
        @DisplayName("킹일때")
        void king() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(new Position("d4"), new King(Color.WHITE));
            testBoard.put(new Position("d5"), new Pawn(Color.WHITE));
            testBoard.put(new Position("e3"), new Pawn(Color.BLACK));

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(new Position("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(new Position("d4"), piece,
                    piece.getMovablePositions(new Position("d4")));
            assertThat(positions).contains(
                    new Position("e3"), new Position("c5"), new Position("e5"),
                    new Position("c4"), new Position("e4"), new Position("c3"),
                    new Position("d3"));
        }

        @Test
        @DisplayName("퀸일때")
        void queen() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(new Position("d4"), new Queen(Color.WHITE));
            testBoard.put(new Position("g4"), new Pawn(Color.WHITE));
            testBoard.put(new Position("d2"), new Pawn(Color.BLACK));

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(new Position("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(new Position("d4"), piece,
                    piece.getMovablePositions(new Position("d4")));
            assertThat(positions).contains(
                    new Position("c4"), new Position("b4"), new Position("a4"),
                    new Position("d5"), new Position("d6"), new Position("d7"), new Position("d8"),
                    new Position("e4"), new Position("f4"),
                    new Position("d3"), new Position("d2"),
                    new Position("e5"), new Position("f6"), new Position("g7"), new Position("h8"),
                    new Position("c3"), new Position("b2"), new Position("a1"),
                    new Position("c5"), new Position("b6"), new Position("a7"),
                    new Position("e3"), new Position("f2"), new Position("g1"));
        }

        @Test
        @DisplayName("룩일 때")
        void rook() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(new Position("d4"), new Rook(Color.WHITE));
            testBoard.put(new Position("g4"), new Pawn(Color.WHITE));
            testBoard.put(new Position("d2"), new Pawn(Color.BLACK));

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(new Position("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(new Position("d4"), piece,
                    piece.getMovablePositions(new Position("d4")));
            assertThat(positions).contains(
                    new Position("c4"), new Position("b4"), new Position("a4"),
                    new Position("d5"), new Position("d6"), new Position("d7"), new Position("d8"),
                    new Position("e4"), new Position("f4"),
                    new Position("d3"), new Position("d2"));
        }

        @Test
        @DisplayName("비숍일때")
        void bishop() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(new Position("d4"), new Bishop(Color.WHITE));
            testBoard.put(new Position("g7"), new Pawn(Color.WHITE));
            testBoard.put(new Position("f2"), new Pawn(Color.BLACK));

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(new Position("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(new Position("d4"), piece,
                    piece.getMovablePositions(new Position("d4")));
            assertThat(positions).contains(
                    new Position("e5"), new Position("f6"),
                    new Position("c3"), new Position("b2"), new Position("a1"),
                    new Position("c5"), new Position("b6"), new Position("a7"),
                    new Position("e3"), new Position("f2"));
        }

        @Test
        @DisplayName("나이트일때")
        void knight() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(new Position("d4"), new Knight(Color.WHITE));
            testBoard.put(new Position("f3"), new Pawn(Color.WHITE));
            testBoard.put(new Position("c2"), new Pawn(Color.BLACK));

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(new Position("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(new Position("d4"), piece,
                    piece.getMovablePositions(new Position("d4")));
            assertThat(positions).contains(
                    new Position("f5"), new Position("e2"), new Position("c2"),
                    new Position("b3"), new Position("b5"), new Position("c6"),
                    new Position("e6"));
        }

        @Test
        @DisplayName("폰일때")
        void pawn() {
            Map<Position, Piece> testBoard = new HashMap<>();
            testBoard.put(new Position("d4"), new Pawn(Color.WHITE));
            testBoard.put(new Position("e5"), new Pawn(Color.BLACK)); // 갈 수 있음

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(new Position("d4"));
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(new Position("d4"), piece,
                    piece.getMovablePositions(new Position("d4")));
            assertThat(positions).contains(
                    new Position("d5"), new Position("e5"));
        }

        @Test
        @DisplayName("처음 움직이는 폰일때")
        void pawnFirstMove() {
            Map<Position, Piece> testBoard = new HashMap<>();
            Position pawnPosition = new Position("d2");

            testBoard.put(pawnPosition, new Pawn(Color.WHITE));
            testBoard.put(new Position("e3"), new Pawn(Color.BLACK));

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(pawnPosition);
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(pawnPosition, piece,
                    piece.getMovablePositions(pawnPosition));
            assertThat(positions).contains(
                    new Position("d3"), new Position("d4"), new Position("e3"));
        }

        @Test
        @DisplayName("폰일 때 앞이 가로막혀있으면 앞으로 갈 수 없다")
        void pawnFirstMoveButSameTeamBlock() {
            Map<Position, Piece> testBoard = new HashMap<>();
            Position pawnPosition = new Position("d2");

            testBoard.put(pawnPosition, new Pawn(Color.WHITE));
            testBoard.put(new Position("d3"), new Pawn(Color.BLACK));
            testBoard.put(new Position("e3"), new Pawn(Color.BLACK));

            ChessBoard chessBoard = new ChessBoard(() -> testBoard);
            Piece piece = chessBoard.selectPiece(pawnPosition);
            List<Position> positions = chessBoard.generateMovablePositionsWithBlock(pawnPosition, piece,
                    piece.getMovablePositions(pawnPosition));
            assertThat(positions).containsExactly(new Position("e3"));
        }
    }

    @Nested
    class Move {

        @Test
        @DisplayName("킹을 이동시킬 수 있다.")
        void king() {
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(new Position("d4"), new King(Color.WHITE)),
                    Map.entry(new Position("d3"), new Pawn(Color.WHITE)),
                    Map.entry(new Position("d5"), new Pawn(Color.BLACK))
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
                    Map.entry(new Position("d4"), new Queen(Color.WHITE)),
                    Map.entry(new Position("g4"), new Pawn(Color.WHITE)),
                    Map.entry(new Position("d2"), new Pawn(Color.BLACK))
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
                    Map.entry(new Position("d4"), new Rook(Color.WHITE)),
                    Map.entry(new Position("g4"), new Pawn(Color.WHITE)),
                    Map.entry(new Position("d2"), new Pawn(Color.BLACK))
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
                    Map.entry(new Position("d4"), new Bishop(Color.WHITE)),
                    Map.entry(new Position("g7"), new Pawn(Color.WHITE)),
                    Map.entry(new Position("f2"), new Pawn(Color.BLACK))
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
}
