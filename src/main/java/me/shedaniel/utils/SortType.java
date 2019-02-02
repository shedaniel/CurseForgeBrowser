package me.shedaniel.utils;

public enum SortType {
    DATE_CREATED(1), LAST_UPDATED(2), NAME(3), POPULARITY(4);
    
    private int id;
    
    SortType(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}
