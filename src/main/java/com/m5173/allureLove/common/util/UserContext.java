package com.m5173.allureLove.common.util;

import com.m5173.allureLove.model.eo.UserEO;

public final class UserContext {
    private static ThreadLocal<UserContext> context = new ThreadLocal<UserContext>() {
        protected UserContext initialValue() {
            return new UserContext();
        }
    };
    private UserEO user;

    public UserEO getUser() {
        return this.user;
    }

    private UserContext() {
    }

    public static UserContext getCurrentContext() {
        return (UserContext)context.get();
    }

    public static Long getCurrentUserId() {
        UserEO user = getCurrentContext().getUser();
        return user != null ? user.getId() : null;
    }

    public static void setCurrentContext(UserEO user) {
        UserContext userContext = getCurrentContext();
        userContext.user = user;
    }

    public static void clean() {
        context.remove();
    }
}

