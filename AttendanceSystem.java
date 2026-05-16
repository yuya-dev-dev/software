import java.util.ArrayList;
import java.util.List;

interface Employee {
    String getId();
    String getName();
    double calculateSalary();
    void recordAttendance(int hours);
}

abstract class BaseEmployee implements Employee {
    private String id;
    private String name;
    protected int totalWorkedHours = 0;

    public BaseEmployee(String id, String name) { this.id = id; this.name = name; }
    public String getId() { return id; }
    public String getName() { return name; }
    public void recordAttendance(int hours) { if(hours > 0) this.totalWorkedHours += hours; }
}

class FullTimeEmployee extends BaseEmployee {
    private double baseSalary;
    public FullTimeEmployee(String id, String name, double baseSalary) { super(id, name); this.baseSalary = baseSalary; }
    public double calculateSalary() {
        return baseSalary + (totalWorkedHours > 160 ? (totalWorkedHours - 160) * 2000.0 : 0);
    }
}

class PartTimeEmployee extends BaseEmployee {
    private double wage;
    public PartTimeEmployee(String id, String name, double wage) { super(id, name); this.wage = wage; }
    public double calculateSalary() { return totalWorkedHours * wage; }
}

public class AttendanceSystem {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new FullTimeEmployee("EMP-01", "山田太郎", 300000));
        list.add(new PartTimeEmployee("EMP-02", "鈴木花子", 1200));
        list.add(new PartTimeEmployee("EMP-03", "佐藤健", 1000));
        
        list.get(0).recordAttendance(170); // 160時間 + 残業10時間
        list.get(1).recordAttendance(85);
        list.get(2).recordAttendance(60);

        System.out.println("=== 月次給与レポート ===");
        System.out.println("-------------------------");
        for (Employee e : list) {
            System.out.printf("ID: %s | 氏名: %s | 支給額: %,.0f円%n", e.getId(), e.getName(), e.calculateSalary());
        }
    }
}