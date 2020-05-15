package com.gbf.onlineshop.aspects;

import com.gbf.onlineshop.model.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Aspect
@Component
public class PasswordAspect {
    private final Class<?> aClass = User.class;

    //@AfterReturning(value = "execution(* com.gbf.onlineshop.*.*.*(..)))", returning = "result")
    public void securePassword(Object result) throws IllegalAccessException {
        if (result instanceof User) {
            User user = (User) result;
            user.setPassword("confidential");
            return;
        }
        if (result instanceof Collection) {
            Collection list = (Collection) result;
            if (!list.isEmpty() && list.iterator().next() instanceof User) {
                for (Object o : list) {
                    User user = (User) o;
                    user.setPassword("confidential");
                }
            }
            return;
        }
        Field[] declaredFields = result.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getType().isAssignableFrom(aClass)) {
                boolean b = declaredField.canAccess(result);
                try {
                    declaredField.setAccessible(true);
                    result = declaredField.get(result);
                    securePassword(result);
                }
                finally {
                    declaredField.setAccessible(b);
                }
            }
        }
    }
}
