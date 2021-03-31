package chess.view;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PieceDto;
import chess.controller.dto.PositionDto;
import chess.domain.Position;
import chess.domain.TeamColor;
import chess.domain.game.ChessResult;

import java.util.stream.Collectors;


public class OutputView {

    private static final String SCORE_FORM = "%s팀 : %.1f" + System.lineSeparator();
    private static final String WINNER_FORM = "%s팀 승리!";

    private OutputView() {
    }

    public static void printByFormat(String format, Object... message) {
        System.out.printf(format, message);
    }

    public static void printBoard(BoardDto boardDto) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = boardDto.getBoardSize() - 1; y >= 0; y--) {
            appendPieceNames(boardDto, stringBuilder, y);
            stringBuilder.append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    private static void appendPieceNames(BoardDto boardDto, StringBuilder stringBuilder, int column) {
        for (int x = 0; x < boardDto.getBoardSize(); x++) {
            PositionDto currentPosition = new PositionDto(Position.of(x, column));
            appendEmptyPosition(boardDto, stringBuilder, currentPosition);
            stringBuilder.append(
                    boardDto.getPieces()
                            .stream()
                            .filter(piece -> piece.getCurrentPosition().equals(currentPosition.getPosition()))
                            .map(PieceDto::getName)
                            .collect(Collectors.joining())
            );
        }
    }

    private static void appendEmptyPosition(BoardDto boardDto, StringBuilder stringBuilder, PositionDto currentPosition) {
        if (boardDto.getPieces()
                .stream()
                .noneMatch(piece -> piece.getCurrentPosition().equals(currentPosition.getPosition()))) {
            stringBuilder.append(".");
        }
    }

    public static void printStartGame() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printCheck() {
        System.out.println("체크!");
    }

    public static void printResult(ChessResult chessResult) {
        printByFormat(SCORE_FORM, TeamColor.WHITE, chessResult.getWhiteTeamScore());
        printByFormat(SCORE_FORM, TeamColor.BLACK, chessResult.getBlackTeamScore());
        TeamColor winner = TeamColor.BLACK;
        if (chessResult.getWhiteTeamScore() > chessResult.getBlackTeamScore()) {
            winner = TeamColor.WHITE;
        }
        printWinner(winner);
    }

    public static void printWinner(TeamColor teamColor) {
        printByFormat(WINNER_FORM, teamColor);
    }
}
