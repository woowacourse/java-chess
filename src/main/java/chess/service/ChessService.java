package chess.service;

import chess.domain.exception.WrongOperationException;
import chess.domain.exception.WrongPositionException;
import chess.domain.game.ChessGame;
import chess.domain.game.ScoreResult;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ChessService는 웹의 요청에 따라, 그에 맞는 데이터를 생성하여 반환한다.
 */
public class ChessService {
    /**
     * makeNormalModel는 정상적으로 컨트롤러에서 동작이 수행될 경우에 호출된다.
     * status를 true로 설정하여, chess.html에 설정되어 있는 templete engine의 알림 동작을 수행하지 않도록 한다.
     *
     * @return 정상적인 상태를 담은 model을 반환한다.
     */
    public Map<String, Object> makeNormalModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("status", true);
        return model;
    }

    /**
     * makeInvaildModel은 컨트롤러 내에서 예외가 발생할 경우에 호출된다.
     * status를 false로 설정하고, 추가로 에러 메세지를 exception이라는 이름으로 전달한다.
     * 이 값은 chess.html에 설정된 templete engine 에 전달된다.
     * (이후 웹에서는 에러 메세지를 담은 alert를 띄운다)
     *
     * @param message 예외처리 시 발생한 메세지를 전달한다.
     * @return 비정상적인 상태를 담은 model을 반환한다.
     */
    public Map<String, Object> makeInvalidModel(String message) {
        Map<String, Object> model = new HashMap<>();
        model.put("status", false);
        model.put("exception", message);
        return model;
    }

    /**
     * draw는 웹에 board의 정보를 전달할 때 호출된다.
     * 파라미터로 chessGame을 받아, 그 안에 저장되어 있는 말, 턴, 점수의 정보를 model을 통해 전달한다.
     * status는 true로 설정하여, 오류에 대한 처리를 하지 않도록 한다.
     *
     * @param chessGame 체스를 관리하는 ChessGame 클래스를 받아 현재 게임의 정보를 얻는다.
     * @return 체스 게임의 현재 정보를 담은 model을 반환한다.
     */
    public Map<String, Object> draw(ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();
        model.put("board", chessGame.createBoard().getPiecesForTransfer());
        model.put("turn", chessGame.getTurn());
        model.put("score", chessGame.calculateScore());
        model.put("status", true);
        return model;
    }

    /**
     * terminate는 게임이 종료될 경우에 호출되는 메서드이다.
     * (현재까지는 왕이 잡힌 경우에만 호출된다.)
     * ChessGame 클래스를 파라미터로 전달받아, 현재 승자가 누구인지와 양측 팀의 점수가 몇 점인지를 계산한다.
     * 최종적으로 이를 model에 담아 반환하여, 결과를 웹에서 출력하도록 한다.
     *
     * @param chessGame 체스를 관리하는 ChessGame 클래스를 받아 현재 게임의 정보를 얻는다.
     * @return 체스 게임의 결과 정보를 담은 model을 반환한다.
     */
    public Map<String, Object> terminate(ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();

        model.put("winner", chessGame.getAliveKingColor());
        ScoreResult scoreResult = chessGame.calculateScore();
        for (Color color : scoreResult.keySet()) {
            model.put(color + "score", scoreResult.getScoreBy(color));
        }

        return model;
    }

    /**
     * chooseFirstPosition은 유저가 움직일 말을 선택했을 때 호출된다.
     * 우선 체스 게임의 상태를 저장한 ChessGame 클래스와, 유저가 클릭한 위치 정보를 저장한 Position 클래스를 전달받는다.
     * 이후 이 값을 바탕으로 움직일 말을 찾고, 그 말이 움직일 수 있는 위치 역시 탐색한다.
     * 최종적으로, 말의 위치와 말이 움직일 수 있는 칸들의 위치 정보를 model에 담아 반환한다.
     * 만약 그 과정에서 잘못된 칸을 클릭(말이 없는 경우, 턴이 아닌 말을 선택한 경우)했을 경우, 예외를 발생시킨다.
     * 예외 발생 시에는 status를 false로 설정한 뒤, 예외처리 메세지를 model에 저장하여 반환한다.
     *
     * @param chessGame 체스를 관리하는 ChessGame 클래스를 받아 현재 게임의 정보를 얻는다.
     * @param position 유저가 클릭한 위치의 정보를 전달한다.
     * @return 유저의 말 위치와, 그 말이 움직일 수 있는 칸들의 위치를 model에 담아 반환한다.
     */
    public Map<String, Object> chooseFirstPosition(ChessGame chessGame, String position) {
        Map<String, Object> model = new HashMap<>();
        try {
            model.put("status", true);
            List<String> movablePositionNames = chessGame
                    .findMovablePositions(PositionFactory.of(position))
                    .getPositions()
                    .stream()
                    .map(Position::toString)
                    .collect(Collectors.toList());
            model.put("position", position);
            model.put("movable", movablePositionNames);

            return model;
        } catch (WrongPositionException | WrongOperationException e) {
            model.put("status", false);
            model.put("exception", e.getMessage());
            return model;
        }
    }

    /**
     * chooseSecondPosition은 유저가 (이미 움직일 말을 선택한 상태에서) 말을 어디로 움직일지 선택하였을 때 호출된다.
     * 유저가 선택한 위치는 이전에 선택한 위치와 함께 전달되어 move를 수행한다.
     * 그리고, move를 수행하는 과정에서 잘못된 위치인지 여부에 대해 검사한다.
     * 그렇기 때문에, 여기에서는 별도의 로직 수행 없이 위치 정보와 상태 정보를 model에 담아 반환한다.
     *
     * @param position 유저가 클릭한 위치의 정보를 전달한다.
     * @return 유저가 클릭한 위치와, 상태값(status : true)을 model에 담아 반환한다.
     */
    public Map<String, Object> chooseSecondPosition(String position) {
        Map<String, Object> model = new HashMap<>();
        model.put("status", true);
        model.put("position", position);
        return model;
    }
}
