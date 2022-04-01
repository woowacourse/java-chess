package chess.view;

import static java.lang.System.*;

import chess.domain.board.position.Positions;
import java.util.Map;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Piece;

public class OutputView {

    private static final String NEXT_LINE = System.lineSeparator();

    public static void printInitMessage() {

        out.println(
            "> 체스 게임을 시작합니다." + NEXT_LINE +
                "> 게임 시작 : start" + NEXT_LINE +
                "> 게임 종료 : end" + NEXT_LINE +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3"
        );
    }

    public static void printChessBoard(Map<Position, Piece> piecesByPositions) {
        for (Rank rank : Rank.reverseValues()) {
            printFilePieces(piecesByPositions, rank);
            out.println();
        }

    }

    private static void printFilePieces(Map<Position, Piece> piecesByPositions, Rank rank) {
        for (File file : File.values()) {
            Position searchPosition = Positions.findPositionBy(file, rank);
            Piece piece = piecesByPositions.get(searchPosition);
            out.print(piece.getEmblem());
        }
    }

    public static void printCurrentTeamScore(double score) {
        out.println("현재 팀의 스코어는 " + score + " 입니다.");
    }
}
