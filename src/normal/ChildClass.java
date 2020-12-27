package normal;

public class ChildClass extends ParentClass {
    public ChildClass() {
        super();
        System.out.println("ChildConstructor");
    }

    public void callMe() {
        System.out.println("ChildClassMessage");
    }


}
