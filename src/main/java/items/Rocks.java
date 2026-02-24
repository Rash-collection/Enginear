/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package items;

import entities.Stack;
import entities.Rock;

/**
 *
 * @author rash4
 */
public class Rocks extends Stack<Rocks, Rock>{
    public Rocks(){
        super();
        super.sprite = elfactory.Materializing.rock();
        super.source = new Rock();
    }
    public Rocks(int amount){
        super();
        super.sprite = elfactory.Materializing.rock();
        super.source = new Rock(amount);
    }
    public Rocks(int amount, int x, int y, int w, int h){
        super(x, y, w, h);
        super.sprite = elfactory.Materializing.rock();
        this.source = new Rock(amount);
    }
    public Rocks add(Rock neo){
        this.source.amount().add(neo.amount());
        return this;
    }
    public Rocks add(Rocks neo){
        this.source.amount().add(neo.source.amount());
        return this;
    }
    public Rocks take(Rock neo){
        // implement later
        return this;
    }
}