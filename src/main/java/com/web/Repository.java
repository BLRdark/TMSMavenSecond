package com.web;

import java.util.HashMap;
import java.util.Map;

public class Repository {
    private Map<String, String> repository;

    public Repository(){
        repository = new HashMap<>();
    }

    public boolean checkAuthorisation(String username, String password){
        if(repository.containsKey(username)){
            if(repository.get(username).equals(password)){
                return true;
            }
        }
        return false;
    }

    public void register(String username, String password){
        repository.put(username, password);
    }
}

