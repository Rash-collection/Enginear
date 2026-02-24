/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entities;

import engine.Lock;
import funks.Locker;

/**
 *
 * @author rash4
 * @param <T>       Entity type extension.
 */
public abstract class Entity <T extends Entity<T>> implements Locker{
    public Entity(){this.LOCK = new Lock();}
    public T self(){return (T)this;}
    @Override public final Lock lock(){return this.LOCK;}
    private final Lock LOCK;
}