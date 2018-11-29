package com.sigma.test;

public enum Algorithm {
    SORT,
    WHITESPACES,
    UNSUPPORTED;

    public static Algorithm parseAlgorithm(String algString) {
        switch (algString) {
            case "SORT":
                return SORT;
            case "WHITESPACES":
                return WHITESPACES;
            default:
                return UNSUPPORTED;
        }
    }
}
