package Combiner;

public class UserValidator {
    public static void main(String[] args) {
        User user = new User("ayman@", "ayman1ER", "ayman1ER");
        UserSignUpValidator.ValidationResult result = UserSignUpValidator
                .isEmailValid()
                .and(UserSignUpValidator.isPasswordValid())
                .and(UserSignUpValidator.isReTypedPasswordValid())
                .apply(user);
        System.out.println(result);
    }

    static class User {
        public String email;
        public String password;
        public String reTypedPassword;

        public User(String email, String password, String reTypedPassword) {
            this.email = email;
            this.password = password;
            this.reTypedPassword = reTypedPassword;
        }
    }
}
