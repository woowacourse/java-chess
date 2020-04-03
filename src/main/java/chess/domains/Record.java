package chess.domains;

public class Record {
    private String record;
    private String errorMsg;

    public Record(String record, String errorMsg) {
        this.record = record;
        this.errorMsg = errorMsg;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
