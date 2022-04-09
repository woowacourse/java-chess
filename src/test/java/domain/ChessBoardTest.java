package domain;

import static chess.domain.piece.property.Team.*;
import static domain.PositionFixtures.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.dto.GameStatus;
import chess.domain.piece.unit.Bishop;
import chess.domain.piece.unit.Pawn;
import chess.domain.piece.unit.Piece;
import chess.domain.piece.unit.Rook;
import chess.domain.position.Position;
import chess.domain.classification.Result;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardTest {

    @Test
    @DisplayName("Source 위치에는 기물이 있어야한다.")
    void haveToSourceNotNull() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        Position source = B3;

        assertThatThrownBy(() -> chessBoard.move(source, B4)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자신의 턴에 자신의 말만 이동할 수 있다.(성공)")
    void moveOwnTurnOwnPieceSuccess() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        assertDoesNotThrow(() -> chessBoard.move(B2, B3));
    }

    @Test
    @DisplayName("자신의 턴에 자신의 말만 이동할 수 있다.(실패)")
    void moveOwnTurnOwnPieceFail() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        assertThatThrownBy(() -> chessBoard.move(B7, B6)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("move 이후에 Turn 이 변경된다.")
    void changeTurnAfterMove() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        chessBoard.move(B2, B3);

        assertThat(chessBoard.getCurrentTurn()).isEqualTo(BLACK);
    }

    @ParameterizedTest
    @MethodSource("availableTargets")
    @DisplayName("target은 source와 다른 팀이거나 null 이다.")
    void checkTargetSuccess(Piece piece) {
        Position source = A1;
        Position target = A8;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Rook(WHITE));
        customBoardGenerator.add(target, piece);
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    private static Stream<Piece> availableTargets() {
        return Stream.of(null, new Rook(BLACK));
    }

    @Test
    @DisplayName("source는 target이 같은팀일 때 공격할 수 없다.")
    void checkTargetFail() {
        Position source = A1;
        Position target = A8;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Rook(WHITE));
        customBoardGenerator.add(target, new Rook(WHITE));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertThatThrownBy(() -> chessBoard.move(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("source 가 target 까지 이동하는 과정에는 모두 비어있어야(null) 이동 가능하다(Knight 는 제외한다).")
    void checkEverySpaceInCrossIsNull() {
        Position source = A1;
        Position waypoint = C3;
        Position target = E5;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Bishop(WHITE));
        customBoardGenerator.add(waypoint, new Bishop(WHITE));
        customBoardGenerator.add(target, new Bishop(BLACK));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertThatThrownBy(() -> chessBoard.move(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("availableKnights")
    @DisplayName("Knight 는 이동경로에 말이 존재해도 넘어갈 수 있다.")
    void checkEverySpaceInCrossSuccess(Position target) {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        assertDoesNotThrow(() -> chessBoard.move(B1, target));
    }

    private static Stream<Position> availableKnights() {
        return Stream.of(A3, C3);
    }

    @ParameterizedTest
    @MethodSource("availableMovePawns")
    @DisplayName("Pawn은 상대 말이 없을 때 이동할 수 있다.")
    void checkPawnMoveNoOpponent(Position target) {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        assertDoesNotThrow(() -> chessBoard.move(B2, target));
    }

    private static Stream<Position> availableMovePawns() {
        return Stream.of(B3, B4);
    }

    @Test
    @DisplayName("Pawn 은 이동 위치에 상대방의 말이 있다면 이동할 수 없다. (앞으로 한칸 이동한 경우)")
    void checkPawnImmovableByOpponent() {
        Position source = B2;
        Position target = B3;
        Position waypoint = B3;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Pawn(WHITE));
        customBoardGenerator.add(waypoint, new Pawn(BLACK));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertThatThrownBy(() -> chessBoard.move(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("immovableTwoSpace")
    @DisplayName("Pawn 은 이동 위치에 상대방의 말이 있다면 이동할 수 없다. (앞으로 두 칸 이동한 경우)")
    void checkPawnTwoSpaceImmovableByOpponent(Position waypoint) {
        Position source = B2;
        Position target = B4;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Pawn(WHITE));
        customBoardGenerator.add(waypoint, new Pawn(BLACK));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertThatThrownBy(() -> chessBoard.move(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Position> immovableTwoSpace() {
        return Stream.of(B3, B4);
    }

    @ParameterizedTest
    @MethodSource("attackPawnPositions")
    @DisplayName("Pawn 의 공격은 대각선 한칸이며, target이 항상 존재해야 한다.")
    void checkPawnAttackSucess(Position target) {
        Position source = B2;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Pawn(WHITE));
        customBoardGenerator.add(target, new Pawn(BLACK));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    @ParameterizedTest
    @MethodSource("attackPawnPositions")
    @DisplayName("Pawn 의 공격은 대각선 한칸이며, target 이 없다면 실패한다.")
    void checkPawnAttackFail(Position target) {
        Position source = B2;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Pawn(WHITE));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertThatThrownBy(() -> chessBoard.move(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Position> attackPawnPositions() {
        return Stream.of(A3, C3);
    }

    @ParameterizedTest
    @MethodSource("attackPawnImpossiblePositions")
    @DisplayName("Pawn 의 공격은 대각선 한칸이다. (실패테스트)")
    void checkPawnAttackFailByUnrelatedPositions(Position target) {
        Position source = B2;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Pawn(WHITE));
        customBoardGenerator.add(target, new Pawn(BLACK));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertThatThrownBy(() -> chessBoard.move(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Position> attackPawnImpossiblePositions() {
        return Stream.of(A4, B3, B4, C4);
    }

    @ParameterizedTest
    @MethodSource("attackRookPossiblePositions")
    @DisplayName("Rook은 직선으로 공격할 수 있다.")
    void checkRookAttack(Position target) {
        Position source = D4;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Rook(WHITE));
        customBoardGenerator.add(target, new Rook(BLACK));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    private static Stream<Position> attackRookPossiblePositions() {
        return Stream.of(A4, D1, D8, E4);
    }

    @ParameterizedTest
    @MethodSource("moveRookPossiblePositions")
    @DisplayName("Rook 은 직선으로 이동할 수 있다.")
    void checkRookMove(Position target) {
        Position source = D4;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Rook(WHITE));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertDoesNotThrow(() -> chessBoard.move(source, target));
    }

    private static Stream<Position> moveRookPossiblePositions() {
        return Stream.of(A4, D1, D8, H4);
    }

    @ParameterizedTest
    @MethodSource("rookImpossiblePositions")
    @DisplayName("Rook은 경로에 기물이 있다면 이동할 수 없다.")
    void checkRookUnavailableMove(Position wayPoint, Position target) {
        Position source = D4;

        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(source, new Rook(WHITE));
        customBoardGenerator.add(wayPoint, new Pawn(WHITE));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);

        assertThatThrownBy(() -> chessBoard.move(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> rookImpossiblePositions() {
        return Stream.of(
                Arguments.of(B4, A4),
                Arguments.of(D2, D1),
                Arguments.of(D5, D8),
                Arguments.of(G4, H4)
        );
    }

    @Test
    @DisplayName("처음 각 팀의 점수는 38점이다.")
    void checkCurrentTeamScore() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        GameStatus gameStatus = new GameStatus(chessBoard);

        assertAll(
                () -> assertEquals(gameStatus.calculateTeamScore(chessBoard.getBoard(), WHITE), 38),
                () -> assertEquals(gameStatus.calculateTeamScore(chessBoard.getBoard(), BLACK), 38)
        );
    }

    @Test
    @DisplayName("폰이 같은 세로줄에 있는 경우 1점이 아닌 0.5점을 준다.")
    void checkDuplicatePawnScore() {
        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(A1, new Pawn(WHITE));
        customBoardGenerator.add(A2, new Pawn(WHITE));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);
        GameStatus gameStatus = new GameStatus(chessBoard);

        assertThat(gameStatus.calculateTeamScore(chessBoard.getBoard(), WHITE)).isEqualTo(1.0);
    }

    @Test
    @DisplayName("점수를 비교하여 승, 패, 무승부를 알 수 있다. (승)")
    void checkScoreWhoWinner() {
        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(A1, new Pawn(WHITE));
        customBoardGenerator.add(B2, new Pawn(WHITE));
        customBoardGenerator.add(A7, new Pawn(BLACK));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);
        GameStatus gameStatus = new GameStatus(chessBoard);

        assertThat(gameStatus.calculateWinner(chessBoard)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("점수를 비교하여 승, 패, 무승부를 알 수 있다. (패)")
    void checkScoreWhoLoser() {
        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(A2, new Pawn(WHITE));
        customBoardGenerator.add(A7, new Pawn(BLACK));
        customBoardGenerator.add(B7, new Pawn(BLACK));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);
        GameStatus gameStatus = new GameStatus(chessBoard);

        assertThat(gameStatus.calculateWinner(chessBoard)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("점수를 비교하여 승, 패, 무승부를 알 수 있다. (무승부)")
    void checkScoreDraw() {
        CustomBoardGenerator customBoardGenerator = new CustomBoardGenerator();
        customBoardGenerator.add(A1, new Pawn(WHITE));
        customBoardGenerator.add(A7, new Pawn(BLACK));
        ChessBoard chessBoard = new ChessBoard(customBoardGenerator);
        GameStatus gameStatus = new GameStatus(chessBoard);

        assertThat(gameStatus.calculateWinner(chessBoard)).isEqualTo(Result.DRAW);
    }
}
