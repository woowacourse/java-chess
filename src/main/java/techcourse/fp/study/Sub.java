package techcourse.fp.study;

import java.time.Instant;

public class Sub extends Super {
    private final Instant instant;

    public Sub() {
        this.instant = Instant.now();
    }

    @Override
    public void overRideMe() {
        System.out.println("Sub 클래스");
        System.out.println(instant);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overRideMe();
    }
}
