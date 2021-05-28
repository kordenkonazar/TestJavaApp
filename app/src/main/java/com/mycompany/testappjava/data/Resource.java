package com.mycompany.testappjava.data;

public class Resource <T> {
    public T data = null;
    public String error = null;

    public static class Success<T> extends Resource<T>{
        public Success(T data){
            super.data = data;
        }
    }
    public static class Error<T> extends Resource<T>{
        public Error(String error){
            super.error = error;
        }
    }

    public static class Loading<T> extends Resource<T>{}
}
