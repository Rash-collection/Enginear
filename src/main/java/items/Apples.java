/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package items;

import entities.Stack;
import entities.Apple;

/**
 *
 * @author rash4
 */
public class Apples extends Stack<Apples, Apple>{
    public Apples(){
        super();
        super.sprite = elfactory.Materializing.food();
        super.source = new Apple();
    }
    public Apples(int amount){
        super();
        super.sprite = elfactory.Materializing.food();
        super.source = new Apple(amount);
    }
    public Apples(int amount, int x, int y, int w, int h){
        super(x, y, w, h);
        super.sprite = elfactory.Materializing.food();
        this.source = new Apple(amount);
    }
    public Apples add(Apple neo){
        this.source.amount().add(neo.amount());
        return this;
    }
    public Apples add(Apples neo){
        this.source.amount().add(neo.source.amount());
        return this;
    }
    public Apples take(Apple neo){
        // implement later
        return this;
    }
}