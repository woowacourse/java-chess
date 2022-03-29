package db_study;

public class Role {

    // user_id : 외래키이기 때문에 Member 인스턴스에만 존재
    private final String role;

    public Role( String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

