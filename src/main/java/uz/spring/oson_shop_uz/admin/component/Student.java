package uz.spring.oson_shop_uz.admin.component;

public class Student implements Cloneable{
    String name = null;
    int id = 0;

    Student() {

    }

    Student(String name, int id)
    {
        this.name = name;
        this.id = id;
    }


    @Override
    public Student clone() {
        try {
            Student clone = (Student) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
