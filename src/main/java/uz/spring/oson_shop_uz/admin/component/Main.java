package uz.spring.oson_shop_uz.admin.component;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student student1 = new Student("Abbos", 22);
        Student student2 = student1.clone();
        System.out.println(student2.hashCode());
        System.out.println(student1.hashCode());
    }
}
