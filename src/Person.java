//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public abstract class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public abstract String getDetails();
}
