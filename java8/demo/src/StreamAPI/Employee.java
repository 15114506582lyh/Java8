package StreamAPI;



import java.util.Objects;

public class Employee {

    private Integer id;
    private String name;
    private Integer age;
    private Double salary;
    private Status status;

    public Employee() {
        super();
    }

    public Employee(Integer id, String name, Integer age, Double salary, Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId().equals(employee.getId()) && getName().equals(employee.getName()) && getAge().equals(employee.getAge()) && getSalary().equals(employee.getSalary()) && getStatus() == employee.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getSalary(), getStatus());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }
}
