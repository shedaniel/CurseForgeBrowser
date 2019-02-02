package me.shedaniel.parser;

public interface IParser<A, B> {
    
    public A parse(B b);
    
}
