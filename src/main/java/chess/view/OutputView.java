package chess.view;

import static chess.utils.Constant.END_COMMAND;
import static chess.utils.Constant.MOVE_COMMAND;
import static chess.utils.Constant.START_COMMAND;

import chess.domain.board.GameInformation;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.ChessBoardDto;
import chess.dto.CurrentResultDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_HEADER = "[ERROR] ";
    private static final String NEW_LINE = System.lineSeparator();

    public void printGameInformation(List<GameInformation> gameInfos) {
        System.out.printf(
                "> 체스 게임을 시작합니다." + NEW_LINE
                        + "> 진행중인 게임을 시작하려면 해당 게임 번호를, 새 게임을 시작하려면 0을 입력하세요 - 예. 0 " + NEW_LINE
                        + "> 진행중인 게임 목록 : %s" + NEW_LINE
                , convertToGameList(gameInfos));
    }

    public void printStartMessage(int gameId) {
        System.out.println(
                "> " + gameId + "번 게임을 시작합니다." + NEW_LINE
                        + "> 게임 시작 : " + START_COMMAND + NEW_LINE
                        + "> 게임 종료 : " + END_COMMAND + NEW_LINE
                        + "> 게임 이동 : " + MOVE_COMMAND + " source위치 target위치 - 예. " + MOVE_COMMAND + " b2 b3"
        );
    }

    private String convertToGameList(List<GameInformation> gameInfos) {
        return gameInfos.stream()
                .map(GameInformation::getGameId)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public void printChessBoard(ChessBoardDto chessBoardDto) {
        Map<Position, Piece> chessBoard = chessBoardDto.chessBoard();

        for (Rank rank : Rank.values()) {
            printEachRank(chessBoard, rank);
            System.out.println();
        }
        System.out.println();
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_HEADER + message);
    }

    public void printResultWithKingCaptured(Color winnerColor) {
        System.out.printf("King을 잡았습니다. %s팀이 승리하였습니다.", winnerColor.name());
    }

    private void printEachRank(Map<Position, Piece> chessBoard, Rank rank) {
        for (File file : File.values()) {
            printEachPiece(chessBoard, rank, file);
        }
    }

    private void printEachPiece(Map<Position, Piece> chessBoard, Rank rank, File file) {
        Position position = Position.of(file, rank);
        if (chessBoard.containsKey(position)) {
            Piece piece = chessBoard.get(position);
            System.out.print(PieceSymbol.convertToSymbol(piece));
            return;
        }
        System.out.print(PieceSymbol.printEmptySymbol());
    }

    public void printEachTeamScore(CurrentResultDto currentResultDto) {
        System.out.printf(
                "현재 점수: BLACK - %.1f점, WHITE - %.1f점" + NEW_LINE
                        + "승리팀: %s" + NEW_LINE
                        + "게임을 계속 진행하시려면 이동 명령어를, 종료하시려면 종료 명령어를 입력하세요." + NEW_LINE
                        + NEW_LINE,
                currentResultDto.getBlackScore(), currentResultDto.getWhiteScore(),
                currentResultDto.winnerColor().name());
    }

}
