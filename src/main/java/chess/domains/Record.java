package chess.domains;

public class Record {
    private String record;
    private String errorMsg;
    private String source;
    private String target;

    public Record(String record, String source, String target, String errorMsg) {
        this.record = record;
        this.source = source;
        this.target = target;
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

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
