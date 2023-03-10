package com.xgileit.hrm.config;

public class Constant {

    public final class Role {

        public static final String USER = "USER";
        public static final String ADMIN = "ADMIN";
    }

    public final class Path {

        public static final String API = "/api";
        public static final String USERS = "/users";
        public static final String ADMIN = "/admin";
        public static final String COMPANY = "/company";
        public static final String EMPLOYEE = "/employee";

        public final class Registration {

            public static final String REGISTRATION_CONTROLLER = API + "/registration";
            public static final String REGISTRATION_CONTROLLER_API_TEST = "/userRegistrationTest";

            public static final String REGISTRATION = "/userRegistration";
            public static final String REGISTRTION_USERNAME = "/username/{userName}";

        }

        public final class Login {

            public static final String LOGIN_CONTROLLER = API + "/login";
            public static final String UPDATE_PASSWORD = "/update/password/";
            public static final String FORGOT_PASSWORD = "/forgot/password/";
            public static final String LOGIN_CONTROLLER_API_TEST = "/userLoginTest";

        }

        public final class Company {

            public static final String TREK_DETAIL_CONTROLLER = API + COMPANY;
            public static final String TREK_DETAIL_API_TEST = "/companyDetailTest";

        }

        public final class User {

            public static final String USER_MANAGEMENT = "/user-management";
            public static final String USER_CONTROLLER = USER_MANAGEMENT + API + USERS;
            public static final String USER_CONTROLLER_API_TEST = "/userProfileTest";

            public static final String PROFILE = "/{userId}/profile";

            public final class Employee {

                public static final String USER_TREK_CONTROLLER = USER_CONTROLLER + EMPLOYEE;

            }
        }

        public final class Admin {

            public static final String ADMIN_CONTROLLER = API + ADMIN;

            public static final String ADMIN_ORDER_CONTROLLER = ADMIN_CONTROLLER;

            public final class Trek {

                public static final String ADMIN_TREK_DETAIL_CONTROLLER =
                        ADMIN_CONTROLLER + COMPANY;
                public static final String ADMIN_TREK_DETAIL_API_TEST = "/adminTrekDetailTest";

            }
        }

    }
}
