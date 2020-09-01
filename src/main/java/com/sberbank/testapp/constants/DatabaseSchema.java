package com.sberbank.testapp.constants;

public class DatabaseSchema {

    public static final class Abonent {
        public static final String NAME = "Abonent";

        public static final class Columns {
            public static final String ID = "ID";
            public static final String SURNAME = "SURNAME";
            public static final String FIRSTNAME = "FIRSTNAME";
            public static final String SECONDNAME = "SECONDNAME";
            public static final String BIRTH = "BIRTH";
            public static final String TARIFF = "TARIFF";
            public static final String MINUTES = "MINUTES";
        }
    }
    public static final class Tariff {
        public static final String NAME = "Tariff";

        public static final class Columns {
            public static final String ID = "ID";
            public static final String DESCRIPTION = "DESCRIPTION";
        }
    }
}
