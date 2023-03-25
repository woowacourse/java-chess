package chess.view;

import chess.domain.board.RowPieces;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import chess.view.result.Result;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
        throw new IllegalStateException("인스턴스를 생성할 수 없는 객체입니다.");
    }

    public static void noticeGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }


    public static void printChessBoard(List<RowPieces> chessBoard) {
        System.out.println(parseChessBoardToDisplay(chessBoard));

    }

    private static String parseChessBoardToDisplay(List<RowPieces> chessBoard) {
        return chessBoard.stream()
            .map(OutputView::parseRowPiecesToDisplay)
            .collect(Collectors.joining("\n", "", "\n"));
    }

    private static String parseRowPiecesToDisplay(RowPieces rowPieces) {
        return rowPieces.pieces().stream()
            .map(OutputView::parsePieceToDisplay)
            .collect(Collectors.joining());
    }

    private static String parsePieceToDisplay(Piece piece) {
        String symbol = String.valueOf(SymbolMatcher.symbolOf(piece.symbol()));
        if (piece.isSameTeam(Team.WHITE)) {
            return symbol.toUpperCase();
        }

        if (piece.isSameTeam(Team.BLACK)) {
            return symbol;
        }

        return ".";
    }

    public static void printPresentStatus(double whiteTeamPoint, double blackTeamPoint, Team team) {
        System.out.println("> 현재 점수 상태입니다");
        System.out.println("> 화이트 팀 : " + whiteTeamPoint);
        System.out.println("> 블랙 팀 : " + blackTeamPoint);
        System.out.println(Result.of(team));
    }
}
