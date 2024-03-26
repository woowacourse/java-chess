package chess.view;

import chess.domain.GameResult;
import chess.domain.Position;
import chess.domain.State;
import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Team;
import chess.dto.BoardStatusDto;
import chess.view.viewer.CharacterViewer;
import chess.view.viewer.GameResultViewer;
import chess.view.viewer.TeamViewer;
import java.util.Map;

public class OutputView {
    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public static void printGameState(BoardStatusDto boardStatusDto) {
        printChessBoard(boardStatusDto.board());
        printState(boardStatusDto.status());
    }

    private static void printChessBoard(Map<Position, Piece> board) {
        for (int i = 8; i >= 1; i--) {
            for (int j = 1; j <= 8; j++) {
                printPiece(board, i, j);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printPiece(Map<Position, Piece> board, int i, int j) {
        if (board.containsKey(Position.of(i, j))) {
            Piece piece = board.get(Position.of(i, j));
            System.out.print(CharacterViewer.show(piece.team(), piece.kind()));
            return;
        }
        System.out.print(".");
    }

    private static void printState(State state) {
        switch (state) {
            case CHECKMATE -> System.out.println("체크메이트!");
            case CHECK -> System.out.println("체크!");
        }
    }

    public static void printWinner(GameResult gameResult) {
        System.out.println(GameResultViewer.show(gameResult));
    }

    public static void printWinner(Team team) {
        System.out.println(TeamViewer.show(team) + "팀 승리!");
    }

    public static void printPoint(Team team, double point) {
        System.out.println(TeamViewer.show(team) + "팀" + point + "점");
    }
}
