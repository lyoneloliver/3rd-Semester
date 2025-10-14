import java.util.ArrayList;
import java.util.List;

public class TransactionLog {
    private List<String> logs = new ArrayList<>();

    public void addLog(String log) {
        logs.add(log);
    }

    public void printLogs() {
        System.out.println("=== Transaction Log ===");
        for(String log : logs) {
            System.out.println(log);
        }
    }
}
