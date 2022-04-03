package chess.dto;

public class StatusDto {

    private final String code;
    private final String message;
    private final String myTurn;
    private final String opponentTurn;
    private final double myScore;
    private final double opponentScore;
    private final String result;

    public StatusDto(String myTurn, String opponentTurn, double myScore, double opponentScore, String result) {
        this("success", "", myTurn, opponentTurn, myScore, opponentScore, result);
    }

    public StatusDto(String code, String message) {
        this(code, message, "", "", 0, 0, "");
    }

    public StatusDto(String code, String message, String myTurn, String opponentTurn, double myScore,
                     double opponentScore,
                     String result) {
        this.code = code;
        this.message = message;
        this.myTurn = myTurn;
        this.opponentTurn = opponentTurn;
        this.myScore = myScore;
        this.opponentScore = opponentScore;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMyTurn() {
        return myTurn;
    }

    public String getOpponentTurn() {
        return opponentTurn;
    }

    public double getMyScore() {
        return myScore;
    }

    public double getOpponentScore() {
        return opponentScore;
    }

    public String getResult() {
        return result;
    }
}
