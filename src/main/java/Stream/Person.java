package Stream;

public class Person {
    public String job;
    public int salary;
    public int id;
    public String city;
    public int year;
    public int age;

    @Override
    public String toString() {
        return String.format("{\n\tjob='%s', \n\tsalary=%d, \n\tcity='%s', \n\tage=%d\n},", job, salary, city, age);
    }
}
