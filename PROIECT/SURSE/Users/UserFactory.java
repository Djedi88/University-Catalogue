package Users;

public class UserFactory {
    public static User getUser(String userType,String firstName,String lastName){
        if(userType ==null){
            return null;
        }
        switch (userType){
            case "student":
                return new Student(firstName,lastName);
            case "parent":
                return new Parent(firstName,lastName);
            case "assistant":
                return new Assistant(firstName,lastName);
            case "teacher":
                return new Teacher(firstName,lastName);
            default:
                return  null;
        }
    }
}
