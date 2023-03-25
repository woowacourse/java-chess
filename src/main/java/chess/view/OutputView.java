package chess.view;

import chess.domain.game.GameResult;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.FileCoordinate;
import chess.domain.position.Position;
import chess.domain.position.RankCoordinate;
import java.util.Map;

public class OutputView {

    public void printStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 초기화 : clear");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(GameResult gameResult) {
        Map<Position, Piece> boards = gameResult.board();
        for (RankCoordinate rankCoordinate : RankCoordinate.values()) {
            printFile(boards, rankCoordinate);
            System.out.println();
        }
    }

    private void printFile(Map<Position, Piece> boards, RankCoordinate rankCoordinate) {
        for (FileCoordinate fileCoordinate : FileCoordinate.values()) {
            Position position = new Position(fileCoordinate, rankCoordinate);
            Piece piece = boards.get(position);
            String message = PieceTypeView.of(piece.getClass()).getMessage(piece.getColor());
            System.out.print(message);
        }
    }

    public void printError(String message) {
        System.out.println("[ERROR]: " + message);
    }

    public void printStatus(GameResult gameResult) {
        System.out.println("흰팀 점수: " + gameResult.calculateScore(Color.WHITE));
        System.out.println("검은팀 점수: " + gameResult.calculateScore(Color.BLACK));
    }

    public void printEnd() {
        System.out.println("게임 종료");
    }

    public void printWinner(GameResult gameResult) {
        System.out.println(gameResult.getWinner());
    }

    public void printClear() {
        System.out.println("게임이 초기화 되었습니다.");
        System.out.println();
    }
}
