package StreamAPI;

import java.util.stream.Stream;

public enum Status {
    FREE("空闲"),BUSY("忙碌"),VOCATION("休假"),LEFT("离职");
    private String desc;

    Status(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Status{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
