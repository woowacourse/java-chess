package chess.view;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import chess.model.piece.PieceScore;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import chess.view.dto.ChessBoardResponse;
import chess.view.dto.ChessGameResultResponse;
import chess.view.messsage.CampMessageConverter;
import chess.view.messsage.PieceMessageConverter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAY_CAMP_FORMAT = "%s 진영의 차례입니다.";
    private static final String SCORE_FORMAT = "%s 진영의 점수는 %s 입니다.";
    private static final String WINNER_FORMAT = "승리한 진영은 %s 입니다.";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final int MAX_FILE_INDEX = 7;

    public void guideGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 불러오기 : load");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source 위치 target 위치 - 예. move b2 b3");
    }

    public void guideEndGame() {
        System.out.println("> 체스 게임이 종료되었습니다.");
    }

    public void printCurrentCamp(final Camp camp) {
        final String currentCampMessage = String.format(PLAY_CAMP_FORMAT, CampMessageConverter.convert(camp));

        System.out.println(currentCampMessage);
    }

    public void printChessBoard(final ChessBoardResponse chessBoardResponse) {
        final Map<Position, Piece> squares = chessBoardResponse.getSquares();
        final Map<String, String> squaresMessage = convertToChessBoardMessage(squares);

        final List<String> rankMessage = convertToRankMessage(squaresMessage);

        System.out.println();
        for (int fileIndex = MAX_FILE_INDEX; fileIndex >= 0; fileIndex--) {
            System.out.println(rankMessage.get(fileIndex));
        }
    }

    private Map<String, String> convertToChessBoardMessage(final Map<Position, Piece> board) {
        return board.keySet().stream()
                .collect(Collectors.toMap(
                        Position::getPosition, position -> convertToPieceMessage(board, position)));
    }

    private String convertToPieceMessage(final Map<Position, Piece> board, final Position position) {
        final Piece piece = board.get(position);

        return PieceMessageConverter.convert(piece.getClass(), piece.camp());
    }


    private List<String> convertToRankMessage(final Map<String, String> squares) {
        return Arrays.stream(Rank.values())
                .map(rank -> convertToPieceMessageByRank(squares, rank))
                .collect(Collectors.toList());
    }

    private String convertToPieceMessageByRank(final Map<String, String> squareResponses, final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> mapToPieceMessage(squareResponses, rank, file))
                .collect(Collectors.joining());
    }

    private String mapToPieceMessage(final Map<String, String> squareResponses, final Rank rank, final File file) {
        final String key = file.name() + rank.value();

        return squareResponses.get(key);
    }

    public void printChessGameResult(final ChessGameResultResponse resultResponse) {
        final String blackCampScoreMessage =
                mapToPieceScoreMessageByCamp(resultResponse.getBlackCampScore(), Camp.BLACK);
        final String whiteCampScoreMessage =
                mapToPieceScoreMessageByCamp(resultResponse.getBlackCampScore(), Camp.WHITE);
        final String winnerMessage =
                String.format(WINNER_FORMAT, CampMessageConverter.convert(resultResponse.getWinner()));

        System.out.println(blackCampScoreMessage);
        System.out.println(whiteCampScoreMessage);
        System.out.println(winnerMessage);
    }

    private String mapToPieceScoreMessageByCamp(final PieceScore pieceScore, final Camp camp) {
        return String.format(SCORE_FORMAT, CampMessageConverter.convert(camp), pieceScore.getValue());
    }

    public void printExceptionMessage(final String exceptionMessage) {
        final String message = ERROR_PREFIX + exceptionMessage;

        System.out.println();
        System.out.println(message);
    }
}
