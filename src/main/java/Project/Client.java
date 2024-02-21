package Project;

public class Client {
    private String name;
    private final String code;
    private String age;
    private String city;
    private static int countCode=1000;

    public Client(String name, String age, String city){
        super();
        this.code = generateCode();
        this.name = name;
        this.age = age;
        this.city = city;
    }

    private String generateCode(){
        return String.valueOf(countCode++);
    }

    public String getName(){
        return name;
    }

    public String getAge(){
        return age;
    }

    public String getCity(){
        return city;
    }

    public String getCode(){
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
