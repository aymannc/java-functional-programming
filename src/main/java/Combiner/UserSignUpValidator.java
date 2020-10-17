package Combiner;

import java.util.function.Function;

public interface UserSignUpValidator extends Function<UserValidator.User, UserSignUpValidator.ValidationResult> {

    static UserSignUpValidator isEmailValid() {
        return user -> user.email.contains("@") ?
                ValidationResult.SUCCESSFUL : ValidationResult.EMAIL_NOT_VALID;
    }

    static UserSignUpValidator isPasswordValid() {
        return user -> (user.password.length() >= 8 && user.password.chars().anyMatch(Character::isDigit)
                && user.password.chars().anyMatch(Character::isAlphabetic)) ?
                ValidationResult.SUCCESSFUL : ValidationResult.PASSWORD_NOT_VALID;
    }

    static UserSignUpValidator isReTypedPasswordValid() {
        return user -> user.password.equals(user.reTypedPassword) ?
                ValidationResult.SUCCESSFUL : ValidationResult.RETYPED_PASSWORD_NOT_VALID;
    }

    default UserSignUpValidator and(UserSignUpValidator other) {
        return user -> {
            ValidationResult result = this.apply(user);
            return result.equals(ValidationResult.SUCCESSFUL) ? other.apply(user) : result;
        };
    }

    enum ValidationResult {
        SUCCESSFUL,
        EMAIL_NOT_VALID,
        PASSWORD_NOT_VALID,
        RETYPED_PASSWORD_NOT_VALID
    }
}
