package chess.view;

import static chess.domain.piece.Role.BISHOP;
import static chess.domain.piece.Role.INITIAL_PAWN;
import static chess.domain.piece.Role.KING;
import static chess.domain.piece.Role.KNIGHT;
import static chess.domain.piece.Role.PAWN;
import static chess.domain.piece.Role.QUEEN;
import static chess.domain.piece.Role.ROOK;

import chess.domain.piece.Piece;
import chess.domain.square.Color;
import chess.domain.square.Team;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 게임 점수 : status");
        System.out.println("> 게임 초기화 : clear");
    }

    public void printBoard(final List<List<Piece>> pieces) {
        Collections.reverse(pieces);
        for (List<Piece> rank : pieces) {
            for (Piece piece : rank) {
                System.out.print(convertPiece(piece));
            }
            System.out.println();
        }
    }

    private String convertPiece(final Piece piece) {
        if (piece.hasSameRole(PAWN) || piece.hasSameRole(INITIAL_PAWN)) {
            return convertSide(piece, "p");
        }
        if (piece.hasSameRole(ROOK)) {
            return convertSide(piece, "r");
        }
        if (piece.hasSameRole(KNIGHT)) {
            return convertSide(piece, "n");
        }
        if (piece.hasSameRole(BISHOP)) {
            return convertSide(piece, "b");
        }
        if (piece.hasSameRole(QUEEN)) {
            return convertSide(piece, "q");
        }
        if (piece.hasSameRole(KING)) {
            return convertSide(piece, "k");
        }
        return ".";
    }

    private String convertSide(final Piece piece, final String convertedPiece) {
        if (piece.getColor().equals(Color.BLACK)) {
            return convertedPiece.toUpperCase();
        }
        return convertedPiece.toLowerCase();
    }

    public void printExceptionMessage(final String message) {
        System.err.println(message);
    }

    public void printStatuses(final Map<Team, Double> status) {
        String statuses = status.entrySet().stream()
                .map(entry -> String.format(entry.getKey() + ": " + entry.getValue() + "점") + System.lineSeparator())
                .collect(Collectors.joining());
        System.out.println(statuses);
    }

    public void showWinner(final Team team) {
        if (team.isEmpty()) {
            System.out.println("우승자가 없습니다.");
            return;
        }
        System.out.println(team.getColor() + "가 이겼습니다.");
    }
}
